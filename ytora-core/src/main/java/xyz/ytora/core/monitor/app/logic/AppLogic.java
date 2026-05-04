package xyz.ytora.core.monitor.app.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.CompositeHealth;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.request.AppRequestMetricsCollector;
import xyz.ytora.base.auth.request.AppRequestOverview;
import xyz.ytora.base.auth.request.AppRequestRecord;
import xyz.ytora.base.exception.error.AppErrorBuffer;
import xyz.ytora.base.exception.error.AppErrorRecord;
import xyz.ytora.base.metric.AppMetricReporter;
import xyz.ytora.base.metric.AppMetricSnapshot;
import xyz.ytora.base.sse.ISseMetrics;
import xyz.ytora.base.sse.ISseRegister;
import xyz.ytora.base.sse.SseClientPushStats;
import xyz.ytora.base.sse.SseClientInfo;
import xyz.ytora.base.sse.SsePushMessageRecord;
import xyz.ytora.core.monitor.app.model.data.AppBasicInfoData;
import xyz.ytora.core.monitor.app.model.data.AppBusinessMetricsData;
import xyz.ytora.core.monitor.app.model.data.AppErrorMetricsData;
import xyz.ytora.core.monitor.app.model.data.AppOverviewData;
import xyz.ytora.core.monitor.app.model.data.AppRequestMetricsData;
import xyz.ytora.core.monitor.sse.model.data.AppSseMetricsData;
import xyz.ytora.core.monitor.app.model.data.AppStatusData;
import xyz.ytora.core.monitor.sse.model.data.ClientItem;
import xyz.ytora.core.monitor.sse.model.data.PushMessageItem;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by YT on 2026/4/24
 * <br/>
 * Spring Boot 应用监控逻辑。
 * <p>
 * 该模块只关注“当前 Spring Boot 应用本身”的状态，
 * 不重复承担 OS 与 JVM 层面的职责。
 */
@Service
@RequiredArgsConstructor
public class AppLogic {

    private final Environment environment;
    private final ApplicationContext applicationContext;
    private final ApplicationAvailability applicationAvailability;
    private final HealthEndpoint healthEndpoint;
    private final ISseRegister sseRegister;

    private final AppRequestMetricsCollector appRequestMetricsCollector;
    private final AppErrorBuffer appErrorBuffer;
    private final AppMetricReporter appMetricReporter;

    /**
     * JVM 运行时入口。
     * <p>
     * 应用启动时间和运行时长从这里读取比自己维护更可靠。
     */
    private final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

    /**
     * 获取应用基础信息。
     */
    public AppBasicInfoData getBasicInfo() {
        return AppBasicInfoData.builder()
                .applicationName(environment.getProperty("spring.application.name"))
                .applicationVersion(environment.getProperty("ytora.version"))
                .contextPath(environment.getProperty("server.servlet.context-path"))
                .port(environment.getProperty("server.port"))
                .activeProfiles(Arrays.asList(environment.getActiveProfiles()))
                .startTime(runtimeMXBean.getStartTime())
                .pid(runtimeMXBean.getPid())
                .build();
    }

    /**
     * 获取应用总览。
     * <p>
     * 这个接口面向驾驶舱首页，只返回最常用的摘要数据，
     * 目的是减少前端为了展示几张卡片而发起过多请求。
     */
    public AppOverviewData getOverview() {
        HealthComponent healthComponent = healthEndpoint.health();
        AppRequestOverview requestOverview = appRequestMetricsCollector.overview();
        int recentErrorCount = appErrorBuffer.recentErrors().size();
        int metricCount = appMetricReporter.listSnapshots().size();

        return AppOverviewData.builder()
                .healthStatus(healthComponent.getStatus().getCode())
                .applicationActive(isApplicationActive())
                .livenessState(applicationAvailability.getLivenessState().name())
                .readinessState(applicationAvailability.getReadinessState().name())
                .uptime(runtimeMXBean.getUptime())
                .activeRequestCount(requestOverview.activeRequestCount())
                .peakRequestCount(requestOverview.peakRequestCount())
                .totalRequestCount(requestOverview.totalRequestCount())
                .averageDurationMs(requestOverview.averageDurationMs())
                .sseConnectionCount(sseRegister.size())
                .totalErrorCount(appErrorBuffer.totalErrorCount())
                .recentErrorCount(recentErrorCount)
                .businessMetricCount(metricCount)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取 Spring Boot 当前状态。
     * <p>
     * 这里组合了三类状态来源：
     * <br/>
     * 1. ApplicationContext 是否激活；
     * <br/>
     * 2. Spring Boot liveness / readiness；
     * <br/>
     * 3. Actuator Health 总体与组件健康状态。
     */
    public AppStatusData getStatus() {
        HealthComponent healthComponent = healthEndpoint.health();
        List<AppStatusData.HealthComponentItem> components = new ArrayList<>();

        if (healthComponent instanceof CompositeHealth compositeHealth) {
            compositeHealth.getComponents().forEach((name, component) -> collectHealthComponents(name, component, components));
        }
        components.sort((left, right) -> left.getName().compareToIgnoreCase(right.getName()));

        return AppStatusData.builder()
                .applicationActive(isApplicationActive())
                .livenessState(applicationAvailability.getLivenessState().name())
                .readinessState(applicationAvailability.getReadinessState().name())
                .healthStatus(healthComponent.getStatus().getCode())
                .healthComponents(components)
                .uptime(runtimeMXBean.getUptime())
                .startTime(runtimeMXBean.getStartTime())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取请求统计。
     */
    public AppRequestMetricsData getRequestMetrics() {
        AppRequestOverview overview = appRequestMetricsCollector.overview();

        List<AppRequestMetricsData.SlowRequestItem> topSlowRequests = overview.topSlowRequests().stream()
                .map(this::buildSlowRequestItem)
                .toList();

        return AppRequestMetricsData.builder()
                .activeRequestCount(overview.activeRequestCount())
                .peakRequestCount(overview.peakRequestCount())
                .totalRequestCount(overview.totalRequestCount())
                .averageDurationMs(overview.averageDurationMs())
                .topSlowRequests(topSlowRequests)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取最近错误。
     */
    public AppErrorMetricsData getErrorMetrics() {
        List<AppErrorMetricsData.ErrorItem> recentErrors = appErrorBuffer.recentErrors().stream()
                .map(this::buildErrorItem)
                .toList();

        return AppErrorMetricsData.builder()
                .totalErrorCount(appErrorBuffer.totalErrorCount())
                .recentErrors(recentErrors)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取业务埋点指标。
     */
    public AppBusinessMetricsData getBusinessMetrics() {
        List<AppBusinessMetricsData.MetricItem> metrics = appMetricReporter.listSnapshots().stream()
                .map(this::buildMetricItem)
                .toList();

        List<AppBusinessMetricsData.MetricItem> highFrequencyTop10 = metrics.stream()
                .sorted((left, right) -> Long.compare(safeLong(right.getTotalCount()), safeLong(left.getTotalCount())))
                .limit(10)
                .toList();

        return AppBusinessMetricsData.builder()
                .metricCount(metrics.size())
                .highFrequencyTop10(highFrequencyTop10)
                .metrics(metrics)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 递归收集健康组件状态。
     */
    private void collectHealthComponents(String name, HealthComponent component, List<AppStatusData.HealthComponentItem> target) {
        target.add(AppStatusData.HealthComponentItem.builder()
                .name(name)
                .status(component.getStatus().getCode())
                .build());

        if (component instanceof CompositeHealth compositeHealth) {
            for (Map.Entry<String, HealthComponent> entry : compositeHealth.getComponents().entrySet()) {
                collectHealthComponents(name + "." + entry.getKey(), entry.getValue(), target);
            }
        }
    }

    /**
     * 判断 Spring 上下文是否处于激活状态。
     */
    private boolean isApplicationActive() {
        if (applicationContext instanceof ConfigurableApplicationContext configurableApplicationContext) {
            return configurableApplicationContext.isActive();
        }
        return true;
    }

    /**
     * 构建慢请求 DTO。
     */
    private AppRequestMetricsData.SlowRequestItem buildSlowRequestItem(AppRequestRecord record) {
        return AppRequestMetricsData.SlowRequestItem.builder()
                .startTime(record.startTime())
                .method(record.method())
                .path(record.path())
                .query(record.query())
                .status(record.status())
                .durationMs(record.durationMs())
                .clientIp(record.clientIp())
                .userId(record.userId())
                .userName(record.userName())
                .error(record.error())
                .build();
    }

    /**
     * 构建错误 DTO。
     */
    private AppErrorMetricsData.ErrorItem buildErrorItem(AppErrorRecord record) {
        return AppErrorMetricsData.ErrorItem.builder()
                .timestamp(record.timestamp())
                .category(record.category())
                .exceptionClass(record.exceptionClass())
                .message(record.message())
                .path(record.path())
                .method(record.method())
                .clientIp(record.clientIp())
                .userId(record.userId())
                .userName(record.userName())
                .stackTrace(record.stackTrace())
                .build();
    }

    /**
     * 构建业务指标 DTO。
     */
    private AppBusinessMetricsData.MetricItem buildMetricItem(AppMetricSnapshot snapshot) {
        return AppBusinessMetricsData.MetricItem.builder()
                .name(snapshot.name())
                .description(snapshot.description())
                .type(snapshot.type().name())
                .counterValue(snapshot.counterValue())
                .gaugeValue(snapshot.gaugeValue())
                .totalCount(snapshot.totalCount())
                .successCount(snapshot.successCount())
                .failureCount(snapshot.failureCount())
                .totalDurationMs(snapshot.totalDurationMs())
                .averageDurationMs(snapshot.averageDurationMs())
                .maxDurationMs(snapshot.maxDurationMs())
                .lastDurationMs(snapshot.lastDurationMs())
                .lastUpdatedTime(snapshot.lastUpdatedTime())
                .build();
    }

    /**
     * 处理空值比较。
     */
    private long safeLong(Long value) {
        return value == null ? 0L : value;
    }
}
