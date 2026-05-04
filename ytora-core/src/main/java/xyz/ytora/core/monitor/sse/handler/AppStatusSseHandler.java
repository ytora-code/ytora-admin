package xyz.ytora.core.monitor.sse.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.core.monitor.app.logic.AppLogic;

/**
 * 应用状态SSE推送
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AppStatusSseHandler extends AbsSseEventHandler {

    private final AppLogic appLogic;

    @Override
    public String cronExpression() {
        return "0/10 * * * * ?";
    }

    @Override
    public String getEventName() {
        return "monitor-app-status";
    }

    @Override
    public String getEventDesc() {
        return "应用状态实时监控";
    }

    @Override
    public Object doExec() {
        return appLogic.getStatus();
    }
}
