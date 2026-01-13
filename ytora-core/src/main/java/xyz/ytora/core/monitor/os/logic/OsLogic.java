package xyz.ytora.core.monitor.os.logic;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import xyz.ytora.core.monitor.os.model.resp.*;
import xyz.ytora.ytool.str.Strs;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * created by YT on 2026/1/13 22:29:06
 * <br/>
 * 操作系统监控
 */
@Service
public class OsLogic {

    // SystemInfo 是 OSHI 的入口
    private static final SystemInfo SI = new SystemInfo();

    private static final DecimalFormat DF = new DecimalFormat("#.##");

    // 保存上一次 CPU 采样的 Tick 数据，用于计算差值
    private long[] prevTicks;
    private long[][] prevProcTicks;

    // 用于计算 IO 速率的历史数据
    private Map<String, Long> lastReadBytesMap = new HashMap<>();
    private Map<String, Long> lastWriteBytesMap = new HashMap<>();
    private long lastIoTimestamp;

    // 用户计算网络实时流量指标
    private Map<String, Long> lastRxBytesMap = new HashMap<>();
    private Map<String, Long> lastTxBytesMap = new HashMap<>();
    private long lastNetTimestamp;

    /**
     * 初始化时先采样一次数据
     */
    @PostConstruct
    public void init() {
        CentralProcessor processor = SI.getHardware().getProcessor();
        this.prevTicks = processor.getSystemCpuLoadTicks();
        this.prevProcTicks = processor.getProcessorCpuLoadTicks();

        updateDiskIoMetrics();
    }

    /**
     * 操作系统基础信息
     */
    public BasicInfoResp baseInfo() {
        // 1. 获取 OSHI 的硬件和系统实例
        HardwareAbstractionLayer hal = SI.getHardware();
        OperatingSystem os = SI.getOperatingSystem();
        NetworkParams networkParams = os.getNetworkParams();

        // 2. 获取 JVM 系统属性
        Properties props = System.getProperties();

        // 3. 处理网络接口数据 (IP 和 MAC)
        List<NetworkIF> networkIFs = hal.getNetworkIFs();
        List<String> ipAddresses = new ArrayList<>();
        List<String> macAddresses = new ArrayList<>();

        for (NetworkIF net : networkIFs) {
            // 过滤掉回环地址(loopback)和未激活的网卡，只保留有IP的物理/虚拟网卡
            if (net.getIPv4addr().length != 0) {
                ipAddresses.addAll(Arrays.asList(net.getIPv4addr()));
                macAddresses.add(net.getMacaddr());
            }
        }

        // 4. 组装并返回 DTO
        return BasicInfoResp.builder()
                /* --------- 主机信息 --------- */
                .hostName(networkParams.getHostName())
                .ipAddresses(ipAddresses)
                .macAddresses(macAddresses)

                /* --------- 操作系统信息 --------- */
                .osName(os.getFamily()) // 例如: Windows 或 Linux
                .osVersion(os.getVersionInfo().getVersion()) // 版本号
                .osDescription(os.toString()) // 完整的发行版描述
                .kernelVersion(os.getVersionInfo().getBuildNumber()) // 内核/构建版本
                .arch(props.getProperty("os.arch")) // 系统架构 (x86_64/arm64)

                /* --------- 时间相关 --------- */
                // OSHI 返回的 BootTime 是秒，需乘以 1000 转为毫秒时间戳
                .bootTime(os.getSystemBootTime() * 1000L)
                // 系统已运行的秒数
                .uptime(os.getSystemUptime())
                // 获取当前 JVM 所在的时区 ID
                .timeZone(ZoneId.systemDefault().getId())
                .build();
    }

    /**
     * 获取 CPU 静态信息
     */
    public CpuStaticResp getCpuStatic() {
        CentralProcessor processor = SI.getHardware().getProcessor();
        CentralProcessor.ProcessorIdentifier id = processor.getProcessorIdentifier();

        return CpuStaticResp.builder()
                .cpuName(id.getName())
                .physicalPackageCount(processor.getPhysicalPackageCount())
                .physicalProcessorCount(processor.getPhysicalProcessorCount())
                .logicalProcessorCount(processor.getLogicalProcessorCount())
                .cpu64bit(id.isCpu64bit())
                .identifier(id.getIdentifier())
                .build();
    }

    /**
     * 获取 CPU 动态信息 (前端每5秒调用一次)
     */
    public CpuDynamicResp getCpuDynamic() {
        CentralProcessor processor = SI.getHardware().getProcessor();

        // 1. 获取当前时刻的 Ticks
        long[] ticks = processor.getSystemCpuLoadTicks();

        // 2. 计算各指标差值 (当前时刻 - 上次时刻)
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

        // 3. 计算每核使用率 (OSHI 内部已封装了差值计算方法)
        double[] loadBetweenTicks = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
        List<Double> perCpuUsage = Arrays.stream(loadBetweenTicks)
                .map(d -> Double.parseDouble(DF.format(d * 100)))
                .boxed()
                .collect(Collectors.toList());

        // 4. 更新历史 Ticks 记录供下次调用使用
        this.prevTicks = ticks;
        this.prevProcTicks = processor.getProcessorCpuLoadTicks();

        // 5. 组装返回结果
        // 如果 totalCpu 为 0 (采样间隔过短)，则返回 0
        if (totalCpu == 0) {
            return CpuDynamicResp.builder().totalUsage(0.0).timestamp(System.currentTimeMillis()).build();
        }

        return CpuDynamicResp.builder()
                .totalUsage(format((1d - (double) idle / totalCpu) * 100))
                .sysUsage(format((double) sys / totalCpu * 100))
                .userUsage(format((double) user / totalCpu * 100))
                .waitUsage(format((double) iowait / totalCpu * 100))
                .idleUsage(format((double) idle / totalCpu * 100))
                .perCpuUsage(perCpuUsage)
                .loadAverage(processor.getSystemLoadAverage(3))
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取内存静态信息
     */
    public MemStaticResp getMemoryStatic() {
        GlobalMemory memory = SI.getHardware().getMemory();
        long total = memory.getTotal();

        return MemStaticResp.builder()
                .total(total)
                .totalGb(Strs.formatSize(total))
                .pageSize(memory.getPageSize())
                .build();
    }

    /**
     * 获取内存动态信息
     */
    public MemDynamicResp getMemoryDynamic() {
        GlobalMemory memory = SI.getHardware().getMemory();
        VirtualMemory vm = memory.getVirtualMemory();

        // 1. 物理内存计算
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;

        // 2. 交换分区(Swap)计算
        long swapTotal = vm.getSwapTotal();
        long swapUsed = vm.getSwapUsed();
        long swapFree = swapTotal - swapUsed;

        return MemDynamicResp.builder()
                /* --------- 物理内存 --------- */
                .used(used)
                .available(available)
                .usageRate(calculateRate(used, total))

                /* --------- 交换分区 --------- */
                .swapTotal(swapTotal)
                .swapUsed(swapUsed)
                .swapFree(swapFree)
                .swapUsageRate(calculateRate(swapUsed, swapTotal))

                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取磁盘静态信息
     */
    public DiskStaticResp getDiskStatic() {
        // 1. 物理磁盘
        List<DiskStaticResp.PhysicalDisk> physicalDisks = SI.getHardware().getDiskStores().stream()
                .map(disk -> DiskStaticResp.PhysicalDisk.builder()
                        .model(disk.getModel())
                        .name(disk.getName())
                        .size(disk.getSize())
                        .build())
                .collect(Collectors.toList());

        // 2. 逻辑分区
        List<DiskStaticResp.LogicalPartition> logicalPartitions = SI.getOperatingSystem().getFileSystem().getFileStores().stream()
                .map(fs -> DiskStaticResp.LogicalPartition.builder()
                        .mount(fs.getMount())
                        .type(fs.getType())
                        .total(fs.getTotalSpace())
                        .build())
                .collect(Collectors.toList());

        return DiskStaticResp.builder()
                .physicalDisks(physicalDisks)
                .logicalPartitions(logicalPartitions)
                .build();
    }

    /**
     * 获取磁盘动态信息
     */
    public DiskDynamicResp getDiskDynamic() {
        // 1. 计算分区使用率 (FileStore)
        List<DiskDynamicResp.PartitionUsage> usages = SI.getOperatingSystem().getFileSystem().getFileStores().stream()
                .map(fs -> {
                    long total = fs.getTotalSpace();
                    long free = fs.getFreeSpace();
                    long used = total - free;
                    return DiskDynamicResp.PartitionUsage.builder()
                            .mount(fs.getMount())
                            .free(free)
                            .used(used)
                            .usageRate(total == 0 ? 0.0 : Double.parseDouble(DF.format(used * 100.0 / total)))
                            .build();
                })
                .collect(Collectors.toList());

        // 2. 计算 IO 速率 (DiskStore)
        long now = System.currentTimeMillis();
        double timeDeltaSec = (now - lastIoTimestamp) / 1000.0;

        List<HWDiskStore> currentDisks = SI.getHardware().getDiskStores();
        List<DiskDynamicResp.DiskIoRate> ioRates = currentDisks.stream()
                .map(disk -> {
                    long currentRead = disk.getReadBytes();
                    long currentWrite = disk.getWriteBytes();

                    // 计算速度: (当前字节 - 上次字节) / 时间差
                    long lastRead = lastReadBytesMap.getOrDefault(disk.getName(), currentRead);
                    long lastWrite = lastWriteBytesMap.getOrDefault(disk.getName(), currentWrite);

                    long readSpeed = (long) ((currentRead - lastRead) / timeDeltaSec);
                    long writeSpeed = (long) ((currentWrite - lastWrite) / timeDeltaSec);

                    // 存入当前值供下次计算使用
                    lastReadBytesMap.put(disk.getName(), currentRead);
                    lastWriteBytesMap.put(disk.getName(), currentWrite);

                    return DiskDynamicResp.DiskIoRate.builder()
                            .name(disk.getName())
                            .readSpeed(Math.max(0, readSpeed)) // 防止溢出或重启导致的负数
                            .writeSpeed(Math.max(0, writeSpeed))
                            .build();
                })
                .collect(Collectors.toList());

        lastIoTimestamp = now;

        return DiskDynamicResp.builder()
                .partitionUsages(usages)
                .ioRates(ioRates)
                .timestamp(now)
                .build();
    }

    /**
     * 获取网络实时速率
     */
    public List<NetDynamicResp> getNetworkDynamic() {
        List<NetworkIF> networkIFs = SI.getHardware().getNetworkIFs();
        long now = System.currentTimeMillis();
        double timeDeltaSec = (now - lastNetTimestamp) / 1000.0;

        List<NetDynamicResp> resps = networkIFs.stream().map(net -> {
            net.updateAttributes(); // 刷新网卡状态
            String name = net.getName();
            long rx = net.getBytesRecv();
            long tx = net.getBytesSent();

            long lastRx = lastRxBytesMap.getOrDefault(name, rx);
            long lastTx = lastTxBytesMap.getOrDefault(name, tx);

            long rxSpeed = (long) ((rx - lastRx) / timeDeltaSec);
            long txSpeed = (long) ((tx - lastTx) / timeDeltaSec);

            lastRxBytesMap.put(name, rx);
            lastTxBytesMap.put(name, tx);

            return NetDynamicResp.builder()
                    .ifaceName(name)
                    .rxSpeed(Math.max(0, rxSpeed))
                    .txSpeed(Math.max(0, txSpeed))
                    .rxBytes(rx)
                    .txBytes(tx)
                    .timestamp(now)
                    .build();
        }).collect(Collectors.toList());

        lastNetTimestamp = now;
        return resps;
    }

    /**
     * 获取最耗CPU的前10个进程
     */
    public List<ProcessResp> getTopProcesses() {
        OperatingSystem os = SI.getOperatingSystem();

        // 1. 获取进程列表时，指定排序规则（按CPU使用率倒序）和限制数量（10个）
        // 这里的 ProcessSort 选 CPU，OSHI 会自动帮我们处理复杂的采样计算
        List<OSProcess> procs = os.getProcesses(null, OperatingSystem.ProcessSorting.CPU_DESC, 11);

        return procs.stream()
                .filter(p -> p.getProcessID() > 0)
                .limit(10)
                .map(p -> {
                    // 2. 获取进程 CPU 使用率 (0.0 ~ 1.0)
                    double cpuUsage = p.getProcessCpuLoadCumulative();

                    // 3. 安全检查：处理 NaN 或 Infinity，防止 Double.parseDouble 报错
                    if (Double.isNaN(cpuUsage) || Double.isInfinite(cpuUsage)) {
                        cpuUsage = 0.0;
                    }

                    // 4. 转换为百分比并格式化
                    // 注意：OSHI 的 getProcessCpuLoadCumulative() 结果已经考虑了多核情况
                    // 如果是 8 核 CPU，单个进程满载可能是 12.5% (1/8)，也可能返回 100% 取决于 OS 实现。
                    // 通常我们乘以 100 得到百分比。
                    double formattedCpu = Double.parseDouble(DF.format(cpuUsage * 100.0));

                    return ProcessResp.builder()
                            .pid(p.getProcessID())
                            .name(p.getName())
                            .cpuUsage(formattedCpu)
                            .memUsage(p.getResidentSetSize()) // 常驻内存占用
                            .path(p.getPath())
                            .uptime(p.getUpTime())
                            .build();
                }).collect(Collectors.toList());
    }

    public OtherMetricsResp getOtherMetrics() {
        OperatingSystem os = SI.getOperatingSystem();
        HardwareAbstractionLayer hal = SI.getHardware();
        FileSystem fileSystem = os.getFileSystem();

        // 1. 文件描述符
        long openFD = fileSystem.getOpenFileDescriptors();
        long maxFD = fileSystem.getMaxFileDescriptors();

        // 2. 网络协议统计
        InternetProtocolStats ipStats = os.getInternetProtocolStats();
        InternetProtocolStats.TcpStats tcpStats = ipStats.getTCPv4Stats();

        // 3. GPU 监控 (OSHI 支持部分显卡信息获取)
        List<OtherMetricsResp.GpuInfo> gpuInfos = hal.getGraphicsCards().stream()
                .map(card -> OtherMetricsResp.GpuInfo.builder()
                        .name(card.getName())
                        // 注意：OSHI 获取 GPU 实时显存和温度的能力有限，
                        // 复杂场景通常需要调用 nvidia-smi 命令行
                        .build())
                .collect(Collectors.toList());

        return OtherMetricsResp.builder()
                .openFileDescriptors(openFD)
                .maxFileDescriptors(maxFD)
                .processCount(os.getProcessCount())
                .threadCount(os.getThreadCount())
                .tcpEstablished(tcpStats.getConnectionsEstablished())
                .tcpListening(tcpStats.getConnectionsActive()) // 简化的监听表示
                .gpus(gpuInfos)
                .build();
    }

    private Double format(Double value) {
        return Double.parseDouble(DF.format(value));
    }

    /**
     * 计算百分比
     */
    private Double calculateRate(long used, long total) {
        if (total == 0) return 0.0;
        return Double.parseDouble(DF.format(used * 100.0 / total));
    }

    // 更新内部 I/O 计数器的辅助方法
    private void updateDiskIoMetrics() {
        List<HWDiskStore> disks = SI.getHardware().getDiskStores();
        for (HWDiskStore disk : disks) {
            lastReadBytesMap.put(disk.getName(), disk.getReadBytes());
            lastWriteBytesMap.put(disk.getName(), disk.getWriteBytes());
        }
        lastIoTimestamp = System.currentTimeMillis();
    }
}
