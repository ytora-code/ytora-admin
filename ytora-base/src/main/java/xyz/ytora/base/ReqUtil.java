package xyz.ytora.base;

import jakarta.servlet.http.HttpServletRequest;
import xyz.ytora.base.scope.ScopedValueItem;

/**
 * created by yangtong on 2025/4/7 16:07:29
 */
public class ReqUtil {

    static String[] ipHeaders = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };

    /**
     * 获取客户端IP
     */
    public static String getClientIp() {
        HttpServletRequest request = ScopedValueItem.REQUEST.get();
        return getClientIp(request);
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return "-1";
        }

        for (String header : ipHeaders) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                // 多个IP时，取第一个（最原始的客户端IP）
                return ip.split(",")[0];
            }
        }
        // 最后兜底
        return request.getRemoteAddr();
    }

}
