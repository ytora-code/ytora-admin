package xyz.ytora.core.monitor.app.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 请求监控信息。
 */
@Data
@Builder
@Schema(description = "请求监控信息")
public class AppRequestMetricsData {

    @Schema(description = "当前活跃请求数")
    private Integer activeRequestCount;

    @Schema(description = "历史峰值并发请求数")
    private Integer peakRequestCount;

    @Schema(description = "累计已完成请求数")
    private Long totalRequestCount;

    @Schema(description = "平均请求耗时(毫秒)")
    private Double averageDurationMs;

    @Schema(description = "历史最慢请求Top10")
    private List<SlowRequestItem> topSlowRequests;

    @Schema(description = "采样时间戳")
    private Long timestamp;

    @Data
    @Builder
    @Schema(description = "慢请求")
    public static class SlowRequestItem {
        @Schema(description = "请求开始时间戳")
        private Long startTime;

        @Schema(description = "请求方法")
        private String method;

        @Schema(description = "请求路径")
        private String path;

        @Schema(description = "查询串")
        private String query;

        @Schema(description = "响应状态码")
        private Integer status;

        @Schema(description = "请求耗时(毫秒)")
        private Long durationMs;

        @Schema(description = "客户端IP")
        private String clientIp;

        @Schema(description = "当前用户ID")
        private String userId;

        @Schema(description = "当前用户名")
        private String userName;

        @Schema(description = "是否异常结束")
        private Boolean error;
    }
}
