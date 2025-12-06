package xyz.ytora.base.auth.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xyz.ytora.base.auth.AuthException;

/**
 * 跨域处理器
 */
public interface CorsHandler extends IHandler {

    /**
     * 给响应对象添加跨域响应头
     * @param request 请求对象
     * @param response 响应对象
     * @throws AuthException 处理过程中可能抛出异常，此时视为处理失败
     */
    void setCors(HttpServletRequest request, HttpServletResponse response) throws AuthException;

}
