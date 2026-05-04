package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * JVM 线程信息。
 */
@Data
@Builder
@Schema(description = "JVM线程信息")
public class JvmThreadData {

    @Schema(description = "当前线程总数")
    private Integer threadCount;

    @Schema(description = "守护线程数量")
    private Integer daemonThreadCount;

    @Schema(description = "历史峰值线程数量")
    private Integer peakThreadCount;

    @Schema(description = "JVM启动以来累计启动线程数量")
    private Long startedThreadCount;

    @Schema(description = "死锁线程数量")
    private Integer deadlockedThreadCount;

    @Schema(description = "死锁线程ID列表")
    private List<Long> deadlockedThreadIds;

    @Schema(description = "各线程状态数量分布")
    private Map<String, Integer> threadStateCounts;

    @Schema(description = "是否支持当前线程CPU时间统计")
    private Boolean currentThreadCpuTimeSupported;

    @Schema(description = "是否支持线程竞争监控")
    private Boolean threadContentionMonitoringSupported;

    @Schema(description = "是否启用线程CPU时间统计")
    private Boolean threadCpuTimeEnabled;

    @Schema(description = "是否启用线程竞争监控")
    private Boolean threadContentionMonitoringEnabled;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
