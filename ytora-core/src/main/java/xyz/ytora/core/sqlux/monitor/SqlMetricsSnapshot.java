package xyz.ytora.core.sqlux.monitor;

import java.util.List;

/**
 * SQL 监控快照。
 *
 * @param totalSqlCount     累计SQL总数
 * @param successSqlCount   累计成功SQL数
 * @param failureSqlCount   累计失败SQL数
 * @param averageDurationMs 平均耗时
 * @param maxDurationMs     最大耗时
 * @param slowSqlCount      累计慢SQL数量
 * @param slowSqlThreshold  慢SQL阈值
 * @param recentSlowSqls    最近慢SQL列表
 * @param hotSlowSqls       热点慢SQL排行
 */
public record SqlMetricsSnapshot(
        long totalSqlCount,
        long successSqlCount,
        long failureSqlCount,
        double averageDurationMs,
        long maxDurationMs,
        long slowSqlCount,
        long slowSqlThreshold,
        List<SlowSqlRecord> recentSlowSqls,
        List<HotSlowSqlRecord> hotSlowSqls
) {
}
