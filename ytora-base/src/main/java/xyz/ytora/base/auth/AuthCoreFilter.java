package xyz.ytora.base.auth;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.ytora.base.auth.handler.AuthnHandler;
import xyz.ytora.base.auth.handler.AuthzHandler;
import xyz.ytora.base.auth.handler.CorsHandler;
import xyz.ytora.base.auth.handler.PublicResourcesHandler;
import xyz.ytora.base.auth.request.AppRequestMetricsCollector;
import xyz.ytora.base.exception.error.AppErrorBuffer;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.base.mvc.result.ResultCode;
import xyz.ytora.base.scope.ScopedValueContext;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 权限核心过滤器
 *
 * <p>进行认证授权操作</p>
 *
 * @author ytora 
 * @since 1.0
 */
@Slf4j
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AuthCoreFilter extends OncePerRequestFilter {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private AppRequestMetricsCollector appRequestMetricsCollector;

    @Resource
    private AppErrorBuffer appErrorBuffer;

    /**
     * 判断请求资源是否是公共资源的组件
     */
    private PublicResourcesHandler publicResourcesHandler;

    /**
     * 跨域处理器，解决跨域问题
     */
    private CorsHandler corsHandler;

    /**
     * 认证组件，判断当前用户是谁
     */
    private AuthnHandler authenticateHandler;
    /**
     * 鉴权组件，判断当前用户有没有资格访问目标资源
     */
    private AuthzHandler authorizeHandler;

    /**
     * auth 配置属性
     */
    private AuthProperty authProperty;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws IOException, ServletException {
        LoginUser currentLoginUser = null;
        Throwable throwable = null;
        AppRequestMetricsCollector.RequestTrack requestTrack = appRequestMetricsCollector.begin(request);
        try {
            // 添加跨域响应头
            corsHandler.setCors(request, response);

            // 如果是预检请求，直接返回
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }

            // 获取业务代码抛出的异常
            AtomicReference<IOException> ioExceptionRef = new AtomicReference<>();
            AtomicReference<ServletException> servletExceptionRef = new AtomicReference<>();

            // 将请求对象和响应对象保存到 ScopedValue
            ScopedValue.Carrier scope = ScopedValue
                    .where(ScopedValueContext.REQUEST, request)
                    .where(ScopedValueContext.RESPONSE, response)
                    .where(ScopedValueContext.APPLICATION_CONTEXT, applicationContext);

            // 如果是公共资源，或者auth没有启用，直接访问资源
            if (publicResourcesHandler.isPublic(request, response) || !authProperty.getEnable()) {
                scope.run(() -> {
                    try {
                        filterChain.doFilter(request, response);
                    } catch (IOException e) {
                        ioExceptionRef.set(e);
                    } catch (ServletException e) {
                        servletExceptionRef.set(e);
                    }
                });

                if (ioExceptionRef.get() != null) {
                    throw ioExceptionRef.get();
                }
                if (servletExceptionRef.get() != null) {
                    throw servletExceptionRef.get();
                }
            }
            // 如果不是公共资源，则需要认证和授权
            else {
                // 认证
                LoginUser loginUser = authenticateHandler.doAuthn(request, response);
                currentLoginUser = loginUser;
                // 授权
                Object permission = authorizeHandler.doAuthz(request, response);
                // 如果上面两个步骤没有抛出异常，说明认证授权通过
                scope.where(ScopedValueContext.LOGIN_USER, loginUser)
                        .where(ScopedValueContext.USER_PERMISSION, permission)
                        .run(() -> {
                            try {
                                filterChain.doFilter(request, response);
                            } catch (IOException e) {
                                ioExceptionRef.set(e);
                            } catch (ServletException e) {
                                servletExceptionRef.set(e);
                            }
                        });

                // 说明run内部代码抛出了异常，要将该异常抛出去，让外部感知到
                if (ioExceptionRef.get() != null) {
                    throw ioExceptionRef.get();
                }
                if (servletExceptionRef.get() != null) {
                    throw servletExceptionRef.get();
                }
            }

        } catch (AuthException e) {
            throwable = e;
            appErrorBuffer.record(e, "AUTH", request, currentLoginUser);
            log.error("鉴权环节异常：{}", e.getMessage(), e);
            HttpUtil.response(response, e.getCode(), e.getMessage());
        } catch (Exception e) {
            throwable = e;
            appErrorBuffer.record(e, "AUTH", request, currentLoginUser);
            log.error("鉴权环节未知异常：{}", e.getMessage(), e);
            HttpUtil.response(response, ResultCode.UNKNOWN_USER_PASSWORD.code, e.getMessage());
        } finally {
            appRequestMetricsCollector.complete(requestTrack, response.getStatus(), throwable, currentLoginUser);
        }
    }

}
