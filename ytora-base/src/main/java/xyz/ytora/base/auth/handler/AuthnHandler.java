package xyz.ytora.base.auth.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.ytora.base.auth.AuthException;
import xyz.ytora.base.auth.LoginUser;

/**
 * 认证环节
 */
@FunctionalInterface
public interface AuthnHandler extends IHandler {
    /**
     * 认证
     * @param request 请求对象
     * @param response 响应对象
     * @return 认证结果，返回当前登录用户
     * @throws AuthException 处理过程中可能抛出异常，此时视为处理失败
     */
    LoginUser doAuthn(HttpServletRequest request, HttpServletResponse response) throws AuthException;
}
