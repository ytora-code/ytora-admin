package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "CPU 动态运行指标")
public class CpuDynamicResp {

    @Schema(description = "CPU总使用率", example = "12.5")
    private Double totalUsage;

    @Schema(description = "CPU系统使用率", example = "4.2")
    private Double sysUsage;

    @Schema(description = "CPU用户使用率", example = "8.1")
    private Double userUsage;

    @Schema(description = "CPU当前等待率(I/O)", example = "0.2")
    private Double waitUsage;

    @Schema(description = "CPU当前空闲率", example = "87.5")
    private Double idleUsage;

    @Schema(description = "每颗逻辑核的使用率列表")
    private List<Double> perCpuUsage;

    @Schema(description = "系统负载平均值 (1m, 5m, 15m)", example = "[1.5, 2.0, 1.8]")
    private double[] loadAverage;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}