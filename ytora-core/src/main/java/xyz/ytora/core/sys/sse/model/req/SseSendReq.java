package xyz.ytora.core.sys.sse.model.req;

import lombok.Data;

/**
 * SSE 请求数据
 */
@Data
public class SseSendReq {

    /**
     * 消息发送给
     */
    private String to;

    /**
     * 消息内容
     */
    private String message;

}
