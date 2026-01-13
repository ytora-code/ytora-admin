package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "内存静态配置信息")
public class MemStaticResp {
    @Schema(description = "总物理内存 (Bytes)", example = "17179869184")
    private Long total;

    @Schema(description = "总物理内存 (GB)", example = "16.0")
    private String totalGb;

    @Schema(description = "内存页大小 (Bytes)")
    private Long pageSize;
}