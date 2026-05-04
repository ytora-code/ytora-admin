package xyz.ytora.base.auth.request;

import java.util.List;

/**
 * 请求监控总览快照。
 *
 * @param activeRequestCount 当前正在处理的请求数
 * @param peakRequestCount   历史峰值并发请求数
 * @param totalRequestCount  累计已完成请求数
 * @param averageDurationMs  平均请求耗时
 * @param topSlowRequests    历史最慢请求 TopN
 */
public record AppRequestOverview(
        int activeRequestCount,
        int peakRequestCount,
        long totalRequestCount,
        double averageDurationMs,
        List<AppRequestRecord> topSlowRequests
) {
}
