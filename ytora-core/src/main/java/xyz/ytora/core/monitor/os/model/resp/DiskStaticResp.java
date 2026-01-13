package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@Schema(description = "磁盘静态配置信息")
public class DiskStaticResp {

    @Schema(description = "物理磁盘列表")
    private List<PhysicalDisk> physicalDisks;

    @Schema(description = "逻辑分区列表")
    private List<LogicalPartition> logicalPartitions;

    @Data
    @Builder
    public static class PhysicalDisk {
        @Schema(description = "磁盘型号")
        private String model;
        @Schema(description = "磁盘名称")
        private String name;
        @Schema(description = "总大小 (Bytes)")
        private Long size;
    }

    @Data
    @Builder
    public static class LogicalPartition {
        @Schema(description = "挂载点", example = "/ or C:\\")
        private String mount;
        @Schema(description = "文件系统类型", example = "ext4 or NTFS")
        private String type;
        @Schema(description = "分区总大小 (Bytes)")
        private Long total;
    }
}