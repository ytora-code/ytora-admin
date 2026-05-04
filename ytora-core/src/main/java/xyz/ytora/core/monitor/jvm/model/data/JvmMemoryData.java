package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * JVM 内存信息。
 */
@Data
@Builder
@Schema(description = "JVM内存信息")
public class JvmMemoryData {

    @Schema(description = "堆内存概览")
    private MemoryArea heap;

    @Schema(description = "非堆内存概览")
    private MemoryArea nonHeap;

    @Schema(description = "等待Finalizer处理的对象数量")
    private Integer objectPendingFinalizationCount;

    @Schema(description = "内存池明细")
    private List<MemoryPoolItem> memoryPools;

    @Schema(description = "NIO Buffer Pool 明细")
    private List<BufferPoolItem> bufferPools;

    @Schema(description = "采样时间戳")
    private Long timestamp;

    @Data
    @Builder
    @Schema(description = "统一内存区域信息")
    public static class MemoryArea {

        @Schema(description = "初始化大小(Bytes)")
        private Long init;

        @Schema(description = "当前已使用大小(Bytes)")
        private Long used;

        @Schema(description = "当前已提交大小(Bytes)")
        private Long committed;

        @Schema(description = "最大可用大小(Bytes)，未知时为0")
        private Long max;

        @Schema(description = "已使用占最大值比例(%)")
        private Double usedRate;

        @Schema(description = "已提交占最大值比例(%)")
        private Double committedRate;

        @Schema(description = "已使用大小文本")
        private String usedText;

        @Schema(description = "已提交大小文本")
        private String committedText;

        @Schema(description = "最大大小文本")
        private String maxText;
    }

    @Data
    @Builder
    @Schema(description = "内存池明细")
    public static class MemoryPoolItem {

        @Schema(description = "内存池名称", example = "G1 Eden Space")
        private String name;

        @Schema(description = "内存池类型", example = "HEAP")
        private String type;

        @Schema(description = "当前使用情况")
        private MemoryArea usage;

        @Schema(description = "峰值使用情况")
        private MemoryArea peakUsage;

        @Schema(description = "最近一次GC后的使用情况")
        private MemoryArea collectionUsage;

        @Schema(description = "关联的内存管理器名称")
        private List<String> managerNames;

        @Schema(description = "是否支持UsageThreshold")
        private Boolean usageThresholdSupported;

        @Schema(description = "是否支持CollectionUsageThreshold")
        private Boolean collectionUsageThresholdSupported;

        @Schema(description = "当前内存池是否有效")
        private Boolean valid;
    }

    @Data
    @Builder
    @Schema(description = "BufferPool 明细")
    public static class BufferPoolItem {

        @Schema(description = "BufferPool名称", example = "direct")
        private String name;

        @Schema(description = "缓冲区数量")
        private Long count;

        @Schema(description = "当前已使用内存(Bytes)")
        private Long memoryUsed;

        @Schema(description = "总容量(Bytes)")
        private Long totalCapacity;

        @Schema(description = "当前已使用内存文本")
        private String memoryUsedText;

        @Schema(description = "总容量文本")
        private String totalCapacityText;
    }
}
