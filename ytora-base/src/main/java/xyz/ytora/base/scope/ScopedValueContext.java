package xyz.ytora.base.scope;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import xyz.ytora.base.auth.LoginUser;

import java.lang.ScopedValue;
import java.util.List;

/**
 * 统一维护所有的 ScopedValue
 *
 * @author ytora
 * @since 1.0
 */
public interface ScopedValueContext {

    /**
     * 空上下文
     */
    ScopedValue<String> NULL_CONTEXT = ScopedValue.newInstance();

    /**
     * 数据源上下文
     */
    ScopedValue<String> DS_CONTEXT = ScopedValue.newInstance();

    /**
     * 请求对象
     */
    ScopedValue<HttpServletRequest> REQUEST = ScopedValue.newInstance();

    /**
     * 请求对象
     */
    ScopedValue<HttpServletResponse> RESPONSE = ScopedValue.newInstance();

    /**
     * spring的上下文容器
     */
    ScopedValue<ApplicationContext> APPLICATION_CONTEXT = ScopedValue.newInstance();

    /**
     * 当前登录用户
     */
    ScopedValue<LoginUser> LOGIN_USER = ScopedValue.newInstance();

    /**
     * 用户权限
     */
    ScopedValue<Object> USER_PERMISSION = ScopedValue.newInstance();

    /**
     * 数据范围
     */
    ScopedValue<List<?>> DATA_SCOPE = ScopedValue.newInstance();

    /**
     * 日志上下文ID traceId
     */
    ScopedValue<String> TRACE_ID = ScopedValue.newInstance();

}
