package xyz.ytora.base.auth.handler.support;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.ytora.base.auth.AuthException;
import xyz.ytora.base.auth.handler.AuthzHandler;

/**
 * created by YT on 2025/12/6 18:09:23
 * <br/>
 * 默认的授权处理器
 */
public class DefaultAuthzHandler implements AuthzHandler {
    @Override
    public Object doAuthz(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        return new Object();
    }
}
