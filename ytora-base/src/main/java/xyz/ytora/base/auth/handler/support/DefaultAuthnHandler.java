package xyz.ytora.base.auth.handler.support;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.ytora.base.auth.AuthException;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.auth.handler.AuthnHandler;

/**
 * created by YT on 2025/12/6 18:08:24
 * <br/>
 * 默认的认证处理器
 */
public class DefaultAuthnHandler implements AuthnHandler {
    @Override
    public LoginUser doAuthn(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        return new LoginUser().setUserName("admin");
    }
}
