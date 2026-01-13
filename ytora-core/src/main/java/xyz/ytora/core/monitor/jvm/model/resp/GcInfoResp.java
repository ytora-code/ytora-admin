package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * created by yangtong on 2025/7/6 20:58:01
 * <br/>
 */
@Data
@Builder
@Schema(description = "垃圾回收统计信息")
public class GcInfoResp {
    @Schema(description = "总GC次数", example = "1250")
    private Long totalGcCount;

    @Schema(description = "总GC耗时（毫秒）", example = "2340")
    private Long totalGcTime;

    @Schema(description = "各垃圾回收器详细信息")
    private List<GcDetailResp> gcDetailResps;
}
