package xyz.ytora.core.sqlux.monitor;

import org.springframework.stereotype.Component;
import xyz.ytora.sqlux.interceptor.SqlExecutionContext;
import xyz.ytora.sqlux.translate.SqlResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * SQL 监控采集器。
 * <p>
 * 该组件只负责在内存中聚合 SQL 执行统计，
 * 不关心具体日志输出，也不依赖业务模块。
 * <br/>
 * 设计目标：
 * <br/>
 * 1. 为数据库监控模块提供可直接读取的统计快照；
 * <br/>
 * 2. 对 SQL 执行链路零侵入，仅通过拦截器写入；
 * <br/>
 * 3. 存储量受控，避免慢 SQL 记录无限增长。
 */
@Component
public class SqlMetricsCollector {

    /**
     * 最近慢 SQL 最大保留数量。
     */
    private static final int MAX_SLOW_SQL_SIZE = 20;

    /**
     * 热点慢 SQL 排行保留数量。
     */
    private static final int HOT_SLOW_SQL_SIZE = 10;

    /**
     * 热度半衰期，单位分钟。
     * <p>
     * 每经过一个半衰期，慢 SQL 的时间新鲜度衰减为原来的一半。
     */
    private static final double HOT_SCORE_HALF_LIFE_MINUTES = 120D;

    /**
     * 平均耗时权重。
     */
    private static final double AVG_DURATION_WEIGHT = 0.5D;

    /**
     * 最大耗时权重。
     */
    private static final double MAX_DURATION_WEIGHT = 0.3D;

    /**
     * 命中次数权重。
     */
    private static final double COUNT_WEIGHT = 0.2D;

    /**
     * 累计 SQL 总数。
     */
    private final LongAdder totalSqlCount = new LongAdder();

    /**
     * 累计成功 SQL 数。
     */
    private final LongAdder successSqlCount = new LongAdder();

    /**
     * 累计失败 SQL 数。
     */
    private final LongAdder failureSqlCount = new LongAdder();

    /**
     * 累计 SQL 总耗时。
     */
    private final LongAdder totalDurationMs = new LongAdder();

    /**
     * 累计慢 SQL 数。
     */
    private final LongAdder slowSqlCount = new LongAdder();

    /**
     * 历史最大耗时。
     */
    private final AtomicLong maxDurationMs = new AtomicLong();

    /**
     * 最近慢 SQL 队列，头部为最新。
     */
    private final ConcurrentLinkedDeque<SlowSqlRecord> recentSlowSqls = new ConcurrentLinkedDeque<>();

    /**
     * 按 SQL 指纹聚合的慢 SQL 统计桶。
     */
    private final Map<String, HotSlowSqlBucket> hotSlowSqlBuckets = new ConcurrentHashMap<>();

    /**
     * 记录一次 SQL 执行结果。
     *
     * @param context          SQL执行上下文
     * @param slowSqlThreshold 慢SQL阈值(毫秒)
     * @param success          是否成功
     */
    public void record(SqlExecutionContext context, long slowSqlThreshold, boolean success) {
        long elapsedMillis = Math.max(0L, context.getElapsedMillis());

        totalSqlCount.increment();
        totalDurationMs.add(elapsedMillis);
        maxDurationMs.accumulateAndGet(elapsedMillis, Math::max);

        if (success) {
            successSqlCount.increment();
        } else {
            failureSqlCount.increment();
        }

        if (elapsedMillis >= slowSqlThreshold) {
            slowSqlCount.increment();
            SlowSqlRecord slowSqlRecord = buildSlowSqlRecord(context, elapsedMillis, success);
            recentSlowSqls.addFirst(slowSqlRecord);
            while (recentSlowSqls.size() > MAX_SLOW_SQL_SIZE) {
                recentSlowSqls.pollLast();
            }
            recordHotSlowSql(slowSqlRecord);
        }
    }

    /**
     * 生成快照。
     */
    public SqlMetricsSnapshot snapshot(long slowSqlThreshold) {
        long total = totalSqlCount.sum();
        double average = total == 0L ? 0D : round(totalDurationMs.sum() * 1D / total);

        return new SqlMetricsSnapshot(
                total,
                successSqlCount.sum(),
                failureSqlCount.sum(),
                average,
                maxDurationMs.get(),
                slowSqlCount.sum(),
                slowSqlThreshold,
                List.copyOf(new ArrayList<>(recentSlowSqls)),
                buildHotSlowSqlRanking()
        );
    }

    /**
     * 构建慢 SQL 记录。
     */
    private SlowSqlRecord buildSlowSqlRecord(SqlExecutionContext context, long elapsedMillis, boolean success) {
        SqlResult sqlResult = context.getSqlResult();
        Throwable exception = context.getException();

        return new SlowSqlRecord(
                System.currentTimeMillis(),
                context.getSqlType().name(),
                elapsedMillis,
                shrink(sqlResult.getSql(), 4000),
                shrink(formatParams(sqlResult.getParams()), 2000),
                exception == null ? null : exception.getClass().getName(),
                exception == null ? null : shrink(exception.getMessage(), 1000),
                success
        );
    }

    /**
     * 记录热点慢 SQL 聚合桶。
     */
    private void recordHotSlowSql(SlowSqlRecord record) {
        String fingerprint = fingerprint(record.sql());
        HotSlowSqlBucket bucket = hotSlowSqlBuckets.computeIfAbsent(
                fingerprint,
                key -> new HotSlowSqlBucket(key, record.sqlType(), record.sql(), record.paramsText(), record.timestamp(), record.elapsedMillis())
        );
        bucket.record(record);
    }

    /**
     * 生成热点慢 SQL 排行。
     * <p>
     * 排行分数同时考虑：
     * <br/>
     * 1. 平均耗时；
     * <br/>
     * 2. 最大耗时；
     * <br/>
     * 3. 出现频率；
     * <br/>
     * 4. 最近一次出现时间。
     */
    private List<HotSlowSqlRecord> buildHotSlowSqlRanking() {
        return hotSlowSqlBuckets.values().stream()
                .map(HotSlowSqlBucket::snapshot)
                .sorted(Comparator.comparingDouble(HotSlowSqlRecord::score).reversed())
                .limit(HOT_SLOW_SQL_SIZE)
                .toList();
    }

    /**
     * 生成 SQL 指纹。
     * <p>
     * 该方法会做轻量归一化处理，把明显的常量与多余空白折叠掉，
     * 让结构相同、参数不同的 SQL 能归并到同一个桶里。
     */
    private String fingerprint(String sql) {
        if (sql == null || sql.isBlank()) {
            return "EMPTY_SQL";
        }

        String normalized = sql.toLowerCase()
                .replaceAll("'[^']*'", "?")
                .replaceAll("\\b\\d+\\b", "?")
                .replaceAll("\\s+", " ")
                .trim();

        return shrink(normalized, 2000);
    }

    /**
     * 参数转文本。
     */
    private String formatParams(List<Object> params) {
        return params == null ? "[]" : params.toString();
    }

    /**
     * 截断长文本，避免内存占用失控。
     */
    private String shrink(String text, int maxLen) {
        if (text == null) {
            return null;
        }
        if (text.length() <= maxLen) {
            return text;
        }
        return text.substring(0, maxLen) + "...";
    }

    /**
     * 保留两位小数。
     */
    private double round(double value) {
        return Math.round(value * 100D) / 100D;
    }

    /**
     * 热点慢 SQL 聚合桶。
     */
    private static final class HotSlowSqlBucket {
        private final String fingerprint;
        private final String sqlType;
        private final LongAdder count = new LongAdder();
        private final LongAdder totalDurationMs = new LongAdder();
        private final AtomicLong maxDurationMs = new AtomicLong();
        private volatile String sampleSql;
        private volatile String sampleParamsText;
        private volatile long firstSeenTime;
        private volatile long lastSeenTime;

        private HotSlowSqlBucket(String fingerprint,
                                 String sqlType,
                                 String sampleSql,
                                 String sampleParamsText,
                                 long firstSeenTime,
                                 long initialDurationMs) {
            this.fingerprint = fingerprint;
            this.sqlType = sqlType;
            this.sampleSql = sampleSql;
            this.sampleParamsText = sampleParamsText;
            this.firstSeenTime = firstSeenTime;
            this.lastSeenTime = firstSeenTime;
            this.maxDurationMs.set(initialDurationMs);
        }

        private synchronized void record(SlowSqlRecord record) {
            count.increment();
            totalDurationMs.add(record.elapsedMillis());
            maxDurationMs.accumulateAndGet(record.elapsedMillis(), Math::max);
            sampleSql = record.sql();
            sampleParamsText = record.paramsText();
            lastSeenTime = Math.max(lastSeenTime, record.timestamp());
            firstSeenTime = Math.min(firstSeenTime, record.timestamp());
        }

        private HotSlowSqlRecord snapshot() {
            long currentCount = count.sum();
            double averageDurationMs = currentCount == 0L ? 0D : totalDurationMs.sum() * 1D / currentCount;
            long maxDuration = maxDurationMs.get();
            double score = calculateScore(averageDurationMs, maxDuration, currentCount, lastSeenTime);

            return new HotSlowSqlRecord(
                    fingerprint,
                    sqlType,
                    sampleSql,
                    sampleParamsText,
                    currentCount,
                    Math.round(averageDurationMs * 100D) / 100D,
                    maxDuration,
                    firstSeenTime,
                    lastSeenTime,
                    Math.round(score * 100D) / 100D
            );
        }

        /**
         * 计算热点分数。
         * <p>
         * 公式：
         * <br/>
         * 热度基础值 = 0.5*log(1+avg) + 0.3*log(1+max) + 0.2*log(1+count)
         * <br/>
         * 最终得分 = 热度基础值 * 时间衰减因子
         * <br/>
         * 时间衰减采用指数衰减，可保证：
         * <br/>
         * 1. 很老的极慢 SQL 不会永久霸榜；
         * <br/>
         * 2. 新出现但略慢的 SQL 不会瞬间把老热点直接挤掉；
         * <br/>
         * 3. 最近持续出现的慢 SQL 会逐步获得更高关注度。
         */
        private double calculateScore(double averageDurationMs, long maxDurationMs, long count, long lastSeenTime) {
            double severity = AVG_DURATION_WEIGHT * Math.log1p(Math.max(0D, averageDurationMs))
                    + MAX_DURATION_WEIGHT * Math.log1p(Math.max(0L, maxDurationMs))
                    + COUNT_WEIGHT * Math.log1p(Math.max(0L, count));

            double ageMinutes = Math.max(0D, (System.currentTimeMillis() - lastSeenTime) / 60000D);
            double lambda = Math.log(2D) / HOT_SCORE_HALF_LIFE_MINUTES;
            double freshness = Math.exp(-lambda * ageMinutes);

            return severity * freshness;
        }
    }
}
