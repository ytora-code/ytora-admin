package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "JVM基本信息")
public class JvmBasicResp {
    @Schema(description = "Java版本", example = "17.0.1")
    private String version;

    @Schema(description = "Java厂商", example = "Eclipse Adoptium")
    private String vendor;

    @Schema(description = "Jvm名称", example = "OpenJDK 64-Bit Server VM、Java HotSpot(TM) 64-Bit Server VM")
    private String jvmName;

    @Schema(description = "JVM启动时间", example = "2025-01-01 10:00:00")
    private String startTime;

    @Schema(description = "进程ID", example = "114514")
    private String pid;

    @Schema(description = "工作目录", example = "/home/user/myapp")
    private String workingDirectory;

    @Schema(description = "用户名", example = "root")
    private String username;

    @Schema(description = "最大堆内存", example = "2.00 GB")
    private String maxHeapMemory;

    @Schema(description = "初始堆内存", example = "512 MB")
    private String initHeapMemory;

    @Schema(description = "最大非堆内存", example = "512 MB")
    private String maxNonHeapMemory;

    @Schema(description = "初始非堆内存", example = "512 MB")
    private String initNonHeapMemory;

    @Schema(description = "最大直接内存", example = "512 MB")
    private String maxDirectMemory;

}