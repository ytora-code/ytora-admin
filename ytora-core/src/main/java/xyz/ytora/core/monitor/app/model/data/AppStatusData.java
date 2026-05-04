package xyz.ytora.core.monitor.app.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Spring Boot 状态。
 */
@Data
@Builder
@Schema(description = "SpringBoot状态")
public class AppStatusData {

    @Schema(description = "Spring上下文是否激活")
    private Boolean applicationActive;

    @Schema(description = "Liveness状态")
    private String livenessState;

    @Schema(description = "Readiness状态")
    private String readinessState;

    @Schema(description = "Actuator健康状态")
    private String healthStatus;

    @Schema(description = "健康组件列表")
    private List<HealthComponentItem> healthComponents;

    @Schema(description = "应用运行时长(毫秒)")
    private Long uptime;

    @Schema(description = "应用启动时间戳")
    private Long startTime;

    @Schema(description = "采样时间戳")
    private Long timestamp;

    @Data
    @Builder
    @Schema(description = "健康组件状态")
    public static class HealthComponentItem {
        @Schema(description = "组件名称")
        private String name;

        @Schema(description = "组件状态")
        private String status;
    }
}
