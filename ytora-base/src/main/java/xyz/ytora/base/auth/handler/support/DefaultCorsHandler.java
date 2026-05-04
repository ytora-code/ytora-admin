package xyz.ytora.base.auth.handler.support;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import xyz.ytora.base.auth.AuthException;
import xyz.ytora.base.auth.AuthProperty;
import xyz.ytora.base.auth.handler.CorsHandler;

/**
 * created by YT on 2025/12/6 17:53:58
 * <br/>
 * 解决跨域问题
 */
@Data
public class DefaultCorsHandler implements CorsHandler {

    private AuthProperty authProperty;

    @Override
    public void setCors(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        // 表示允许跨域请求携带凭据（如 cookies、HTTP 身份验证信息或客户端证书）进行跨域请求
        setHeaderIfPresent(response, "Access-Control-Allow-Credentials", String.valueOf(authProperty.getAllowCredentials()));

        // 设置允许跨域访问的域名 (Access-Control-Allow-Credentials为true，那么Access-Control-Allow-Origin不能为*)
        String origin = request.getHeader("Origin");
        if (origin == null) {
            origin = authProperty.getOrigins();
        }
        setHeaderIfPresent(response, "Access-Control-Allow-Origin", origin);

        // 设置允许跨域访问的请求方法
        setHeaderIfPresent(response, "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // 设置允许跨域访问的自定义头
        setHeaderIfPresent(response, "Access-Control-Allow-Headers", "Content-Type, Authorization, *");

        // 指定前端JavaScript代码在跨域请求中能够访问的响应头，如果JavaScript访问其他请求头，会得到一个空
        setHeaderIfPresent(response, "Access-Control-Expose-Headers", "Content-Disposition, Content-Type, Content-Length");
    }

    /**
     * 添加响应头
     */
    public static void setHeaderIfPresent(HttpServletResponse response, String key, String value) {
        if (response.getHeader(key) == null) {
            response.setHeader(key, value);
        }
    }
}
