package xyz.ytora.base.sse;

import java.util.List;

/**
 * SSE 客户端推送统计。
 *
 * @param clientId          客户端ID
 * @param totalPayloadBytes 累计推送字节数
 * @param recentMessages    最近推送的10条消息
 */
public record SseClientPushStats(
        String clientId,
        long totalPayloadBytes,
        List<SsePushMessageRecord> recentMessages
) {
}
