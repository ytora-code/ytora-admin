package xyz.ytora.base.sse;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scheduler.timewheel.ITimeWheelScheduler;
import xyz.ytora.base.scheduler.timewheel.TimeWheelTask;
import xyz.ytora.base.sse.support.DefaultISseMessagePusher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SSE事件处理器
 */
@Slf4j
public abstract class AbsSseEventHandler {
    /**
     * 所有的SSE事件
     */
    private final static Map<String, AbsSseEventHandler> sseEventMap = new ConcurrentHashMap<>();

    /**
     * 定时任务
     */
    private ITimeWheelScheduler scheduler;

    /**
     * 消息推送器
     */
    private DefaultISseMessagePusher pusher;

    /**
     * 该SSE事件处理器在定时任务中的任务id
     */
    public String taskId;

    /**
     * 该SSE事件处理器是否正在运行
     */
    public volatile boolean isRunning = false;

    @Autowired
    public void setScheduler(ITimeWheelScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Autowired
    public void setPusher(DefaultISseMessagePusher pusher) {
        this.pusher = pusher;
    }

    /**
     * 启动定时任务
     */
    @PostConstruct
    public void start() {
        if (isEnabled()) {
            invoke();
            isRunning = true;
            log.info("SSE事件处理器已启动: {}", getEventName());
        } else {
            log.info("SSE事件处理器已禁用: {}", getEventName());
        }
    }

    /**
     * 停止定时任务
     */
    @PreDestroy
    public void stop() {
        if (taskId != null) {
            scheduler.cancelTask(taskId);
            taskId = null;
        }
        isRunning = false;
        log.info("SSE事件处理器已停止: {}", getEventName());
    }

    /**
     * 是否启用该处理器（默认启用）
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * 执行任务获取数据
     */
    public Object exec() {
        if (pusher.size() > 0) {
            log.info("执行任务并推送SSE数据：{}", getEventName());
            return doExec();
        }
        return null;
    }

    /**
     * 设置消息推送的周期
     */
    public abstract String cronExpression();

    /**
     * 得到事件名称
     */
    public abstract String getEventName();

    /**
     * 获取事件描述
     */
    public abstract String getEventDesc();

    /**
     * 获取将要推送的消息
     * @return 返回值就是将要推送的消息，返回null表示不推送
     */
    public abstract Object doExec();

    private void invoke() {
        String eventName = getEventName();
        AbsSseEventHandler handler = sseEventMap.get(eventName);
        if (handler != null) {
            throw new BaseException("名称为{}的SSE事件已注册", eventName);
        }
        sseEventMap.put(eventName, this);

        SseMessage message = new SseMessage()
                .setEvent(eventName)
                .setSource("SYSTEM")
                .setTo(null) //null 表示给所有人发送消息
                .setType(1);

        //将任务注册进时间轮
        TimeWheelTask task = new TimeWheelTask(cronExpression(), () -> {
            if (isRunning) {
                Object result = exec();
                if (result != null) {
                    message.setData(result);
                    pusher.push(message);
                }
            }
        });

        this.taskId = scheduler.addTask(task);
    }
}
