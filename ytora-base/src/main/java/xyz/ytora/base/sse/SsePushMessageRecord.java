package xyz.ytora.base.sse;

/**
 * 单条 SSE 推送记录。
 *
 * @param timestamp    推送时间戳
 * @param event        事件名称
 * @param messageId    消息ID
 * @param payload      推送数据
 * @param payloadBytes 推送数据字节数
 */
public record SsePushMessageRecord(
        long timestamp,
        String event,
        String messageId,
        String payload,
        long payloadBytes
) {
}
