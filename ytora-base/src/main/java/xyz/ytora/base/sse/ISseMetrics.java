package xyz.ytora.base.sse;

import java.util.List;

/**
 * SSE 运行期统计接口。
 * <p>
 * 用于暴露 SSE 推送侧的监控数据，
 * 例如每个客户端累计推送字节数、最近推送消息列表等。
 */
public interface ISseMetrics {

    /**
     * 获取全部客户端推送统计信息。
     */
    List<SseClientPushStats> listClientPushStats();
}
