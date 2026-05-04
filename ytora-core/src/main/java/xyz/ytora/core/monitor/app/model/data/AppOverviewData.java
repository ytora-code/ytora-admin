package xyz.ytora.core.monitor.app.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 应用总览。
 * <p>
 * 该对象面向前端首页驾驶舱使用，
 * 只聚合最常用、最适合直接做卡片展示的动态摘要数据。
 */
@Data
@Builder
@Schema(description = "应用总览")
public class AppOverviewData {

    @Schema(description = "应用总体健康状态")
    private String healthStatus;

    @Schema(description = "Spring上下文是否激活")
    private Boolean applicationActive;

    @Schema(description = "Liveness状态")
    private String livenessState;

    @Schema(description = "Readiness状态")
    private String readinessState;

    @Schema(description = "应用运行时长(毫秒)")
    private Long uptime;

    @Schema(description = "当前活跃请求数")
    private Integer activeRequestCount;

    @Schema(description = "历史峰值并发请求数")
    private Integer peakRequestCount;

    @Schema(description = "累计请求总数")
    private Long totalRequestCount;

    @Schema(description = "平均请求耗时(毫秒)")
    private Double averageDurationMs;

    @Schema(description = "当前SSE连接数")
    private Integer sseConnectionCount;

    @Schema(description = "累计错误数")
    private Long totalErrorCount;

    @Schema(description = "最近错误数，最大为20")
    private Integer recentErrorCount;

    @Schema(description = "业务指标数量")
    private Integer businessMetricCount;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
