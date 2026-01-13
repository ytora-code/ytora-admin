package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * created by yangtong on 2025/7/6 20:58:52
 * <br/>
 */
@Data
@Builder
@Schema(description = "JVM线程统计信息")
public class ThreadInfoResp {
    @Schema(description = "当前线程总数", example = "156")
    private Integer totalThreads;

    @Schema(description = "活跃线程数", example = "156")
    private Integer activeThreads;

    @Schema(description = "守护线程数", example = "12")
    private Integer daemonThreads;

    @Schema(description = "峰值线程数", example = "180")
    private Integer peakThreads;
}
