package xyz.ytora.core.monitor.sse.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.core.monitor.os.logic.OsLogic;

/**
 * 操作系统磁盘动态监控SSE推送
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OsDiskSseHandler extends AbsSseEventHandler {

    private final OsLogic osLogic;

    @Override
    public String cronExpression() {
        return "0/5 * * * * ?";
    }

    @Override
    public String getEventName() {
        return "monitor-os-disk";
    }

    @Override
    public String getEventDesc() {
        return "操作系统磁盘动态监控";
    }

    @Override
    public Object doExec() {
        return osLogic.getDiskDynamic();
    }
}
