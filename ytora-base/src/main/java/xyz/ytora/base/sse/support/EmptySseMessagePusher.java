package xyz.ytora.base.sse.support;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.ISseMetrics;
import xyz.ytora.base.sse.ISsePusher;
import xyz.ytora.base.sse.SseClientPushStats;
import xyz.ytora.base.sse.SseMessage;

import java.util.Collections;
import java.util.List;

/**
 * SSE 消息推送器
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "ytora", name = "sse", havingValue = "false")
public class EmptySseMessagePusher extends DefaultSseRegister implements ISsePusher, ISseMetrics {

    public EmptySseMessagePusher() {
        System.out.println();
    }

    /**
     * 推送消息
     */
    @Override
    public void push(@NonNull SseMessage message) {

    }

    @Override
    public List<SseClientPushStats> listClientPushStats() {
        return Collections.emptyList();
    }

}
