package xyz.ytora.base.sse.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.sse.ISseRegister;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SSE 注册器
 */
@Slf4j
public class DefaultSseRegister implements ISseRegister {

    /**
     * 管理所有SSE连接
     */
    private final Map<String, SseEmitter> connectionCache = new ConcurrentHashMap<>();

    /**
     * 反向映射：SseEmitter -> ClientId
     */
    private final Map<SseEmitter, String> revertConnectionCache = new ConcurrentHashMap<>();

    @Override
    public void doRegister(String id, SseEmitter emitter) {
        SseEmitter oldEmitter = connectionCache.get(id);
        // 销毁旧有连接
        if (oldEmitter != null) {
            closeEmitter(id, oldEmitter);
        }

        setupEmitterCallbacks(id, emitter);
        connectionCache.put(id, emitter);
        revertConnectionCache.put(emitter, id);
    }

    /**
     * 关闭sse连接
     */
    public void closeEmitter(String id, SseEmitter emitter) {
        try {
            // 1. 正常关闭连接
            emitter.complete();
        } catch (Exception e) {
            // 如果正常关闭失败，直接强制关闭
            emitter.completeWithError(e);
        } finally {
            // 3. 从活跃连接中移除
            removeConnection(id, emitter);
            log.info("已销毁用户 {} 的旧连接", id);
        }
    }

    /**
     * 关闭sse连接
     */
    public void closeEmitter(SseEmitter emitter) {
        if (emitter == null) {
            return;
        }

        String id = revertConnectionCache.get(emitter);
        if (id != null) {
            closeEmitter(id, emitter);
        } else {
            // 如果找不到对应的ID，直接关闭emitter
            log.warn("未找到emitter对应的客户端ID，直接关闭连接");
            try {
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }
    }

    /**
     * 获取指定id的sse连接
     */
    public SseEmitter get(String id) {
        return connectionCache.get(id);
    }

    /**
     * 获取全部sse连接
     */
    public List<SseEmitter> getAll() {
        return connectionCache.values().stream().toList();
    }

    /**
     * 获取当前建立SSE连接的数量
     */
    public Integer size() {
        return connectionCache.size();
    }

    // 设置 SseEmitter 回调
    private void setupEmitterCallbacks(String id, SseEmitter emitter) {
        emitter.onCompletion(() -> {
            removeConnection(id, emitter);
            log.info("用户 {} 连接正常关闭", id);
        });

        emitter.onTimeout(() -> {
            removeConnection(id, emitter);
            log.warn("用户 {} 连接超时", id);
        });

        emitter.onError((ex) -> {
            removeConnection(id, emitter);
            log.error("用户 {} 连接错误: {}", id, ex.getMessage());
        });
    }

    /**
     * 移除SseEmitter
     */
    private void removeConnection(String id, SseEmitter targetEmitter) {
        SseEmitter currentEmitter = connectionCache.get(id);
        if (currentEmitter == targetEmitter) {
            connectionCache.remove(id, targetEmitter);
            revertConnectionCache.remove(targetEmitter);
        }
    }
}
