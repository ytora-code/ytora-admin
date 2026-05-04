package xyz.ytora.core.monitor.sse.handler;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.core.monitor.sse.logic.SseLogic;

/**
 * 应用SSE连接状态推送
 */
@Slf4j
@Component
public class AppSseMetricsSseHandler extends AbsSseEventHandler {

    @Lazy
    @Resource
    private SseLogic sseLogic;

    @Override
    public String cronExpression() {
        return "0/5 * * * * ?";
    }

    @Override
    public String getEventName() {
        return "monitor-app-sse";
    }

    @Override
    public String getEventDesc() {
        return "应用SSE连接状态实时监控";
    }

    @Override
    public Object doExec() {
        return sseLogic.getSseMetrics();
    }
}
