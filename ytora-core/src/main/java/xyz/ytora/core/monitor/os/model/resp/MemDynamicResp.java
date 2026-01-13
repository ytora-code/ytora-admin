package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "内存动态运行指标")
public class MemDynamicResp {

    /* --------- 物理内存 --------- */
    @Schema(description = "已用物理内存 (Bytes)")
    private Long used;

    @Schema(description = "可用物理内存 (Bytes)")
    private Long available;

    @Schema(description = "物理内存使用率", example = "45.5")
    private Double usageRate;

    /* --------- 交换分区 (Swap) --------- */
    @Schema(description = "总交换分区 (Bytes)")
    private Long swapTotal;

    @Schema(description = "已用交换分区 (Bytes)")
    private Long swapUsed;

    @Schema(description = "可用交换分区 (Bytes)")
    private Long swapFree;

    @Schema(description = "交换分区使用率", example = "10.2")
    private Double swapUsageRate;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}