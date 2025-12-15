package xyz.ytora.base.sse.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.base.sse.support.DefaultSseRegister;

/**
 * 测试SSE发送
 */
@Component
@RequiredArgsConstructor
public class TestHandler extends AbsSseEventHandler {

    private final DefaultSseRegister sseRegister;

    @Override
    public String cronExpression() {
        return "0/3 * * * * ?";
    }

    @Override
    public String getEventName() {
        return "test";
    }

    @Override
    public String getEventDesc() {
        return "测试使用的SSE事件";
    }

    @Override
    public Object doExec() {
        return "hello SSE";
    }
}
