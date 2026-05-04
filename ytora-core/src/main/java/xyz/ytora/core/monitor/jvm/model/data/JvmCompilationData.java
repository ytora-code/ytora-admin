package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * JVM JIT 编译信息。
 */
@Data
@Builder
@Schema(description = "JVM即时编译信息")
public class JvmCompilationData {

    @Schema(description = "JIT编译器名称", example = "HotSpot 64-Bit Tiered Compilers")
    private String compilerName;

    @Schema(description = "是否支持编译耗时监控")
    private Boolean compilationTimeMonitoringSupported;

    @Schema(description = "累计编译耗时(毫秒)，不支持时为null")
    private Long totalCompilationTime;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
