package xyz.ytora.core.monitor.app.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 应用基础信息。
 */
@Data
@Builder
@Schema(description = "应用基础信息")
public class AppBasicInfoData {

    @Schema(description = "应用名称")
    private String applicationName;

    @Schema(description = "应用版本")
    private String applicationVersion;

    @Schema(description = "上下文路径")
    private String contextPath;

    @Schema(description = "服务端口")
    private String port;

    @Schema(description = "当前激活环境")
    private List<String> activeProfiles;

    @Schema(description = "应用启动时间戳")
    private Long startTime;

    @Schema(description = "当前进程PID")
    private Long pid;
}
