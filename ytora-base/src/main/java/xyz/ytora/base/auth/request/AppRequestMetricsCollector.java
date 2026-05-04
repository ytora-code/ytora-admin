package xyz.ytora.base.auth.request;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ytora.base.auth.LoginUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 应用请求监控采集器。
 * <p>
 * 该组件只关注应用自身的 HTTP 请求维度：
 * <br/>
 * 1. 当前正在处理的请求数；
 * <br/>
 * 2. 历史峰值并发请求数；
 * <br/>
 * 3. 平均请求耗时；
 * <br/>
 * 4. 历史最慢请求 Top 10。
 * <p>
 * 这里的数据全部保存在内存中，应用重启后会自然清空，
 * 这符合驾驶舱监控与排障分析的使用场景。
 */
@Component
public class AppRequestMetricsCollector {

    /**
     * 最慢请求保留数量。
     */
    private static final int TOP_SLOW_REQUEST_SIZE = 10;

    /**
     * 当前活跃请求数。
     */
    private final AtomicInteger activeRequestCount = new AtomicInteger();

    /**
     * 历史峰值活跃请求数。
     */
    private final AtomicInteger peakRequestCount = new AtomicInteger();

    /**
     * 累计已完成请求数。
     */
    private final LongAdder totalRequestCount = new LongAdder();

    /**
     * 累计总耗时，用于计算平均值。
     */
    private final LongAdder totalDurationMs = new LongAdder();

    /**
     * 历史最慢请求列表。
     * <p>
     * 由于容量固定且数量极小，使用同步块维护比引入复杂堆结构更直接，也更容易保证可读性。
     */
    private final List<AppRequestRecord> topSlowRequests = new ArrayList<>();

    /**
     * 请求进入时创建跟踪对象，并更新并发计数。
     */
    public RequestTrack begin(HttpServletRequest request) {
        int current = activeRequestCount.incrementAndGet();
        updatePeak(current);

        return new RequestTrack(
                System.currentTimeMillis(),
                System.nanoTime(),
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString(),
                resolveClientIp(request)
        );
    }

    /**
     * 请求结束时更新汇总指标与最慢请求列表。
     */
    public void complete(RequestTrack track, int status, Throwable throwable, LoginUser loginUser) {
        long durationMs = Math.max(0L, (System.nanoTime() - track.startNanoTime()) / 1_000_000L);

        totalRequestCount.increment();
        totalDurationMs.add(durationMs);
        activeRequestCount.decrementAndGet();

        AppRequestRecord record = new AppRequestRecord(
                track.startTime(),
                track.method(),
                track.path(),
                track.query(),
                status,
                durationMs,
                track.clientIp(),
                loginUser == null ? null : loginUser.getId(),
                loginUser == null ? null : loginUser.getUserName(),
                throwable != null
        );

        addSlowRequest(record);
    }

    /**
     * 获取当前请求监控总览。
     */
    public AppRequestOverview overview() {
        long total = totalRequestCount.sum();
        double average = total == 0L ? 0D : round(totalDurationMs.sum() * 1D / total);

        synchronized (topSlowRequests) {
            return new AppRequestOverview(
                    activeRequestCount.get(),
                    peakRequestCount.get(),
                    total,
                    average,
                    List.copyOf(topSlowRequests)
            );
        }
    }

    /**
     * 更新峰值并发。
     */
    private void updatePeak(int current) {
        peakRequestCount.accumulateAndGet(current, Math::max);
    }

    /**
     * 维护最慢请求 TopN。
     */
    private void addSlowRequest(AppRequestRecord record) {
        synchronized (topSlowRequests) {
            topSlowRequests.add(record);
            topSlowRequests.sort(Comparator.comparingLong(AppRequestRecord::durationMs).reversed());
            if (topSlowRequests.size() > TOP_SLOW_REQUEST_SIZE) {
                topSlowRequests.removeLast();
            }
        }
    }

    /**
     * 尽量获取真实客户端 IP。
     */
    private String resolveClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }

        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isBlank()) {
            return realIp.trim();
        }

        return Objects.toString(request.getRemoteAddr(), null);
    }

    /**
     * 保留两位小数。
     */
    private double round(double value) {
        return Math.round(value * 100D) / 100D;
    }

    /**
     * 单次请求的运行期跟踪对象。
     */
    @RequiredArgsConstructor
    public static final class RequestTrack {
        private final long startTime;
        private final long startNanoTime;
        private final String method;
        private final String path;
        private final String query;
        private final String clientIp;

        public long startTime() {
            return startTime;
        }

        public long startNanoTime() {
            return startNanoTime;
        }

        public String method() {
            return method;
        }

        public String path() {
            return path;
        }

        public String query() {
            return query;
        }

        public String clientIp() {
            return clientIp;
        }
    }
}
