package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * JVM 运行时信息。
 */
@Data
@Builder
@Schema(description = "JVM运行时信息")
public class JvmRuntimeData {

    @Schema(description = "JVM运行时名称，通常包含PID和主机名", example = "12345@localhost")
    private String name;

    @Schema(description = "当前JVM进程PID")
    private Long pid;

    @Schema(description = "JVM运行时长(毫秒)")
    private Long uptime;

    @Schema(description = "JVM启动时间戳")
    private Long startTime;

    @Schema(description = "系统平均负载，部分平台可能为负数或不可用，此处已归一化", example = "1.52")
    private Double systemLoadAverage;

    @Schema(description = "可用处理器数量")
    private Integer availableProcessors;

    @Schema(description = "是否支持ShutdownHook，JVM标准实现中通常为true")
    private Boolean shutdownHooksSupported;

    @Schema(description = "本地库搜索路径")
    private String libraryPath;

    @Schema(description = "应用类路径")
    private String classPath;

    @Schema(description = "BootClassPath，不支持时为null")
    private String bootClassPath;

    @Schema(description = "默认时区")
    private String timezone;

    @Schema(description = "默认字符集")
    private String charset;

    @Schema(description = "当前工作目录")
    private String userDir;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
