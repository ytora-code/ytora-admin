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
}
