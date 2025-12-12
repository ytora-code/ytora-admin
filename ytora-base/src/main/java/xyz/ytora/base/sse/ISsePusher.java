package xyz.ytora.base.sse;

/**
 * SSE 消息推送器
 */
public interface ISsePusher {
    /**
     * 消息推送
     * @param message 消息
     */
    void push(SseMessage message);
}
