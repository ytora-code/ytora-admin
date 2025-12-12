package xyz.ytora.base.sse;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * SSE 注册器
 */
@FunctionalInterface
public interface ISseRegister {
    /**
     * 注册sse
     */
    void doRegister(String id, SseEmitter emitter);
}
