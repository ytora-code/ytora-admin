package xyz.ytora.base.metric;

import java.util.List;

/**
 * 业务埋点上报器。
 * <p>
 * 使用方式分两类：
 * <br/>
 * 1. 显式调用：适合在业务关键节点手工上报计数、耗时、瞬时值；
 * <br/>
 * 2. 注解+AOP：适合为方法统一统计调用次数与耗时。
 * <p>
 * 示例：
 * <pre>{@code
 * appMetricReporter.increment("user.login.success", "用户登录成功次数");
 * appMetricReporter.gauge("queue.pending", pendingCount, "当前队列积压数");
 * appMetricReporter.recordDuration("report.export", costMs, "报表导出耗时");
 * }</pre>
 */
public interface AppMetricReporter {

    /**
     * 计数器 +1。
     */
    void increment(String name, String description);

    /**
     * 计数器增加指定值。
     */
    void increment(String name, long delta, String description);

    /**
     * 设置瞬时值。
     */
    void gauge(String name, double value, String description);

    /**
     * 记录耗时。
     */
    void recordDuration(String name, long durationMs, String description);

    /**
     * 记录一次方法调用。
     */
    void recordInvocation(String name, String description, long durationMs, boolean success);

    /**
     * 获取全部指标快照。
     */
    List<AppMetricSnapshot> listSnapshots();
}
