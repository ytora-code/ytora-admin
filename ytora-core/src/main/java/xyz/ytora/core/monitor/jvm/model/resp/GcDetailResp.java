package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * created by yangtong on 2025/7/6 20:58:32
 * <br/>
 */
@Data
@Builder
@Schema(description = "垃圾回收器详细信息")
public class GcDetailResp {
    @Schema(description = "垃圾回收器名称", example = "G1 Young Generation")
    private String name;

    @Schema(description = "回收次数", example = "1000")
    private Long collectionCount;

    @Schema(description = "回收总耗时（毫秒）", example = "1500")
    private Long collectionTime;
}
