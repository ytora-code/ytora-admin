package xyz.ytora.base.scope;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.ytora.base.auth.LoginUser;

/**
 * 统一维护所有的 ScopedValue
 */
public interface ScopedValueItem {
    /**
     * 定时任务日志ID上下文
     */
    ScopedValue<String> TASK_LOG_ID = ScopedValue.newInstance();

    /**
     * SQL操作的来源
     */
    ScopedValue<String> SQL_SOURCE = ScopedValue.newInstance();

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
     * 当前登录用户
     */
    ScopedValue<LoginUser> LOGIN_USER = ScopedValue.newInstance();

    /**
     * 用户权限
     */
    ScopedValue<Object> USER_PERMISSION = ScopedValue.newInstance();
}


