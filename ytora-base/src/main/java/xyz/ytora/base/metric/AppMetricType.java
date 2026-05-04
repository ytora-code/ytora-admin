package xyz.ytora.base.metric;

/**
 * 业务埋点指标类型。
 */
public enum AppMetricType {
    /**
     * 计数器。
     */
    COUNTER,
    /**
     * 瞬时数值。
     */
    GAUGE,
    /**
     * 耗时/调用统计。
     */
    TIMER
}
