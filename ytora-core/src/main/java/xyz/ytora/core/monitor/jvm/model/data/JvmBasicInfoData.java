package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * JVM 基础信息。
 */
@Data
@Builder
@Schema(description = "JVM基础信息")
public class JvmBasicInfoData {

    @Schema(description = "JVM名称", example = "OpenJDK 64-Bit Server VM")
    private String vmName;

    @Schema(description = "JVM供应商", example = "Oracle Corporation")
    private String vmVendor;

    @Schema(description = "JVM版本", example = "25+36")
    private String vmVersion;

    @Schema(description = "JVM规范名称", example = "Java Virtual Machine Specification")
    private String specName;

    @Schema(description = "JVM规范供应商")
    private String specVendor;

    @Schema(description = "JVM规范版本")
    private String specVersion;

    @Schema(description = "JMX管理规范版本")
    private String managementSpecVersion;

    @Schema(description = "Java安装目录")
    private String javaHome;

    @Schema(description = "Java版本")
    private String javaVersion;

    @Schema(description = "Java供应商")
    private String javaVendor;

    @Schema(description = "JVM附加信息")
    private String javaVmInfo;

    @Schema(description = "JVM启动参数")
    private List<String> inputArguments;

    @Schema(description = "是否支持BootClassPath")
    private Boolean bootClassPathSupported;

    @Schema(description = "系统属性数量")
    private Integer systemPropertiesCount;

    @Schema(description = "JVM启动时间戳")
    private Long startTime;
}
