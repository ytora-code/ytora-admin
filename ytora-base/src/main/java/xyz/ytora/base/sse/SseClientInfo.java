package xyz.ytora.base.sse;

/**
 * SSE 客户端连接信息。
 *
 * @param clientId     客户端标识
 * @param requestUri   建连请求路径
 * @param clientIp     客户端IP
 * @param userAgent    客户端UA
 * @param connectedAt  建连时间
 */
public record SseClientInfo(
        String clientId,
        String requestUri,
        String clientIp,
        String userAgent,
        long connectedAt
) {
}
