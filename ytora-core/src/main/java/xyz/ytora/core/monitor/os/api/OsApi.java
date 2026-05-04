package xyz.ytora.core.monitor.os.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.monitor.os.logic.OsLogic;
import xyz.ytora.core.monitor.os.model.data.*;

import java.util.List;

/**
 * created by YT on 2026/4/24
 * <br/>
 * 操作系统监控接口。
 */
@Slf4j
@Tag(name = "操作系统")
@RestController
@RequestMapping("/monitor/os")
@RequiredArgsConstructor
public class OsApi {

    private final OsLogic osLogic;

    // ======================== 静态数据 ========================>

    /**
     * 基础信息。
     */
    @GetMapping("/baseInfo")
    @Operation(summary = "基础信息", description = "基础信息")
    public BasicInfoData baseInfo() {
        return osLogic.baseInfo();
    }

    /**
     * CPU 静态信息。
     */
    @GetMapping("/cpu")
    @Operation(summary = "CPU静态信息", description = "CPU静态信息")
    public CpuStaticData getCpuStatic() {
        return osLogic.getCpuStatic();
    }

    /**
     * 内存静态信息。
     */
    @GetMapping("/memory")
    @Operation(summary = "内存静态信息", description = "内存静态信息")
    public MemStaticData getMemoryStatic() {
        return osLogic.getMemoryStatic();
    }

    /**
     * 磁盘静态信息。
     */
    @GetMapping("/disk")
    @Operation(summary = "磁盘静态信息", description = "磁盘静态信息")
    public DiskStaticData getDiskStatic() {
        return osLogic.getDiskStatic();
    }

    // ======================== 动态数据 ========================>

    /**
     * CPU 动态信息。
     */
    @GetMapping("/cpuRealtime")
    @Operation(summary = "CPU动态信息", description = "CPU动态信息")
    public CpuDynamicData getCpuDynamic() {
        return osLogic.getCpuDynamic();
    }

    /**
     * 内存动态信息。
     */
    @GetMapping("/memoryRealtime")
    @Operation(summary = "内存动态信息", description = "内存动态信息")
    public MemDynamicData getMemoryDynamic() {
        return osLogic.getMemoryDynamic();
    }

    /**
     * 磁盘动态信息。
     */
    @GetMapping("/diskRealtime")
    @Operation(summary = "磁盘动态信息", description = "磁盘动态信息")
    public DiskDynamicData getDiskDynamic() {
        return osLogic.getDiskDynamic();
    }

    /**
     * 网络实时速率。
     */
    @GetMapping("/network")
    @Operation(summary = "网络实时速率", description = "网络实时速率")
    public List<NetDynamicData> getNetworkDynamic() {
        return osLogic.getNetworkDynamic();
    }

    /**
     * 最耗 CPU 的前 10 个进程。
     */
    @GetMapping("/topProcesses")
    @Operation(summary = "最耗CPU进程", description = "最耗CPU进程")
    public List<ProcessData> getTopProcesses() {
        return osLogic.getTopProcesses();
    }

    /**
     * 其他动态监控指标。
     */
    @GetMapping("/otherMetrics")
    @Operation(summary = "其他动态监控指标", description = "其他动态监控指标")
    public OtherMetricsData getOtherMetrics() {
        return osLogic.getOtherMetrics();
    }
}
