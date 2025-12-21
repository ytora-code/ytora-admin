package xyz.ytora.core.sys.sse.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.mvc.R;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.base.sse.ISsePusher;
import xyz.ytora.base.sse.ISseRegister;
import xyz.ytora.base.sse.SseMessage;
import xyz.ytora.core.sys.sse.model.req.SseSendReq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SSE 接口
 */
@Slf4j
@RestController
@RequestMapping("/sys/sse")
@AllArgsConstructor
@Tag(name = "SSE接口")
public class SseController {
    /**
     * sse连接注册器
     */
    private final ISseRegister ISseRegister;
    /**
     * sse消息推送器
     */
    private final ISsePusher sseMessagePusher;

    /**
     * 消息推送事件
     */
    private Map<String, AbsSseEventHandler> sseEventHandlerMap;

    @Autowired
    public void sseEventHandlers(List<AbsSseEventHandler> sseEventHandlers) {
        sseEventHandlerMap = new HashMap<>(sseEventHandlers.size());
        for (AbsSseEventHandler sseEventHandler : sseEventHandlers) {
            sseEventHandlerMap.put(sseEventHandler.getEventName(), sseEventHandler);
        }
    }

    /**
     * 建立sse连接
     */
    @GetMapping(value = "/connect", produces = "text/event-stream;charset=UTF-8")
    @Operation(summary = "建立sse连接", description = "建立sse连接")
    public SseEmitter connect(HttpServletResponse response) {
        LoginUser loginUser = ScopedValueItem.LOGIN_USER.get();
        log.info("客户端 {} 请求建立 SSE 连接", loginUser.getUserName());

        // 设置响应头
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 创建 SseEmitter，设置5分钟超时
        SseEmitter emitter = new SseEmitter(1000 * 60 * 5L);
        ISseRegister.doRegister(loginUser.getUserName(), emitter);

        // 发送消息
        try {
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .data("连接建立成功，客户端ID: " + loginUser.getUserName())
                    .id(String.valueOf(System.currentTimeMillis())));

            log.info("客户端 {} SSE 连接建立成功", loginUser.getUserName());
            return emitter;
        } catch (IOException e) {
            log.error("客户端 {} SSE 连接建立失败: {}", loginUser.getUserName(), e.getMessage(), e);
            // 创建一个会立即报错的 emitter
            SseEmitter errorEmitter = new SseEmitter(0L);
            errorEmitter.completeWithError(e);
            return errorEmitter;
        }
    }

    @PostMapping(value = "/send")
    @Operation(summary = "发送消息", description = "发送消息")
    public R<?> send(@RequestBody SseSendReq sseSendReq) {
        LoginUser loginUser = ScopedValueItem.LOGIN_USER.get();
        SseMessage message = new SseMessage();
        message.setSource(loginUser.getUserName());
        message.setTo(sseSendReq.getTo());
        message.setEvent("chat");
        message.setData(sseSendReq.getMessage());

        sseMessagePusher.push(message);

        return R.success();
    }

    @GetMapping(value = "/listEvent")
    @Operation(summary = "查询消息推送事件", description = "查询消息推送事件")
    public List<Map<String, Object>> listEvent() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AbsSseEventHandler sseEventHandler : sseEventHandlerMap.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", sseEventHandler.getClass().getSimpleName());
            map.put("isEnabled", sseEventHandler.isEnabled());
            map.put("cronExpression", sseEventHandler.cronExpression());
            map.put("eventName", sseEventHandler.getEventName());
            map.put("isRunning", sseEventHandler.isRunning);
            map.put("taskId", sseEventHandler.taskId);

            list.add(map);
        }
        return list;
    }

    @GetMapping(value = "/startEvent")
    @Operation(summary = "启动消息推送事件", description = "启动消息推送事件")
    public R<String> startEvent(String eventName) {
        AbsSseEventHandler sseEventHandler = sseEventHandlerMap.get(eventName);
        if (sseEventHandler == null) {
            return R.error("事件【" + eventName + "】不存在");
        } else {
            sseEventHandler.start();
            return R.success("启动成功");
        }
    }

    @GetMapping(value = "/stopEvent")
    @Operation(summary = "停止消息推送事件", description = "停止消息推送事件")
    public R<String> stopEvent(String eventName) {
        AbsSseEventHandler sseEventHandler = sseEventHandlerMap.get(eventName);
        if (sseEventHandler == null) {
            return R.error("事件【" + eventName + "】不存在");
        } else {
            sseEventHandler.stop();
            return R.success("停止成功");
        }
    }
}
