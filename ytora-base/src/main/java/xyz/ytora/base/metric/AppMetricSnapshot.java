package xyz.ytora.base.metric;

/**
 * 业务埋点快照。
 *
 * @param name            指标名称
 * @param description     指标说明
 * @param type            指标类型
 * @param counterValue    当前计数值
 * @param gaugeValue      当前瞬时值
 * @param totalCount      总调用次数
 * @param successCount    成功次数
 * @param failureCount    失败次数
 * @param totalDurationMs 总耗时
 * @param averageDurationMs 平均耗时
 * @param maxDurationMs   最大耗时
 * @param lastDurationMs  最近一次耗时
 * @param lastUpdatedTime 最近更新时间
 */
public record AppMetricSnapshot(
        String name,
        String description,
        AppMetricType type,
        long counterValue,
        Double gaugeValue,
        long totalCount,
        long successCount,
        long failureCount,
        long totalDurationMs,
        double averageDurationMs,
        long maxDurationMs,
        long lastDurationMs,
        long lastUpdatedTime
) {
}
