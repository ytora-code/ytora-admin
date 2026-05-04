package xyz.ytora.base.metric.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.ytora.base.metric.AppMetricReporter;
import xyz.ytora.base.metric.anno.AppMetric;

/**
 * 业务埋点切面。
 * <p>
 * 使用注解时，无需在业务代码里手工计算耗时，
 * 该切面会自动补齐“调用次数 + 耗时 + 成功/失败次数”统计。
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AppMetricAspect {

    private final AppMetricReporter appMetricReporter;

    @Around("@annotation(appMetric)")
    public Object around(ProceedingJoinPoint joinPoint, AppMetric appMetric) throws Throwable {
        long start = System.nanoTime();
        boolean success = false;
        try {
            Object result = joinPoint.proceed();
            success = true;
            return result;
        } finally {
            long durationMs = Math.max(0L, (System.nanoTime() - start) / 1_000_000L);
            appMetricReporter.recordInvocation(appMetric.value(), appMetric.description(), durationMs, success);
        }
    }
}
