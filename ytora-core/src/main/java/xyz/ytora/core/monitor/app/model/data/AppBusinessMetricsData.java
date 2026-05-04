package xyz.ytora.core.monitor.app.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 业务埋点指标。
 */
@Data
@Builder
@Schema(description = "业务埋点指标")
public class AppBusinessMetricsData {

    @Schema(description = "指标数量")
    private Integer metricCount;

    @Schema(description = "高频业务Top10")
    private List<MetricItem> highFrequencyTop10;

    @Schema(description = "指标列表")
    private List<MetricItem> metrics;

    @Schema(description = "采样时间戳")
    private Long timestamp;

    @Data
    @Builder
    @Schema(description = "业务指标项")
    public static class MetricItem {
        @Schema(description = "指标名称")
        private String name;

        @Schema(description = "指标说明")
        private String description;

        @Schema(description = "指标类型")
        private String type;

        @Schema(description = "计数器值")
        private Long counterValue;

        @Schema(description = "瞬时值")
        private Double gaugeValue;

        @Schema(description = "总调用次数")
        private Long totalCount;

        @Schema(description = "成功次数")
        private Long successCount;

        @Schema(description = "失败次数")
        private Long failureCount;

        @Schema(description = "累计耗时(毫秒)")
        private Long totalDurationMs;

        @Schema(description = "平均耗时(毫秒)")
        private Double averageDurationMs;

        @Schema(description = "最大耗时(毫秒)")
        private Long maxDurationMs;

        @Schema(description = "最近一次耗时(毫秒)")
        private Long lastDurationMs;

        @Schema(description = "最近更新时间")
        private Long lastUpdatedTime;
    }
}
