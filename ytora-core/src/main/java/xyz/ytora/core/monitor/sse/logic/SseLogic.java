package xyz.ytora.core.monitor.sse.logic;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.sse.*;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.core.monitor.sse.model.data.AppSseMetricsData;
import xyz.ytora.core.monitor.sse.model.data.ClientItem;
import xyz.ytora.core.monitor.sse.model.data.PushMessageItem;
import xyz.ytora.core.monitor.sse.model.param.SseSendParam;
import xyz.ytora.toolkit.text.Strs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 描述
 *
 * <p>说明</p>
 *
 * @author ytora 
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SseLogic {

    private final ISseMetrics sseMetrics;

    /**
     * sse注册器
     */
    private final ISseRegister sseRegister;

    /**
     * sse消息推送器
     */
    private final ISsePusher ssePusher;

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
     * 建立SSE连接
     * @return SSE连接对象
     */
    public SseEmitter connect() {
        HttpServletResponse response = HttpUtil.getResp();
        if (response == null) {
            throw new IllegalStateException("当前处于非web环境，无法连接SSE连接");
        }

        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
        log.info("客户端 {} 请求建立 SSE 连接", loginUser.getUserName());

        // 设置响应头
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 创建 SseEmitter，设置5分钟超时
        SseEmitter emitter = new SseEmitter(1000 * 60 * 5L);
        sseRegister.doRegister(loginUser.getUserName(), emitter);

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

    /**
     * 发送消息
     * @param param 消息参数
     */
    public void send(SseSendParam param) {
        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
        SseMessage message = new SseMessage();
        message.setSource(loginUser.getUserName());
        message.setTo(param.getTo());
        message.setEvent(param.getEventName());
        message.setData(param.getMessage());

        ssePusher.push(message);
    }

    /**
     * 查询消息推送事件
     */
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

    /**
     * 启动指定事件的SSE消息推送
     *
     * @param eventName 事件名称
     */
    public String startEvent(String eventName) {
        AbsSseEventHandler sseEventHandler = sseEventHandlerMap.get(eventName);
        if (sseEventHandler == null) {
            return "事件【" + eventName + "】不存在";
        } else {
            sseEventHandler.start();
            return "启动成功";
        }
    }

    /**
     * 停止指定事件的SSE消息推送
     *
     * @param eventName 事件名称
     */
    public String stopEvent(String eventName) {
        AbsSseEventHandler sseEventHandler = sseEventHandlerMap.get(eventName);
        if (sseEventHandler == null) {
            return "事件【" + eventName + "】不存在";
        } else {
            sseEventHandler.stop();
            return "停止成功";
        }
    }

    /**
     * 获取 SSE 连接状态。
     */
    public AppSseMetricsData getSseMetrics() {
        Map<String, SseClientPushStats> pushStatsMap = sseMetrics
                .listClientPushStats()
                .stream()
                .collect(Collectors.toMap(SseClientPushStats::clientId, item -> item, (left, right) -> left));

        List<ClientItem> clients = sseRegister
                .listClientInfos()
                .stream()
                .map(clientInfo -> buildSseClientItem(clientInfo, pushStatsMap.get(clientInfo.clientId())))
                .toList();

        return AppSseMetricsData.builder()
                .connectionCount(sseRegister.size())
                .clients(clients)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 构建 SSE 客户端 DTO。
     */
    private ClientItem buildSseClientItem(SseClientInfo clientInfo, SseClientPushStats pushStats) {
        List<PushMessageItem> recentMessages = pushStats == null
                ? List.of()
                : pushStats.recentMessages().stream().map(this::buildPushMessageItem).toList();

        return ClientItem.builder()
                .clientId(clientInfo.clientId())
                .requestUri(clientInfo.requestUri())
                .clientIp(clientInfo.clientIp())
                .userAgent(clientInfo.userAgent())
                .connectedAt(clientInfo.connectedAt())
                .totalPayloadBytes(pushStats == null ? 0L : pushStats.totalPayloadBytes())
                .recentMessages(recentMessages)
                .build();
    }

    /**
     * 构建客户端最近推送消息 DTO。
     */
    private PushMessageItem buildPushMessageItem(SsePushMessageRecord record) {
        return PushMessageItem.builder()
                .timestamp(record.timestamp())
                .event(record.event())
                .messageId(record.messageId())
                .payload(record.payload())
                .payloadBytes(record.payloadBytes())
                .build();
    }

    /**
     * SSE客户端订阅事件
     */
    public void subscribe(String eventName) {
        if (Strs.isEmpty(eventName)) {
            return;
        }
        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
        sseRegister.subscribe(loginUser.getUserName(), eventName);
    }

    /**
     * SSE客户端取消事件订阅
     */
    public void unSubscribe(String eventName) {
        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
        sseRegister.unSubscribe(loginUser.getUserName(), eventName);
    }
}
