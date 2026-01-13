package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "其他监控指标")
public class OtherMetricsResp {

    /* --------- 系统限制 --------- */
    @Schema(description = "当前打开的文件描述符数", example = "1024")
    private Long openFileDescriptors;

    @Schema(description = "系统最大文件描述符数", example = "65535")
    private Long maxFileDescriptors;

    /* --------- 进程/线程统计 --------- */
    @Schema(description = "总进程数")
    private Integer processCount;

    @Schema(description = "总线程数")
    private Integer threadCount;

    /* --------- 网络套接字统计 --------- */
    @Schema(description = "TCP 已建立连接数")
    private Long tcpEstablished;

    @Schema(description = "TCP 监听中的连接数")
    private Long tcpListening;

    /* --------- 显卡监控 (可选) --------- */
    @Schema(description = "显卡信息列表")
    private List<GpuInfo> gpus;

    @Data
    @Builder
    public static class GpuInfo {
        private String name;
        private Long memoryTotal;
        private Long memoryUsed;
        private Double temperature;
    }
}