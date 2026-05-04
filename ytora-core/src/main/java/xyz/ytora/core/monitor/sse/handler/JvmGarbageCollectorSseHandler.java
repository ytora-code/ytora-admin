package xyz.ytora.core.monitor.sse.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.core.monitor.jvm.logic.JvmLogic;

/**
 * JVM垃圾回收SSE推送
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JvmGarbageCollectorSseHandler extends AbsSseEventHandler {

    private final JvmLogic jvmLogic;

    @Override
    public String cronExpression() {
        return "0/5 * * * * ?";
    }

    @Override
    public String getEventName() {
        return "monitor-jvm-gc";
    }

    @Override
    public String getEventDesc() {
        return "JVM垃圾回收实时监控";
    }

    @Override
    public Object doExec() {
        return jvmLogic.getGarbageCollectors();
    }
}
