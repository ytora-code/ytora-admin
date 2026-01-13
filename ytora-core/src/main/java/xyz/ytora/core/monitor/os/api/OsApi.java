package xyz.ytora.core.monitor.os.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.monitor.os.logic.OsLogic;
import xyz.ytora.core.monitor.os.model.resp.*;

import java.util.List;

/**
 * created by YT on 2026/1/13 22:09:48
 * <br/>
 * 操作系统监控
 */
@Slf4j
@Tag(name = "操作系统")
@RestController
@RequestMapping("/monitor/os")
@RequiredArgsConstructor
public class OsApi {

    private final OsLogic osLogic;

    /**
     * 基础信息
     */
    @GetMapping("/baseInfo")
    @Operation(summary = "基础信息", description = "基础信息")
    public BasicInfoResp baseInfo() {
        return osLogic.baseInfo();
    }

    /**
     * CPU静态信息
     */
    @GetMapping("/cpuStatic")
    @Operation(summary = "CPU静态信息", description = "CPU静态信息")
    public CpuStaticResp getCpuStatic() {
        return osLogic.getCpuStatic();
    }

    /**
     * CPU动态信息
     */
    @GetMapping("/cpuDynamic")
    @Operation(summary = "CPU动态信息", description = "CPU动态信息")
    public CpuDynamicResp getCpuDynamic() {
        return osLogic.getCpuDynamic();
    }

    /**
     * 内存静态信息
     */
    @GetMapping("/memoryStatic")
    @Operation(summary = "内存静态信息", description = "内存静态信息")
    public MemStaticResp getMemoryStatic() {
        return osLogic.getMemoryStatic();
    }

    /**
     * 内存动态信息
     */
    @GetMapping("/memoryDynamic")
    @Operation(summary = "内存动态信息", description = "内存动态信息")
    public MemDynamicResp getMemoryDynamic() {
        return osLogic.getMemoryDynamic();
    }

    /**
     * 磁盘静态信息
     */
    @GetMapping("/diskStatic")
    @Operation(summary = "磁盘静态信息", description = "磁盘静态信息")
    public DiskStaticResp diskStatic() {
        return osLogic.getDiskStatic();
    }

    /**
     * 磁盘动态信息
     */
    @GetMapping("/diskDynamic")
    @Operation(summary = "磁盘动态信息", description = "磁盘动态信息")
    public DiskDynamicResp getDiskDynamic() {
        return osLogic.getDiskDynamic();
    }

    /**
     * 网络实时速率
     */
    @GetMapping("/networkDynamic")
    @Operation(summary = "网络实时速率", description = "网络实时速率")
    public List<NetDynamicResp> getNetworkDynamic() {
        return osLogic.getNetworkDynamic();
    }

    /**
     * 获取最耗CPU的前10个进程
     */
    @GetMapping("/topProcesses")
    @Operation(summary = "获取最耗CPU的前10个进程", description = "获取最耗CPU的前10个进程")
    public List<ProcessResp> getTopProcesses() {
        return osLogic.getTopProcesses();
    }

    /**
     * 其他监控指标
     */
    @GetMapping("/otherMetrics")
    @Operation(summary = "其他监控指标", description = "其他监控指标")
    public OtherMetricsResp getOtherMetrics() {
        return osLogic.getOtherMetrics();
    }

}
