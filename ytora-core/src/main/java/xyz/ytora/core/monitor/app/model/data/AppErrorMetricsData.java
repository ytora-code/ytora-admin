package xyz.ytora.core.monitor.app.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 最近错误信息。
 */
@Data
@Builder
@Schema(description = "最近错误信息")
public class AppErrorMetricsData {

    @Schema(description = "累计错误总数")
    private Long totalErrorCount;

    @Schema(description = "最近20个错误")
    private List<ErrorItem> recentErrors;

    @Schema(description = "采样时间戳")
    private Long timestamp;

    @Data
    @Builder
    @Schema(description = "错误项")
    public static class ErrorItem {
        @Schema(description = "记录时间戳")
        private Long timestamp;

        @Schema(description = "错误分类")
        private String category;

        @Schema(description = "异常类名")
        private String exceptionClass;

        @Schema(description = "异常消息")
        private String message;

        @Schema(description = "请求路径")
        private String path;

        @Schema(description = "请求方法")
        private String method;

        @Schema(description = "客户端IP")
        private String clientIp;

        @Schema(description = "当前用户ID")
        private String userId;

        @Schema(description = "当前用户名")
        private String userName;

        @Schema(description = "堆栈信息")
        private String stackTrace;
    }
}
