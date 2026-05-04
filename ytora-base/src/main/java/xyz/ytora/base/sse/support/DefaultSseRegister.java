package xyz.ytora.base.sse.support;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.sse.ISseRegister;
import xyz.ytora.base.sse.SseClientInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    /**
     * 客户端元信息缓存。
     */
    private final Map<String, SseClientInfo> clientInfoCache = new ConcurrentHashMap<>();

    @Override
    public void doRegister(String id, SseEmitter emitter) {
        SseEmitter oldEmitter = connectionCache.get(id);
        // 销毁旧有连接
        if (oldEmitter != null) {
            closeEmitter(id, oldEmitter);
        }

        setupEmitterCallbacks(id, emitter);
        // 绑定SSE连接
        connectionCache.put(id, emitter);
        revertConnectionCache.put(emitter, id);
        // 绑定SSE客户端
        clientInfoCache.put(id, buildClientInfo(id));
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
    public int size() {
        return connectionCache.size();
    }

    @Override
    public List<SseClientInfo> listClientInfos() {
        return clientInfoCache.values().stream()
                .sorted((left, right) -> Long.compare(right.connectedAt(), left.connectedAt()))
                .toList();
    }

    /**
     * 根据 emitter 反查客户端ID。
     * <p>
     * 该方法提供给 SSE 推送器内部做统计使用，
     * 不对外暴露到接口层。
     */
    protected String getClientId(SseEmitter emitter) {
        return revertConnectionCache.get(emitter);
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
            clientInfoCache.remove(id);
        }
    }

    /**
     * 尝试从当前请求上下文提取客户端信息。
     * <p>
     * SSE 建连通常发生在控制器请求线程中，因此这里优先从 ScopedValue 读取请求对象。
     */
    private SseClientInfo buildClientInfo(String clientId) {
        HttpServletRequest request = ScopedValueContext.REQUEST.isBound() ? ScopedValueContext.REQUEST.get() : null;
        if (request == null) {
            return new SseClientInfo(clientId, null, null, null, System.currentTimeMillis());
        }

        return new SseClientInfo(
                clientId,
                request.getRequestURI(),
                resolveClientIp(request),
                request.getHeader("User-Agent"),
                System.currentTimeMillis()
        );
    }

    /**
     * 尽量获取真实客户端 IP。
     */
    private String resolveClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }

        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isBlank()) {
            return realIp.trim();
        }

        return Objects.toString(request.getRemoteAddr(), null);
    }
}
