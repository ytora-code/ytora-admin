package xyz.ytora.base.auth.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.ytora.base.auth.AuthException;

/**
 * 公共资源处理器
 */
@FunctionalInterface
public interface PublicResourcesHandler extends IHandler {

    /**
     * 判断当前请求的资源是否属于无需鉴权的公共资源
     * @param request 请求对象
     * @param response 响应对象
     * @return true:是公共资源;false:不是公共资源
     * @throws AuthException 处理过程中可能抛出异常，此时视为处理失败
     */
    boolean isPublic(HttpServletRequest request, HttpServletResponse response) throws AuthException;

}
