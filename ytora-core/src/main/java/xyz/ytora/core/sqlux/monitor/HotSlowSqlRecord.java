package xyz.ytora.core.sqlux.monitor;

/**
 * 热点慢 SQL 记录。
 * <p>
 * 该对象不是单次执行事件，而是按 SQL 指纹聚合后的排行项，
 * 适合用于“当前最值得关注的慢 SQL”榜单。
 *
 * @param fingerprint       SQL指纹
 * @param sqlType           SQL类型
 * @param sampleSql         代表性SQL文本
 * @param sampleParamsText  最近一次参数文本
 * @param count             命中次数
 * @param averageDurationMs 平均耗时
 * @param maxDurationMs     最大耗时
 * @param firstSeenTime     首次出现时间
 * @param lastSeenTime      最近出现时间
 * @param score             综合评分
 */
public record HotSlowSqlRecord(
        String fingerprint,
        String sqlType,
        String sampleSql,
        String sampleParamsText,
        long count,
        double averageDurationMs,
        long maxDurationMs,
        long firstSeenTime,
        long lastSeenTime,
        double score
) {
}
