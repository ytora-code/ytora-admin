package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "进程")
public class ProcessResp {
    @Schema(description = "进程PID", example = "12345")
    private int pid;
    @Schema(description = "进程名称")
    private String name;
    @Schema(description = "进程占用的CPU百分比")
    private Double cpuUsage;
    @Schema(description = "进程占用的物理内存(Bytes)")
    private Long memUsage;
    @Schema(description = "进程执行路径")
    private String path;
    @Schema(description = "运行时间(ms)")
    private Long uptime;
}