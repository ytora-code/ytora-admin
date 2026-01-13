package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "CPU 静态配置信息")
public class CpuStaticResp {
    @Schema(description = "CPU型号", example = "Intel(R) Core(TM) i7-10700 CPU @ 2.90GHz")
    private String cpuName;

    @Schema(description = "物理插槽数 (Sockets)")
    private Integer physicalPackageCount;

    @Schema(description = "物理核心数 (Physical Cores)")
    private Integer physicalProcessorCount;

    @Schema(description = "逻辑核心数 (Threads)")
    private Integer logicalProcessorCount;

    @Schema(description = "是否为64位")
    private Boolean cpu64bit;

    @Schema(description = "CPU 标识符 (Vendor ID, Stepping, etc.)")
    private String identifier;
}