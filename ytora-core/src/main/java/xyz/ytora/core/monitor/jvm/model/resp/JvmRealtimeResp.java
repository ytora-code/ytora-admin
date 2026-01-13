package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * created by yangtong on 2025/7/6 20:56:24
 * <br/>
 */
@Data
@Builder
@Schema(description = "JVM运行时的动态信息")
public class JvmRealtimeResp {

    @Schema(description = "数据采集时间戳", example = "1699123456789")
    private Long timestamp;

    @Schema(description = "已提交堆内存（字节数）", example = "1024")
    private Long committedHeapMemory;

    @Schema(description = "已提交堆内存", example = "2.0 GB")
    private String committedHeapMemoryStr;

    @Schema(description = "已使用堆内存（字节数）", example = "1024")
    private Long usedHeapMemory;

    @Schema(description = "已使用堆内存", example = "2.0 GB")
    private String usedHeapMemoryStr;

    @Schema(description = "空闲堆内存（字节数）", example = "1024")
    private Long freeHeapMemory;

    @Schema(description = "堆内存使用率（%）", example = "25.0", minimum = "0", maximum = "100")
    private BigDecimal heapMemoryUsageRate;

    @Schema(description = "已提交非堆内存（字节数）", example = "1024")
    private Long committedNonHeapMemory;

    @Schema(description = "已提交非堆内存", example = "2.0 GB")
    private String committedNonHeapMemoryStr;

    @Schema(description = "已使用非堆内存（字节数）", example = "1024")
    private Long usedNonHeapMemory;

    @Schema(description = "已使用非堆内存）", example = "2.0 GB")
    private String usedNonHeapMemoryStr;


    @Schema(description = "空闲非堆内存（字节数）", example = "1024")
    private Long freeNonHeapMemory;

    @Schema(description = "非堆内存使用率（%）", example = "25.0", minimum = "0", maximum = "100")
    private BigDecimal nonHeapMemoryUsageRate;

    @Schema(description = "当前线程数", example = "156")
    private Integer threadCount;

    @Schema(description = "数据库活跃连接数", example = "8")
    private Integer activeConnections;

    @Schema(description = "数据库连接池使用率（%）", example = "40.0", minimum = "0", maximum = "100")
    private Double connectionUsage;

    @Schema(description = "JVM运行时长（毫秒）", example = "3600000")
    private Long uptime;

    @Schema(description = "JVM运行时长）", example = "2天3小时45分钟12秒")
    private String formattedUptime;
}