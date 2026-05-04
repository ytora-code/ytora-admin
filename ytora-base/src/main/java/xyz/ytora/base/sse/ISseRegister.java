package xyz.ytora.base.sse;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * SSE 注册器
 */
public interface ISseRegister {
    /**
     * 注册sse
     */
    void doRegister(String id, SseEmitter emitter);

    /**
     * 当前连接数。
     */
    int size();

    /**
     * 客户端连接信息列表。
     */
    List<SseClientInfo> listClientInfos();

    /**
     * 订阅事件
     * @param id 客户端ID
     * @param eventName 事件名称
     */
    void subscribe(String id, String eventName);

    /**
     * SSE客户端取消事件订阅
     * @param id 客户端ID
     * @param eventName 事件名称
     */
    void unSubscribe(String id, String eventName);
}
