package xyz.ytora.core.monitor.sse.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.core.monitor.os.logic.OsLogic;

/**
 * 操作系统网络实时速率SSE推送
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OsNetworkSseHandler extends AbsSseEventHandler {

    private final OsLogic osLogic;

    @Override
    public String cronExpression() {
        return "0/3 * * * * ?";
    }

    @Override
    public String getEventName() {
        return "monitor-os-network";
    }

    @Override
    public String getEventDesc() {
        return "操作系统网络实时速率监控";
    }

    @Override
    public Object doExec() {
        return osLogic.getNetworkDynamic();
    }
}
