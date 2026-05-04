package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * JVM GC 信息。
 */
@Data
@Builder
@Schema(description = "JVM垃圾回收器信息")
public class JvmGarbageCollectorData {

    @Schema(description = "垃圾回收器名称", example = "G1 Young Generation")
    private String name;

    @Schema(description = "累计GC次数")
    private Long collectionCount;

    @Schema(description = "累计GC耗时(毫秒)")
    private Long collectionTime;

    @Schema(description = "当前GC管理的内存池")
    private List<String> memoryPoolNames;

    @Schema(description = "GC是否有效")
    private Boolean valid;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
