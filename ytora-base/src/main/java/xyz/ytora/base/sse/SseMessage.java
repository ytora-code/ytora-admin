package xyz.ytora.base.sse;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * SSE 消息
 */
@Data
@Accessors(chain = true)
public class SseMessage {
    /**
     * 消息唯一ID
     */
    private String id;

    /**
     * 事件名称（对应SSE的event字段）
     */
    private String event = "default";

    /**
     * 消息数据（对应SSE的data字段）
     */
    private Object data;

    /**
     * 消息类型/分类,1-系统消息，2-广播消息，3-管理员消息，4-单聊消息，5-群聊消息
     */
    private Integer type;

    /**
     * 创建时间戳
     */
    private long timestamp;

    /**
     * 消息来源
     */
    private String source;

    /**
     * 消息发送目标（可选，为空表示广播）
     */
    private String to;

    /**
     * 消息标签（用于分组和过滤）
     */
    private Set<String> tags;

    /**
     * 消息元数据
     */
    private Map<String, Object> metadata;

    public SseMessage() {
        this.timestamp = System.currentTimeMillis();
        this.id = UUID.randomUUID().toString();
    }
}
