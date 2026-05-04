package xyz.ytora.core.monitor.sse.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.sse.AbsSseEventHandler;
import xyz.ytora.base.sse.ISsePusher;
import xyz.ytora.base.sse.ISseRegister;
import xyz.ytora.base.sse.SseMessage;
import xyz.ytora.core.monitor.sse.logic.SseLogic;
import xyz.ytora.core.monitor.sse.model.data.AppSseMetricsData;
import xyz.ytora.core.monitor.sse.model.param.SseSendParam;

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
@RequestMapping("/sse")
@AllArgsConstructor
@Tag(name = "SSE接口")
public class SseApi {

    /**
     * SSE逻辑
     */
    private final SseLogic sseLogic;

    /**
     * 建立sse连接
     */
    @GetMapping(value = "/connect", produces = "text/event-stream;charset=UTF-8")
    @Operation(summary = "建立sse连接", description = "建立sse连接")
    public SseEmitter connect() {
        return sseLogic.connect();
    }

    @PostMapping(value = "/send")
    @Operation(summary = "发送消息", description = "发送消息")
    public R<?> send(@RequestBody SseSendParam param) {
        sseLogic.send(param);
        return R.success();
    }

    @GetMapping(value = "/listEvent")
    @Operation(summary = "查询消息推送事件", description = "查询消息推送事件")
    public List<Map<String, Object>> listEvent() {
        return sseLogic.listEvent();
    }

    @GetMapping(value = "/startEvent")
    @Operation(summary = "启动消息推送事件", description = "启动消息推送事件")
    public String startEvent(String eventName) {
        return sseLogic.startEvent(eventName);
    }

    @GetMapping(value = "/stopEvent")
    @Operation(summary = "停止消息推送事件", description = "停止消息推送事件")
    public String stopEvent(String eventName) {
        return sseLogic.stopEvent(eventName);
    }

    /**
     * 查询SSE客户端
     */
    @GetMapping("/listClient")
    @Operation(summary = "查询SSE连接客户端", description = "查询SSE连接客户端")
    public AppSseMetricsData listClient() {
        return sseLogic.getSseMetrics();
    }

    /**
     * SSE客户端订阅事件
     */
    @GetMapping("/subscribe")
    @Operation(summary = "SSE客户端订阅事件", description = "SSE客户端订阅事件")
    public R<?> subscribe(@RequestParam String eventName) {
        sseLogic.subscribe(eventName);
        return R.success();
    }

    /**
     * SSE客户端取消事件订阅
     */
    @GetMapping("/unSubscribe")
    @Operation(summary = "SSE客户端取消事件订阅", description = "SSE客户端取消事件订阅")
    public R<?> unSubscribe(@RequestParam String eventName) {
        sseLogic.unSubscribe(eventName);
        return R.success();
    }

}
