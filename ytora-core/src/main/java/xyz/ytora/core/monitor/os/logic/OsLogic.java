package xyz.ytora.core.monitor.os.logic;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.NetworkParams;
import xyz.ytora.core.monitor.os.model.data.BasicInfoData;
import xyz.ytora.core.monitor.os.model.data.CpuDynamicData;
import xyz.ytora.core.monitor.os.model.data.CpuStaticData;
import xyz.ytora.core.monitor.os.model.data.DiskDynamicData;
import xyz.ytora.core.monitor.os.model.data.DiskStaticData;
import xyz.ytora.core.monitor.os.model.data.MemDynamicData;
import xyz.ytora.core.monitor.os.model.data.MemStaticData;
import xyz.ytora.core.monitor.os.model.data.NetDynamicData;
import xyz.ytora.core.monitor.os.model.data.OtherMetricsData;
import xyz.ytora.core.monitor.os.model.data.ProcessData;
import xyz.ytora.toolkit.text.Strs;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by YT on 2026/1/13 22:29:06
 * <br/>
 * 操作系统监控。
 * <p>
 * 设计要点：
 * <br/>
 * 1. OSHI 的很多动态指标都依赖“前后两次采样差值”计算，而不是一次读取就能得到真实速率。
 * <br/>
 * 2. Spring Service 默认是单例，如果直接把“上次采样状态”暴露给多个并发请求共享，
 *    不做保护就会出现采样窗口互相覆盖、速率抖动、首帧异常等问题。
 * <br/>
 * 3. 因此本类把所有依赖历史状态的动态采样统一放到同一把锁中维护，
 *    让每次采样都基于一致的历史快照进行计算。
 */
@Service
public class OsLogic {

    /**
     * OSHI 总入口。
     * <p>
     * SystemInfo 创建成本不低，而且内部会缓存部分平台信息，整个应用共用一个实例即可。
     */
    private static final SystemInfo SI = new SystemInfo();

    /**
     * 统一保留两位小数，避免接口层出现过长的小数串。
     */
    private static final int SCALE = 100;

    /**
     * Top 进程展示数量。
     */
    private static final int TOP_PROCESS_LIMIT = 10;

    private final HardwareAbstractionLayer hardware = SI.getHardware();
    private final OperatingSystem os = SI.getOperatingSystem();

    /**
     * 所有“依赖上一次采样状态”的动态指标都通过同一把锁保护。
     * <p>
     * 这样做的目的不是把所有接口串行化，而是确保：
     * <br/>
     * 1. 同一个动态指标不会被并发请求打乱采样窗口；
     * <br/>
     * 2. 读写历史快照时不会发生竞态条件；
     * <br/>
     * 3. 首次采样和后续采样的行为稳定、可预测。
     */
    private final Object sampleLock = new Object();

    /**
     * CPU 系统级采样基线。
     */
    private long[] prevCpuTicks;

    /**
     * CPU 每个逻辑核采样基线。
     */
    private long[][] prevProcessorTicks;

    /**
     * 磁盘累计读写字节的上一次快照。
     * key 使用磁盘名，value 保存“上次采样时的累计字节数”。
     */
    private final Map<String, DiskIoCounter> diskIoCounters = new HashMap<>();

    /**
     * 网络累计收发字节的上一次快照。
     * key 使用网卡名，value 保存“上次采样时的累计字节数”。
     */
    private final Map<String, NetIoCounter> netIoCounters = new HashMap<>();

    /**
     * 进程 CPU 差值采样基线。
     * key 为 pid，value 为上一次采样到的进程快照。
     */
    private final Map<Integer, OSProcess> prevProcessMap = new HashMap<>();

    /**
     * 磁盘速率采样时间基线。
     */
    private long lastDiskSampleTime;

    /**
     * 网络速率采样时间基线。
     */
    private long lastNetSampleTime;

    @PostConstruct
    public void init() {
        synchronized (sampleLock) {
            initCpuBaseline();
            initDiskBaseline();
            initNetworkBaseline();
            initProcessBaseline();
        }
    }

    /**
     * 获取操作系统基础信息。
     * <p>
     * 该接口返回的是“基本不依赖前后差值”的静态/半静态信息，
     * 因此不需要进入采样锁。
     */
    public BasicInfoData baseInfo() {
        NetworkParams networkParams = os.getNetworkParams();
        Properties props = System.getProperties();

        Set<String> ipAddresses = new LinkedHashSet<>();
        Set<String> macAddresses = new LinkedHashSet<>();

        for (NetworkIF net : hardware.getNetworkIFs()) {
            net.updateAttributes();

            String[] ipv4 = net.getIPv4addr();
            String[] ipv6 = net.getIPv6addr();
            boolean hasAddress = ipv4.length > 0 || ipv6.length > 0;
            if (!hasAddress) {
                continue;
            }

            if (isLoopbackAddressOnly(ipv4, ipv6)) {
                continue;
            }

            ipAddresses.addAll(Arrays.asList(ipv4));
            ipAddresses.addAll(Arrays.asList(ipv6));

            if (net.getMacaddr() != null && !net.getMacaddr().isBlank()) {
                macAddresses.add(net.getMacaddr());
            }
        }

        return BasicInfoData.builder()
                .hostName(networkParams.getHostName())
                .ipAddresses(new ArrayList<>(ipAddresses))
                .macAddresses(new ArrayList<>(macAddresses))
                .osName(os.getFamily())
                .osVersion(os.getVersionInfo().getVersion())
                .osDescription(os.toString())
                // codeName 在不同平台上通常比 buildNumber 更接近“发行版/内核标识”，这里优先展示更稳定的信息。
                .kernelVersion(resolveKernelVersion())
                .arch(props.getProperty("os.arch"))
                .bootTime(os.getSystemBootTime() * 1000L)
                .uptime(os.getSystemUptime())
                .timeZone(ZoneId.systemDefault().getId())
                .build();
    }

    /**
     * 获取 CPU 静态信息。
     */
    public CpuStaticData getCpuStatic() {
        CentralProcessor processor = hardware.getProcessor();
        CentralProcessor.ProcessorIdentifier id = processor.getProcessorIdentifier();

        return CpuStaticData.builder()
                .cpuName(id.getName())
                .physicalPackageCount(processor.getPhysicalPackageCount())
                .physicalProcessorCount(processor.getPhysicalProcessorCount())
                .logicalProcessorCount(processor.getLogicalProcessorCount())
                .cpu64bit(id.isCpu64bit())
                .identifier(id.getIdentifier())
                .build();
    }

    /**
     * 获取 CPU 动态信息。
     * <p>
     * 这里的使用率属于“区间值”：
     * 本次采样结果 = 当前 ticks - 上次 ticks。
     * <br/>
     * 如果采样间隔过短，totalTicks 可能为 0，此时返回 0，避免产生无意义数据。
     */
    public CpuDynamicData getCpuDynamic() {
        synchronized (sampleLock) {
            CentralProcessor processor = hardware.getProcessor();
            long now = System.currentTimeMillis();

            long[] currentTicks = processor.getSystemCpuLoadTicks();
            long[] prevTicks = prevCpuTicks;
            long[][] prevProcTicks = prevProcessorTicks;

            if (prevTicks == null || prevProcTicks == null) {
                initCpuBaseline();
                List<Double> perCpuUsage = new ArrayList<>(processor.getLogicalProcessorCount());
                for (int i = 0; i < processor.getLogicalProcessorCount(); i++) {
                    perCpuUsage.add(0d);
                }
                return CpuDynamicData.builder()
                        .totalUsage(0d)
                        .sysUsage(0d)
                        .userUsage(0d)
                        .waitUsage(0d)
                        .idleUsage(0d)
                        .perCpuUsage(perCpuUsage)
                        .loadAverage(new double[]{0d, 0d, 0d})
                        .timestamp(now)
                        .build();
            }

            long user = delta(currentTicks, prevTicks, CentralProcessor.TickType.USER);
            long nice = delta(currentTicks, prevTicks, CentralProcessor.TickType.NICE);
            long sys = delta(currentTicks, prevTicks, CentralProcessor.TickType.SYSTEM);
            long idle = delta(currentTicks, prevTicks, CentralProcessor.TickType.IDLE);
            long iowait = delta(currentTicks, prevTicks, CentralProcessor.TickType.IOWAIT);
            long irq = delta(currentTicks, prevTicks, CentralProcessor.TickType.IRQ);
            long softirq = delta(currentTicks, prevTicks, CentralProcessor.TickType.SOFTIRQ);
            long steal = delta(currentTicks, prevTicks, CentralProcessor.TickType.STEAL);
            long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

            double[] currentProcessorLoads = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
            List<Double> perCpuUsage = Arrays.stream(currentProcessorLoads)
                    .map(load -> round(load * 100))
                    .boxed()
                    .collect(Collectors.toList());

            prevCpuTicks = currentTicks;
            prevProcessorTicks = processor.getProcessorCpuLoadTicks();

            if (totalCpu <= 0) {
                return CpuDynamicData.builder()
                        .totalUsage(0d)
                        .sysUsage(0d)
                        .userUsage(0d)
                        .waitUsage(0d)
                        .idleUsage(0d)
                        .perCpuUsage(perCpuUsage)
                        .loadAverage(new double[]{0d, 0d, 0d})
                        .timestamp(now)
                        .build();
            }

            return CpuDynamicData.builder()
                    .totalUsage(round((1d - (double) idle / totalCpu) * 100))
                    .sysUsage(round((double) sys / totalCpu * 100))
                    .userUsage(round((double) user / totalCpu * 100))
                    .waitUsage(round((double) iowait / totalCpu * 100))
                    .idleUsage(round((double) idle / totalCpu * 100))
                    .perCpuUsage(perCpuUsage)
                    .loadAverage(normalizeLoadAverage(processor.getSystemLoadAverage(3)))
                    .timestamp(now)
                    .build();
        }
    }

    /**
     * 获取内存静态信息。
     */
    public MemStaticData getMemoryStatic() {
        GlobalMemory memory = hardware.getMemory();
        long total = memory.getTotal();

        return MemStaticData.builder()
                .total(total)
                .totalGb(Strs.formatSize(total))
                .pageSize(memory.getPageSize())
                .build();
    }

    /**
     * 获取内存动态信息。
     */
    public MemDynamicData getMemoryDynamic() {
        GlobalMemory memory = hardware.getMemory();
        VirtualMemory vm = memory.getVirtualMemory();

        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = Math.max(0L, total - available);

        long swapTotal = vm.getSwapTotal();
        long swapUsed = vm.getSwapUsed();
        long swapFree = Math.max(0L, swapTotal - swapUsed);

        return MemDynamicData.builder()
                .used(used)
                .available(available)
                .usageRate(calculateRate(used, total))
                .swapTotal(swapTotal)
                .swapUsed(swapUsed)
                .swapFree(swapFree)
                .swapUsageRate(calculateRate(swapUsed, swapTotal))
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取磁盘静态信息。
     */
    public DiskStaticData getDiskStatic() {
        List<DiskStaticData.PhysicalDisk> physicalDisks = hardware.getDiskStores().stream()
                .peek(HWDiskStore::updateAttributes)
                .map(disk -> DiskStaticData.PhysicalDisk.builder()
                        .model(disk.getModel())
                        .name(disk.getName())
                        .size(disk.getSize())
                        .build())
                .collect(Collectors.toList());

        List<DiskStaticData.LogicalPartition> logicalPartitions = os.getFileSystem().getFileStores().stream()
                .map(fs -> DiskStaticData.LogicalPartition.builder()
                        .mount(fs.getMount())
                        .type(fs.getType())
                        .total(fs.getTotalSpace())
                        .build())
                .collect(Collectors.toList());

        return DiskStaticData.builder()
                .physicalDisks(physicalDisks)
                .logicalPartitions(logicalPartitions)
                .build();
    }

    /**
     * 获取磁盘动态信息。
     * <p>
     * 返回两类数据：
     * <br/>
     * 1. 分区占用率：直接读取当前空间快照即可。
     * <br/>
     * 2. 读写速率：必须基于“累计读写字节差值 / 时间差”计算。
     */
    public DiskDynamicData getDiskDynamic() {
        synchronized (sampleLock) {
            long now = System.currentTimeMillis();

            List<DiskDynamicData.PartitionUsage> partitionUsages = os.getFileSystem().getFileStores().stream()
                    .map(this::buildPartitionUsage)
                    .collect(Collectors.toList());

            if (lastDiskSampleTime <= 0L) {
                initDiskBaseline();
                return DiskDynamicData.builder()
                        .partitionUsages(partitionUsages)
                        .ioRates(List.of())
                        .timestamp(now)
                        .build();
            }

            long intervalMs = now - lastDiskSampleTime;
            if (intervalMs <= 0L) {
                return DiskDynamicData.builder()
                        .partitionUsages(partitionUsages)
                        .ioRates(buildZeroDiskIoRates())
                        .timestamp(now)
                        .build();
            }

            double intervalSeconds = intervalMs / 1000d;
            List<DiskDynamicData.DiskIoRate> ioRates = new ArrayList<>();

            for (HWDiskStore disk : hardware.getDiskStores()) {
                disk.updateAttributes();

                String diskName = disk.getName();
                long readBytes = disk.getReadBytes();
                long writeBytes = disk.getWriteBytes();

                DiskIoCounter prevCounter = diskIoCounters.get(diskName);
                long readSpeed = 0L;
                long writeSpeed = 0L;

                if (prevCounter != null) {
                    readSpeed = speedPerSecond(readBytes - prevCounter.readBytes(), intervalSeconds);
                    writeSpeed = speedPerSecond(writeBytes - prevCounter.writeBytes(), intervalSeconds);
                }

                diskIoCounters.put(diskName, new DiskIoCounter(readBytes, writeBytes));

                ioRates.add(DiskDynamicData.DiskIoRate.builder()
                        .name(diskName)
                        .readSpeed(readSpeed)
                        .writeSpeed(writeSpeed)
                        .build());
            }

            lastDiskSampleTime = now;

            return DiskDynamicData.builder()
                    .partitionUsages(partitionUsages)
                    .ioRates(ioRates)
                    .timestamp(now)
                    .build();
        }
    }

    /**
     * 获取网络实时速率。
     * <p>
     * 与磁盘速率同理，网络吞吐属于区间指标，不能直接使用累计字节数当作瞬时速率。
     */
    public List<NetDynamicData> getNetworkDynamic() {
        synchronized (sampleLock) {
            long now = System.currentTimeMillis();

            if (lastNetSampleTime <= 0L) {
                initNetworkBaseline();
                return buildZeroNetworkSnapshots(now);
            }

            long intervalMs = now - lastNetSampleTime;
            if (intervalMs <= 0L) {
                return buildZeroNetworkSnapshots(now);
            }

            double intervalSeconds = intervalMs / 1000d;
            List<NetDynamicData> data = new ArrayList<>();

            for (NetworkIF net : hardware.getNetworkIFs()) {
                net.updateAttributes();

                String ifaceName = net.getName();
                long rxBytes = net.getBytesRecv();
                long txBytes = net.getBytesSent();

                NetIoCounter prevCounter = netIoCounters.get(ifaceName);
                long rxSpeed = 0L;
                long txSpeed = 0L;

                if (prevCounter != null) {
                    rxSpeed = speedPerSecond(rxBytes - prevCounter.rxBytes(), intervalSeconds);
                    txSpeed = speedPerSecond(txBytes - prevCounter.txBytes(), intervalSeconds);
                }

                netIoCounters.put(ifaceName, new NetIoCounter(rxBytes, txBytes));

                data.add(NetDynamicData.builder()
                        .ifaceName(ifaceName)
                        .rxSpeed(rxSpeed)
                        .txSpeed(txSpeed)
                        .rxBytes(rxBytes)
                        .txBytes(txBytes)
                        .timestamp(now)
                        .build());
            }

            lastNetSampleTime = now;
            return data;
        }
    }

    /**
     * 获取最近一个采样区间内最耗 CPU 的前 N 个进程。
     * <p>
     * 这里不再使用“进程生命周期累计 CPU 占比”直接展示，
     * 而是优先使用 between ticks 的区间值。
     * <br/>
     * 这样更符合监控面板对“最近一段时间谁最忙”的认知。
     */
    public List<ProcessData> getTopProcesses() {
        synchronized (sampleLock) {
            int processLimit = Math.max(os.getProcessCount(), TOP_PROCESS_LIMIT);
            List<OSProcess> processes = os.getProcesses(null, OperatingSystem.ProcessSorting.PID_ASC, processLimit);
            Map<Integer, OSProcess> currentProcessMap = new HashMap<>(processes.size());
            List<ProcessData> processDataList = new ArrayList<>(processes.size());

            for (OSProcess process : processes) {
                if (process.getProcessID() <= 0) {
                    continue;
                }

                currentProcessMap.put(process.getProcessID(), process);

                OSProcess previousProcess = prevProcessMap.get(process.getProcessID());
                double cpuUsage = previousProcess == null
                        ? process.getProcessCpuLoadCumulative()
                        : process.getProcessCpuLoadBetweenTicks(previousProcess);

                if (!Double.isFinite(cpuUsage) || cpuUsage < 0d) {
                    cpuUsage = 0d;
                }

                processDataList.add(ProcessData.builder()
                        .pid(process.getProcessID())
                        .name(process.getName())
                        .cpuUsage(round(cpuUsage * 100))
                        .memUsage(process.getResidentSetSize())
                        .path(process.getPath())
                        .uptime(process.getUpTime())
                        .build());
            }

            prevProcessMap.clear();
            prevProcessMap.putAll(currentProcessMap);

            Comparator<ProcessData> comparator = Comparator
                    .comparing(ProcessData::getCpuUsage, Comparator.nullsLast(Double::compareTo))
                    .thenComparing(ProcessData::getMemUsage, Comparator.nullsLast(Long::compareTo))
                    .reversed();

            return processDataList.stream()
                    .sorted(comparator)
                    .limit(TOP_PROCESS_LIMIT)
                    .collect(Collectors.toList());
        }
    }

    /**
     * 获取其他系统监控指标。
     * <p>
     * 这里重点修正 TCP 字段语义：
     * <br/>
     * - established 表示当前已建立连接数；
     * <br/>
     * - activeOpens / passiveOpens 表示累计主动打开/被动打开次数；
     * <br/>
     * 不再把“active opens”误标成“listening”。
     */
    public OtherMetricsData getOtherMetrics() {
        FileSystem fileSystem = os.getFileSystem();
        InternetProtocolStats ipStats = os.getInternetProtocolStats();
        InternetProtocolStats.TcpStats tcpStats = ipStats.getTCPv4Stats();

        List<OtherMetricsData.GpuInfo> gpuInfos = hardware.getGraphicsCards().stream()
                .map(this::buildGpuInfo)
                .collect(Collectors.toList());

        return OtherMetricsData.builder()
                .openFileDescriptors(fileSystem.getOpenFileDescriptors())
                .maxFileDescriptors(fileSystem.getMaxFileDescriptors())
                .processCount(os.getProcessCount())
                .threadCount(os.getThreadCount())
                .tcpEstablished(tcpStats.getConnectionsEstablished())
                .tcpActiveOpens(tcpStats.getConnectionsActive())
                .tcpPassiveOpens(tcpStats.getConnectionsPassive())
                .gpus(gpuInfos)
                .build();
    }

    /**
     * 初始化 CPU 采样基线。
     * <p>
     * 只记录基线，不计算结果。
     */
    private void initCpuBaseline() {
        CentralProcessor processor = hardware.getProcessor();
        prevCpuTicks = processor.getSystemCpuLoadTicks();
        prevProcessorTicks = processor.getProcessorCpuLoadTicks();
    }

    /**
     * 初始化磁盘采样基线。
     */
    private void initDiskBaseline() {
        diskIoCounters.clear();

        for (HWDiskStore disk : hardware.getDiskStores()) {
            disk.updateAttributes();
            diskIoCounters.put(disk.getName(), new DiskIoCounter(disk.getReadBytes(), disk.getWriteBytes()));
        }

        lastDiskSampleTime = System.currentTimeMillis();
    }

    /**
     * 初始化网络采样基线。
     */
    private void initNetworkBaseline() {
        netIoCounters.clear();

        for (NetworkIF net : hardware.getNetworkIFs()) {
            net.updateAttributes();
            netIoCounters.put(net.getName(), new NetIoCounter(net.getBytesRecv(), net.getBytesSent()));
        }

        lastNetSampleTime = System.currentTimeMillis();
    }

    /**
     * 初始化进程采样基线。
     */
    private void initProcessBaseline() {
        prevProcessMap.clear();

        int processLimit = Math.max(os.getProcessCount(), TOP_PROCESS_LIMIT);
        for (OSProcess process : os.getProcesses(null, OperatingSystem.ProcessSorting.PID_ASC, processLimit)) {
            prevProcessMap.put(process.getProcessID(), process);
        }
    }

    /**
     * 过滤只包含回环地址的网卡。
     * <p>
     * 当前 OSHI 版本没有直接暴露 isLoopback()，因此这里通过地址内容兜底判断。
     */
    private boolean isLoopbackAddressOnly(String[] ipv4, String[] ipv6) {
        boolean hasIpv4 = ipv4 != null && ipv4.length > 0;
        boolean hasIpv6 = ipv6 != null && ipv6.length > 0;

        boolean onlyLoopbackIpv4 = !hasIpv4 || Arrays.stream(ipv4).allMatch(ip -> ip != null && ip.startsWith("127."));
        boolean onlyLoopbackIpv6 = !hasIpv6 || Arrays.stream(ipv6).allMatch(ip -> "::1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip));

        return (hasIpv4 || hasIpv6) && onlyLoopbackIpv4 && onlyLoopbackIpv6;
    }

    /**
     * 组装分区占用率信息。
     */
    private DiskDynamicData.PartitionUsage buildPartitionUsage(OSFileStore fileStore) {
        long total = fileStore.getTotalSpace();
        long free = fileStore.getFreeSpace();
        long used = Math.max(0L, total - free);

        return DiskDynamicData.PartitionUsage.builder()
                .mount(fileStore.getMount())
                .free(free)
                .used(used)
                .usageRate(calculateRate(used, total))
                .build();
    }

    /**
     * 当时间窗口非法时，仍然返回当前累计字节数，但速率强制归零。
     * <p>
     * 这样前端不会收到离谱的大值，也能知道当前累计流量。
     */
    private List<NetDynamicData> buildZeroNetworkSnapshots(long now) {
        List<NetDynamicData> data = new ArrayList<>();

        for (NetworkIF net : hardware.getNetworkIFs()) {
            net.updateAttributes();
            netIoCounters.put(net.getName(), new NetIoCounter(net.getBytesRecv(), net.getBytesSent()));

            data.add(NetDynamicData.builder()
                    .ifaceName(net.getName())
                    .rxSpeed(0L)
                    .txSpeed(0L)
                    .rxBytes(net.getBytesRecv())
                    .txBytes(net.getBytesSent())
                    .timestamp(now)
                    .build());
        }

        lastNetSampleTime = now;
        return data;
    }

    /**
     * 当时间窗口非法时，磁盘速率统一归零。
     */
    private List<DiskDynamicData.DiskIoRate> buildZeroDiskIoRates() {
        List<DiskDynamicData.DiskIoRate> ioRates = new ArrayList<>();

        for (HWDiskStore disk : hardware.getDiskStores()) {
            disk.updateAttributes();
            diskIoCounters.put(disk.getName(), new DiskIoCounter(disk.getReadBytes(), disk.getWriteBytes()));

            ioRates.add(DiskDynamicData.DiskIoRate.builder()
                    .name(disk.getName())
                    .readSpeed(0L)
                    .writeSpeed(0L)
                    .build());
        }

        lastDiskSampleTime = System.currentTimeMillis();
        return ioRates;
    }

    /**
     * 构建 GPU 信息。
     * <p>
     * OSHI 对 GPU 动态指标支持有限，所以这里只输出稳定可得的信息。
     */
    private OtherMetricsData.GpuInfo buildGpuInfo(GraphicsCard card) {
        return OtherMetricsData.GpuInfo.builder()
                .name(card.getName())
                .memoryTotal(card.getVRam())
                .memoryUsed(null)
                .temperature(null)
                .build();
    }

    /**
     * 统一处理 load average。
     * <p>
     * 某些平台不支持该指标时会返回负数，统一转成 0，避免前端误判。
     */
    private double[] normalizeLoadAverage(double[] values) {
        if (values == null || values.length == 0) {
            return new double[]{0d, 0d, 0d};
        }

        double[] normalized = Arrays.copyOf(values, values.length);
        for (int i = 0; i < normalized.length; i++) {
            normalized[i] = normalized[i] < 0d || !Double.isFinite(normalized[i]) ? 0d : round(normalized[i]);
        }
        return normalized;
    }

    /**
     * 计算指定 tick 类型的差值。
     */
    private long delta(long[] current, long[] previous, CentralProcessor.TickType tickType) {
        int index = tickType.getIndex();
        return Math.max(0L, current[index] - previous[index]);
    }

    /**
     * 根据累计值差异计算每秒速率。
     * <p>
     * 这里统一做了三层保护：
     * <br/>
     * 1. 时间差必须大于 0；
     * <br/>
     * 2. 字节差不能为负；
     * <br/>
     * 3. 最终结果向最近整数取整。
     */
    private long speedPerSecond(long deltaBytes, double intervalSeconds) {
        if (intervalSeconds <= 0d || deltaBytes <= 0L) {
            return 0L;
        }
        return Math.max(0L, Math.round(deltaBytes / intervalSeconds));
    }

    /**
     * 计算百分比。
     */
    private double calculateRate(long used, long total) {
        if (used <= 0L || total <= 0L) {
            return 0d;
        }
        return round(used * 100d / total);
    }

    /**
     * 统一保留两位小数。
     * <p>
     * 不使用 DecimalFormat 的原因是它不是线程安全的，
     * 作为单例 Service 的共享静态对象会带来并发隐患。
     */
    private double round(double value) {
        return Math.round(value * SCALE) / (double) SCALE;
    }

    /**
     * 解析更适合作为“内核/系统构建标识”的字段。
     * <p>
     * 不同操作系统暴露的版本结构并不一致：
     * <br/>
     * - Linux/macOS 更适合优先看 version；
     * <br/>
     * - Windows build number 也有价值；
     * <br/>
     * 因此这里优先选非空 version，再兜底 codeName/buildNumber。
     */
    private String resolveKernelVersion() {
        String version = os.getVersionInfo().getVersion();
        if (version != null && !version.isBlank()) {
            return version;
        }

        String codeName = os.getVersionInfo().getCodeName();
        if (codeName != null && !codeName.isBlank()) {
            return codeName;
        }

        return Objects.toString(os.getVersionInfo().getBuildNumber(), "");
    }

    /**
     * 磁盘累计读写字节快照。
     */
    private record DiskIoCounter(long readBytes, long writeBytes) {
    }

    /**
     * 网卡累计收发字节快照。
     */
    private record NetIoCounter(long rxBytes, long txBytes) {
    }
}
