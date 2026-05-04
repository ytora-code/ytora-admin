package xyz.ytora.core.monitor.jvm.logic;

import org.springframework.stereotype.Service;
import xyz.ytora.core.monitor.jvm.model.data.JvmBasicInfoData;
import xyz.ytora.core.monitor.jvm.model.data.JvmClassLoadingData;
import xyz.ytora.core.monitor.jvm.model.data.JvmCompilationData;
import xyz.ytora.core.monitor.jvm.model.data.JvmGarbageCollectorData;
import xyz.ytora.core.monitor.jvm.model.data.JvmMemoryData;
import xyz.ytora.core.monitor.jvm.model.data.JvmRuntimeData;
import xyz.ytora.core.monitor.jvm.model.data.JvmThreadData;
import xyz.ytora.toolkit.text.Strs;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by YT on 2026/4/24
 * <br/>
 * JVM 监控逻辑。
 * <p>
 * 和 OS 监控不同，JVM 监控绝大多数指标都可以直接通过 JMX / MXBean 读取，
 * 不需要自己维护复杂的前后采样窗口。
 * <br/>
 * 因此本类的核心目标不是“做差值计算”，而是：
 * <br/>
 * 1. 统一封装 JVM MXBean；
 * <br/>
 * 2. 把零散的 JMX 指标整理成前端可直接消费的 DTO；
 * <br/>
 * 3. 明确字段语义，避免把累计值、当前值、阈值混在一起。
 */
@Service
public class JvmLogic {

    /**
     * JVM 运行时总览入口。
     */
    private final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

    /**
     * JVM 内存入口，负责堆/非堆/finalizer queue 等指标。
     */
    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * JVM 线程入口，负责线程数量、死锁检测、线程状态分布等指标。
     */
    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    /**
     * JVM 类加载入口。
     */
    private final ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();

    /**
     * JVM JIT 编译器入口。
     */
    private final CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();

    /**
     * 获取 JVM 基础信息。
     * <p>
     * 这里返回的是 JVM 实例的身份、版本、供应商、启动参数等稳定信息，
     * 适合在监控页面初始化时加载一次。
     */
    public JvmBasicInfoData getBasicInfo() {
        return JvmBasicInfoData.builder()
                .vmName(runtimeMXBean.getVmName())
                .vmVendor(runtimeMXBean.getVmVendor())
                .vmVersion(runtimeMXBean.getVmVersion())
                .specName(runtimeMXBean.getSpecName())
                .specVendor(runtimeMXBean.getSpecVendor())
                .specVersion(runtimeMXBean.getSpecVersion())
                .managementSpecVersion(runtimeMXBean.getManagementSpecVersion())
                .javaHome(System.getProperty("java.home"))
                .javaVersion(System.getProperty("java.version"))
                .javaVendor(System.getProperty("java.vendor"))
                .javaVmInfo(System.getProperty("java.vm.info"))
                .inputArguments(runtimeMXBean.getInputArguments())
                .bootClassPathSupported(runtimeMXBean.isBootClassPathSupported())
                .systemPropertiesCount(runtimeMXBean.getSystemProperties().size())
                .startTime(runtimeMXBean.getStartTime())
                .build();
    }

    /**
     * 获取 JVM 运行时信息。
     * <p>
     * 这一部分强调“当前运行状态”，例如：
     * <br/>
     * 1. JVM 已运行多久；
     * <br/>
     * 2. 进程名称、PID；
     * <br/>
     * 3. 可用处理器数和系统负载；
     * <br/>
     * 4. 当前默认字符集、时区、工作目录。
     */
    public JvmRuntimeData getRuntime() {
        Runtime runtime = Runtime.getRuntime();
        long now = System.currentTimeMillis();

        return JvmRuntimeData.builder()
                .name(runtimeMXBean.getName())
                .pid(runtimeMXBean.getPid())
                .uptime(runtimeMXBean.getUptime())
                .startTime(runtimeMXBean.getStartTime())
                .systemLoadAverage(round(ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage()))
                .availableProcessors(runtime.availableProcessors())
                .shutdownHooksSupported(true)
                .libraryPath(System.getProperty("java.library.path"))
                .classPath(runtimeMXBean.getClassPath())
                .bootClassPath(safeBootClassPath())
                .timezone(ZoneId.systemDefault().getId())
                .charset(Charset.defaultCharset().name())
                .userDir(System.getProperty("user.dir"))
                .timestamp(now)
                .build();
    }

    /**
     * 获取 JVM 内存信息。
     * <p>
     * 该接口包含四部分：
     * <br/>
     * 1. 堆内存；
     * <br/>
     * 2. 非堆内存；
     * <br/>
     * 3. 各个 Memory Pool；
     * <br/>
     * 4. NIO Buffer Pool。
     * <p>
     * 这样设计的目的是让使用者可以先看总览，再下钻到具体内存池。
     */
    public JvmMemoryData getMemory() {
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryMXBean.getNonHeapMemoryUsage();

        List<JvmMemoryData.MemoryPoolItem> memoryPools = ManagementFactory.getMemoryPoolMXBeans().stream()
                .map(this::buildMemoryPoolItem)
                .collect(Collectors.toList());

        List<JvmMemoryData.BufferPoolItem> bufferPools = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class).stream()
                .map(this::buildBufferPoolItem)
                .collect(Collectors.toList());

        return JvmMemoryData.builder()
                .heap(buildMemoryArea(heapUsage))
                .nonHeap(buildMemoryArea(nonHeapUsage))
                .objectPendingFinalizationCount(memoryMXBean.getObjectPendingFinalizationCount())
                .memoryPools(memoryPools)
                .bufferPools(bufferPools)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取 JVM 线程信息。
     * <p>
     * 线程是 Java 应用问题最常见的来源之一，所以这里除了总量信息，
     * 还额外提供线程状态分布和死锁线程 ID。
     */
    public JvmThreadData getThread() {
        long[] allThreadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(allThreadIds, 0);

        Map<String, Integer> threadStateCounts = new LinkedHashMap<>();
        for (Thread.State state : Thread.State.values()) {
            threadStateCounts.put(state.name(), 0);
        }

        for (ThreadInfo threadInfo : threadInfos) {
            if (threadInfo == null) {
                continue;
            }
            String stateName = threadInfo.getThreadState().name();
            threadStateCounts.computeIfPresent(stateName, (key, value) -> value + 1);
        }

        long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();

        return JvmThreadData.builder()
                .threadCount(threadMXBean.getThreadCount())
                .daemonThreadCount(threadMXBean.getDaemonThreadCount())
                .peakThreadCount(threadMXBean.getPeakThreadCount())
                .startedThreadCount(threadMXBean.getTotalStartedThreadCount())
                .deadlockedThreadCount(deadlockedThreadIds == null ? 0 : deadlockedThreadIds.length)
                .deadlockedThreadIds(deadlockedThreadIds == null ? List.of() : Arrays.stream(deadlockedThreadIds).boxed().collect(Collectors.toList()))
                .threadStateCounts(threadStateCounts)
                .currentThreadCpuTimeSupported(threadMXBean.isCurrentThreadCpuTimeSupported())
                .threadContentionMonitoringSupported(threadMXBean.isThreadContentionMonitoringSupported())
                .threadCpuTimeEnabled(threadMXBean.isThreadCpuTimeEnabled())
                .threadContentionMonitoringEnabled(threadMXBean.isThreadContentionMonitoringEnabled())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取 GC 信息。
     * <p>
     * GC 指标天然是“累计值”：
     * <br/>
     * - collectionCount 表示累计回收次数；
     * <br/>
     * - collectionTime 表示累计耗时。
     * <p>
     * 前端如果需要“最近一分钟 GC 次数/耗时”，应基于这里的累计值自行做差。
     */
    public List<JvmGarbageCollectorData> getGarbageCollectors() {
        return ManagementFactory.getGarbageCollectorMXBeans().stream()
                .map(this::buildGarbageCollectorData)
                .collect(Collectors.toList());
    }

    /**
     * 获取类加载信息。
     */
    public JvmClassLoadingData getClassLoading() {
        return JvmClassLoadingData.builder()
                .loadedClassCount(classLoadingMXBean.getLoadedClassCount())
                .unloadedClassCount(classLoadingMXBean.getUnloadedClassCount())
                .totalLoadedClassCount(classLoadingMXBean.getTotalLoadedClassCount())
                .verbose(classLoadingMXBean.isVerbose())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 获取 JIT 编译器信息。
     * <p>
     * 某些极简运行时可能不支持编译耗时统计，因此这里需要显式判断 supported。
     */
    public JvmCompilationData getCompilation() {
        boolean supported = compilationMXBean.isCompilationTimeMonitoringSupported();

        return JvmCompilationData.builder()
                .compilerName(compilationMXBean.getName())
                .compilationTimeMonitoringSupported(supported)
                .totalCompilationTime(supported ? compilationMXBean.getTotalCompilationTime() : null)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 构建 GC DTO。
     * <p>
     * 这里会把 GC 管理的内存池一起带出去，方便排查新生代/老年代回收器的职责边界。
     */
    private JvmGarbageCollectorData buildGarbageCollectorData(GarbageCollectorMXBean gcBean) {
        return JvmGarbageCollectorData.builder()
                .name(gcBean.getName())
                .collectionCount(gcBean.getCollectionCount())
                .collectionTime(gcBean.getCollectionTime())
                .memoryPoolNames(Arrays.asList(gcBean.getMemoryPoolNames()))
                .valid(gcBean.isValid())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 构建统一的内存区域视图。
     * <p>
     * 由于 MemoryUsage 对象同时适用于堆、非堆和内存池，
     * 因此抽成统一方法可以减少重复代码。
     */
    private JvmMemoryData.MemoryArea buildMemoryArea(MemoryUsage usage) {
        long init = normalizeUsageValue(usage.getInit());
        long used = normalizeUsageValue(usage.getUsed());
        long committed = normalizeUsageValue(usage.getCommitted());
        long max = normalizeUsageValue(usage.getMax());

        return JvmMemoryData.MemoryArea.builder()
                .init(init)
                .used(used)
                .committed(committed)
                .max(max)
                .usedRate(calculateRate(used, max))
                .committedRate(calculateRate(committed, max))
                .usedText(Strs.formatSize(used))
                .committedText(Strs.formatSize(committed))
                .maxText(max <= 0 ? null : Strs.formatSize(max))
                .build();
    }

    /**
     * 构建内存池明细。
     * <p>
     * MemoryPool 可以对应 Eden、Survivor、Old Gen、Metaspace、Code Cache 等具体区域，
     * 它们比 heap / non-heap 更接近 JVM 调优时真正关心的颗粒度。
     */
    private JvmMemoryData.MemoryPoolItem buildMemoryPoolItem(MemoryPoolMXBean poolBean) {
        MemoryUsage usage = poolBean.getUsage();
        MemoryUsage peakUsage = poolBean.getPeakUsage();
        MemoryUsage collectionUsage = poolBean.getCollectionUsage();

        return JvmMemoryData.MemoryPoolItem.builder()
                .name(poolBean.getName())
                .type(poolBean.getType() == MemoryType.HEAP ? "HEAP" : "NON_HEAP")
                .usage(usage == null ? null : buildMemoryArea(usage))
                .peakUsage(peakUsage == null ? null : buildMemoryArea(peakUsage))
                .collectionUsage(collectionUsage == null ? null : buildMemoryArea(collectionUsage))
                .managerNames(Arrays.asList(poolBean.getMemoryManagerNames()))
                .usageThresholdSupported(poolBean.isUsageThresholdSupported())
                .collectionUsageThresholdSupported(poolBean.isCollectionUsageThresholdSupported())
                .valid(poolBean.isValid())
                .build();
    }

    /**
     * 构建 NIO Buffer Pool 明细。
     * <p>
     * DirectBuffer 泄漏排查时，这部分数据非常有用。
     */
    private JvmMemoryData.BufferPoolItem buildBufferPoolItem(BufferPoolMXBean poolBean) {
        return JvmMemoryData.BufferPoolItem.builder()
                .name(poolBean.getName())
                .count(poolBean.getCount())
                .memoryUsed(poolBean.getMemoryUsed())
                .totalCapacity(poolBean.getTotalCapacity())
                .memoryUsedText(Strs.formatSize(Math.max(0L, poolBean.getMemoryUsed())))
                .totalCapacityText(Strs.formatSize(Math.max(0L, poolBean.getTotalCapacity())))
                .build();
    }

    /**
     * 安全读取 BootClassPath。
     * <p>
     * 现代 JDK 很多实现已经不再支持 boot class path，直接读取会抛异常。
     * 因此必须先判断是否支持。
     */
    private String safeBootClassPath() {
        if (!runtimeMXBean.isBootClassPathSupported()) {
            return null;
        }
        return runtimeMXBean.getBootClassPath();
    }

    /**
     * 统一处理 JMX 中“未知值”的表示。
     * <p>
     * 很多 MXBean 在无法提供上限时会返回 -1，
     * 这里统一转成 0，避免前端把 -1 当成真实字节数。
     */
    private long normalizeUsageValue(long value) {
        return Math.max(value, 0L);
    }

    /**
     * 百分比统一保留两位小数。
     */
    private double calculateRate(long numerator, long denominator) {
        if (numerator <= 0L || denominator <= 0L) {
            return 0d;
        }
        return round(numerator * 100d / denominator);
    }

    /**
     * 对浮点结果做统一收口，避免接口返回太长小数。
     */
    private double round(double value) {
        if (!Double.isFinite(value) || value < 0d) {
            return 0d;
        }
        return Math.round(value * 100d) / 100d;
    }
}
