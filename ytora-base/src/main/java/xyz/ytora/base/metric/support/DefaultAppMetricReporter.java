package xyz.ytora.base.metric.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.metric.AppMetricReporter;
import xyz.ytora.base.metric.AppMetricSnapshot;
import xyz.ytora.base.metric.AppMetricType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * 默认业务埋点上报器。
 * <p>
 * 当前实现为纯内存聚合，优点是：
 * <br/>
 * 1. 对现有系统零依赖；
 * <br/>
 * 2. 便于快速开始埋点；
 * <br/>
 * 3. 可作为后续接入 Micrometer / Prometheus 前的过渡方案。
 */
@Component
public class DefaultAppMetricReporter implements AppMetricReporter {

    /**
     * 全部业务指标桶。
     */
    private final Map<String, MetricBucket> metrics = new ConcurrentHashMap<>();

    @Override
    public void increment(String name, String description) {
        increment(name, 1L, description);
    }

    @Override
    public void increment(String name, long delta, String description) {
        MetricBucket bucket = metrics.computeIfAbsent(name, key -> new MetricBucket(name, description, AppMetricType.COUNTER));
        bucket.increment(delta, description);
    }

    @Override
    public void gauge(String name, double value, String description) {
        MetricBucket bucket = metrics.computeIfAbsent(name, key -> new MetricBucket(name, description, AppMetricType.GAUGE));
        bucket.gauge(value, description);
    }

    @Override
    public void recordDuration(String name, long durationMs, String description) {
        MetricBucket bucket = metrics.computeIfAbsent(name, key -> new MetricBucket(name, description, AppMetricType.TIMER));
        bucket.recordDuration(durationMs, description);
    }

    @Override
    public void recordInvocation(String name, String description, long durationMs, boolean success) {
        MetricBucket bucket = metrics.computeIfAbsent(name, key -> new MetricBucket(name, description, AppMetricType.TIMER));
        bucket.recordInvocation(durationMs, success, description);
    }

    @Override
    public List<AppMetricSnapshot> listSnapshots() {
        return metrics.values().stream()
                .map(MetricBucket::snapshot)
                .sorted(Comparator.comparingLong(AppMetricSnapshot::lastUpdatedTime).reversed())
                .toList();
    }

    /**
     * 单个业务指标桶。
     * <p>
     * 统一承载 counter/gauge/timer 三类指标，避免为每种指标再维护一套并行结构。
     */
    private static final class MetricBucket {
        private final String name;
        private final AppMetricType type;
        private final AtomicReference<String> description;
        private final LongAdder counterValue = new LongAdder();
        private final AtomicReference<Double> gaugeValue = new AtomicReference<>();
        private final LongAdder totalCount = new LongAdder();
        private final LongAdder successCount = new LongAdder();
        private final LongAdder failureCount = new LongAdder();
        private final LongAdder totalDurationMs = new LongAdder();
        private final AtomicLong maxDurationMs = new AtomicLong();
        private final AtomicLong lastDurationMs = new AtomicLong();
        private final AtomicLong lastUpdatedTime = new AtomicLong();

        private MetricBucket(String name, String description, AppMetricType type) {
            this.name = name;
            this.description = new AtomicReference<>(description);
            this.type = type;
            this.lastUpdatedTime.set(System.currentTimeMillis());
        }

        private void increment(long delta, String description) {
            refreshDescription(description);
            counterValue.add(Math.max(0L, delta));
            lastUpdatedTime.set(System.currentTimeMillis());
        }

        private void gauge(double value, String description) {
            refreshDescription(description);
            gaugeValue.set(value);
            lastUpdatedTime.set(System.currentTimeMillis());
        }

        private void recordDuration(long durationMs, String description) {
            refreshDescription(description);
            long safeDuration = Math.max(0L, durationMs);
            totalCount.increment();
            totalDurationMs.add(safeDuration);
            lastDurationMs.set(safeDuration);
            maxDurationMs.accumulateAndGet(safeDuration, Math::max);
            lastUpdatedTime.set(System.currentTimeMillis());
        }

        private void recordInvocation(long durationMs, boolean success, String description) {
            refreshDescription(description);
            long safeDuration = Math.max(0L, durationMs);
            totalCount.increment();
            totalDurationMs.add(safeDuration);
            lastDurationMs.set(safeDuration);
            maxDurationMs.accumulateAndGet(safeDuration, Math::max);
            if (success) {
                successCount.increment();
            } else {
                failureCount.increment();
            }
            lastUpdatedTime.set(System.currentTimeMillis());
        }

        private AppMetricSnapshot snapshot() {
            long total = totalCount.sum();
            long totalDuration = totalDurationMs.sum();
            double averageDuration = total == 0L ? 0D : Math.round(totalDuration * 100D / total) / 100D;

            return new AppMetricSnapshot(
                    name,
                    description.get(),
                    type,
                    counterValue.sum(),
                    gaugeValue.get(),
                    total,
                    successCount.sum(),
                    failureCount.sum(),
                    totalDuration,
                    averageDuration,
                    maxDurationMs.get(),
                    lastDurationMs.get(),
                    lastUpdatedTime.get()
            );
        }

        private void refreshDescription(String latestDescription) {
            if (latestDescription != null && !latestDescription.isBlank()) {
                description.set(latestDescription);
            }
        }
    }
}
