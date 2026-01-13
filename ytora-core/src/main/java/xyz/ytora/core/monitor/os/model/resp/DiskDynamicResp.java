package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@Schema(description = "磁盘动态运行指标")
public class DiskDynamicResp {

    @Schema(description = "分区占用情况")
    private List<PartitionUsage> partitionUsages;

    @Schema(description = "物理磁盘IO情况")
    private List<DiskIoRate> ioRates;

    @Schema(description = "采样时间戳")
    private Long timestamp;

    @Data
    @Builder
    public static class PartitionUsage {
        @Schema(description = "挂载点")
        private String mount;
        @Schema(description = "已用空间 (Bytes)")
        private Long used;
        @Schema(description = "可用空间 (Bytes)")
        private Long free;
        @Schema(description = "使用率", example = "75.5")
        private Double usageRate;
    }

    @Data
    @Builder
    public static class DiskIoRate {
        @Schema(description = "磁盘名称")
        private String name;
        @Schema(description = "读取速度 (Bytes/s)")
        private Long readSpeed;
        @Schema(description = "写入速度 (Bytes/s)")
        private Long writeSpeed;
    }
}