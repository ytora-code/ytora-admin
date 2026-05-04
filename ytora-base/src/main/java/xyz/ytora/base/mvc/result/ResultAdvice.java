package xyz.ytora.base.mvc.result;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.mvc.result.anno.DownloadMapper;
import xyz.ytora.base.mvc.result.anno.NoWrap;
import xyz.ytora.base.swagger.SpringDocConfig;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.base.util.json.Jsons;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;

/**
 * 返回结果的增强
 *
 * <p>SpringMvc消息转换器在将控制器的返回值写入响应体之前，有一处增强</p>
 * <p>在这处增强，可以对最终写入响应体的数据做修改</p>
 * <p>只能对basePackages指定包下类的返回值生效</p>
 *
 * @author ytora 
 * @since 1.0
 */
@ControllerAdvice
@ConditionalOnBean({SpringDocConfig.class})
@Order(0)
public class ResultAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private SpringDocConfig springDocConfig;

    AntPathMatcher matcher = new AntPathMatcher();

    /**
     * 判断是否应该对控制器方法的返回值进行增强
     * 如果接口返回类型不是R，也不是void，就转换为R再返回
     *
     * @param returnType    封装后的返回值
     * @param converterType 消息转换器
     * @return 是否增强
     */
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
//        HttpServletRequest request = ScopedValueContext.REQUEST.get();
        HttpServletRequest request = HttpUtil.getReq();
        if (request == null) {
            return false;
        }

        //如果是swagger请求，则不需要被增强
        for (String path : springDocConfig.swaggerPath()) {
            if (matcher.match(path, request.getRequestURI())) {
                return false;
            }
        }

        Method method = returnType.getMethod();
        if (method == null || method.isAnnotationPresent(NoWrap.class)) {
            return false;
        }
        Class<?> methodReturnType = method.getReturnType();

        return !(
                //如果接口返回值符合下面任一条件，就不进行增强
                void.class.equals(methodReturnType)
                        || R.class.isAssignableFrom(methodReturnType)
                        || HttpEntity.class.isAssignableFrom(methodReturnType)
                        || byte[].class.equals(methodReturnType)
                        || InputStream.class.equals(methodReturnType)
                        || Reader.class.equals(methodReturnType)
                        || File.class.equals(methodReturnType)
                        || SseEmitter.class.equals(methodReturnType)
                        || method.isAnnotationPresent(DownloadMapper.class)
        );
    }

    /**
     * 对最终写入响应体的返回值进行修改
     *
     * @param body                  控制器方法的返回值
     * @param returnType            封装后的返回值
     * @param selectedContentType   选择的内容类型
     * @param selectedConverterType 选择的转换器类型
     * @param request               请求对象
     * @param response              响应对象
     * @return 返回值就是最终写入响应体的数据
     */
    @Override
    @NonNull
    public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof R || body instanceof HttpEntity) {
            return body;
        } else if (body instanceof String stringBody) {
            //Springmvc会使用StringHttpMessageConverter来处理返回String的控制器方法，所以需要进行如下处理，告诉springmvc这是json类型
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return Jsons.toJsonStr(R.success(stringBody, stringBody));
        } else {
            return R.success(null, body);
        }
    }

}
