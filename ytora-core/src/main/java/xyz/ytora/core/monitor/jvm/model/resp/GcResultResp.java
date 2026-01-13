package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * created by yangtong on 2025/7/6 21:49:12
 * <br/>
 */
@Data
@Builder
@Schema(description = "垃圾回收执行结果")
public class GcResultResp {

    @Schema(description = "执行是否成功", example = "true")
    private Boolean success;

    @Schema(description = "执行耗时（毫秒）", example = "156")
    private Long duration;

    @Schema(description = "GC前内存使用量（字节）", example = "536870912")
    private Long memoryBefore;

    @Schema(description = "GC后内存使用量（字节）", example = "268435456")
    private Long memoryAfter;

    @Schema(description = "释放的内存量（字节）", example = "268435456")
    private Long memoryReleased;

    @Schema(description = "释放的内存量（MB）", example = "256")
    private Long memoryReleasedMB;

    @Schema(description = "错误信息（如果执行失败）")
    private String error;
}
