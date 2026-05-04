package xyz.ytora.core.monitor.sse.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * SSE 请求数据
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SseSendParam {

    /**
     * 事件类型
     */
    private String eventName;

    /**
     * 消息发送给
     */
    private String to;

    /**
     * 消息内容
     */
    private String message;

}
