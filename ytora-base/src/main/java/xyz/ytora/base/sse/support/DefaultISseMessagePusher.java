package xyz.ytora.base.sse.support;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.sse.ISsePusher;
import xyz.ytora.base.sse.SseMessage;
import xyz.ytora.ytool.json.Jsons;
import xyz.ytora.ytool.str.Strs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SSE 消息推送器
 */
@Slf4j
@Component
public class DefaultISseMessagePusher extends DefaultSseRegister implements ISsePusher {
    /**
     * 推送消息
     */
    @Override
    public void push(@NonNull SseMessage message) {
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
        String dataStr = Jsons.toJsonStr(data);

        SseEmitter.SseEventBuilder sseEventBuilder = SseEmitter.event()
                .id(message.getId())
                .name(message.getEvent())
                .data(dataStr, MediaType.APPLICATION_JSON);

        //发送失败的连接
        List<SseEmitter> failedEmitters = new ArrayList<>();
        for (SseEmitter sseEmitter : sseEmitters) {
            try {
                sseEmitter.send(sseEventBuilder);
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
}
