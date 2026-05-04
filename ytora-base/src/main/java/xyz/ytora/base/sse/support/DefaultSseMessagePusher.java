package xyz.ytora.base.sse.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.sse.ISseMetrics;
import xyz.ytora.base.sse.ISsePusher;
import xyz.ytora.base.sse.SseClientPushStats;
import xyz.ytora.base.sse.SsePushMessageRecord;
import xyz.ytora.base.sse.SseMessage;
import xyz.ytora.toolkit.text.Strs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * SSE 消息推送器
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "ytora", name = "sse", havingValue = "true")
public class DefaultSseMessagePusher extends DefaultSseRegister implements ISsePusher, ISseMetrics {

    /**
     * 每个客户端最近消息保留数量。
     */
    private static final int RECENT_MESSAGE_LIMIT = 10;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 客户端推送统计缓存。
     * <p>
     * key 为客户端ID，value 为该客户端的累计推送大小与最近消息记录。
     */
    private final Map<String, ClientPushBucket> clientPushBuckets = new ConcurrentHashMap<>();

    public DefaultSseMessagePusher() {
        System.out.println();
    }

    /**
     * 推送消息
     */
    @Override
    public void push(@NonNull SseMessage message) {
        if (size() == 0) {
            return;
        }
        List<SseEmitter> sseEmitters = listCandidateSseConnection(message.getTo());
        // 如果没有任何SSE连接，则不发送
        if (sseEmitters.isEmpty()) {
            return;
        }
        // 如果要发送的数据为空，则不发送
        Object data = message.getData();
        if (data == null) {
            return;
        }
        String dataStr;
        try {
            dataStr = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new BaseException(e);
        }

        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .id(message.getId())
                .name(message.getEvent())
                .data(dataStr, MediaType.APPLICATION_JSON);

        //发送失败的连接
        List<SseEmitter> failedEmitters = new ArrayList<>();
        for (SseEmitter sseEmitter : sseEmitters) {
            try {
                sseEmitter.send(sseEventBuilder);
                String clientId = getClientId(sseEmitter);
                if (clientId != null) {
                    recordPush(clientId, message, dataStr);
                }
            } catch (Exception e) {
                failedEmitters.add(sseEmitter);
                log.warn("SSE消息发送失败，to: {}, messageId: {}, error: {}", message.getTo(), message.getId(), e.getMessage());
            }
        }

        // 使用子线程关闭连接
//        if (!failedEmitters.isEmpty()) {
//            T.exec(() -> failedEmitters.forEach(super::closeEmitter));
//        }
    }

    /**
     * 获取符合条件的SSE连接对象
     */
    private List<SseEmitter> listCandidateSseConnection(String targetClientId) {
        //如果targetClientId为空，则表示广播消息
        if (Strs.isEmpty(targetClientId)) {
            return getAll();
        }

        //否则给指定的客户端发送消息
        SseEmitter sseEmitter = get(targetClientId);
        if (sseEmitter == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(sseEmitter);
    }

    @Override
    public List<SseClientPushStats> listClientPushStats() {
        return clientPushBuckets.values().stream()
                .map(ClientPushBucket::snapshot)
                .sorted(Comparator.comparingLong(SseClientPushStats::totalPayloadBytes).reversed())
                .toList();
    }

    /**
     * 记录单个客户端的一次推送。
     */
    private void recordPush(String clientId, SseMessage message, String payload) {
        ClientPushBucket bucket = clientPushBuckets.computeIfAbsent(clientId, key -> new ClientPushBucket(clientId));
        bucket.record(message, payload);
    }

    /**
     * 单个客户端的推送统计桶。
     */
    private static final class ClientPushBucket {
        private final String clientId;
        private final LongAdder totalPayloadBytes = new LongAdder();
        private final LinkedList<SsePushMessageRecord> recentMessages = new LinkedList<>();

        private ClientPushBucket(String clientId) {
            this.clientId = clientId;
        }

        private void record(SseMessage message, String payload) {
            long payloadBytes = payload.getBytes(java.nio.charset.StandardCharsets.UTF_8).length;
            totalPayloadBytes.add(payloadBytes);

            SsePushMessageRecord record = new SsePushMessageRecord(
                    System.currentTimeMillis(),
                    message.getEvent(),
                    message.getId(),
                    payload,
                    payloadBytes
            );

            synchronized (recentMessages) {
                recentMessages.addFirst(record);
                while (recentMessages.size() > RECENT_MESSAGE_LIMIT) {
                    recentMessages.removeLast();
                }
            }
        }

        private SseClientPushStats snapshot() {
            synchronized (recentMessages) {
                return new SseClientPushStats(clientId, totalPayloadBytes.sum(), List.copyOf(recentMessages));
            }
        }
    }
}
