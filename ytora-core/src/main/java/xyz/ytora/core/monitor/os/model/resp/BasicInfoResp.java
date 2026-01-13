package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * created by YT on 2026/1/13 22:19:59
 * <br/>
 */
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "操作系统基本信息")
public class BasicInfoResp {

    /* ========= 主机 ========= */

    @Schema(description = "主机名称", example = "zhangsan-pc")
    private String hostName;

    @Schema(description = "主机IP地址列表", example = "[\"192.168.1.10\", \"10.0.0.5\"]")
    private List<String> ipAddresses;

    @Schema(description = "MAC地址列表")
    private List<String> macAddresses;

    /* ========= 操作系统 ========= */

    @Schema(description = "操作系统名称", example = "Linux")
    private String osName;

    @Schema(description = "操作系统版本", example = "5.15.0-86-generic")
    private String osVersion;

    @Schema(description = "操作系统发行版", example = "Ubuntu 22.04 LTS")
    private String osDescription;

    @Schema(description = "内核版本", example = "5.15.0")
    private String kernelVersion;

    @Schema(description = "系统架构", example = "x86_64")
    private String arch;


    /* ========= 时间相关 ========= */

    @Schema(description = "系统启动时间（毫秒时间戳）")
    private Long bootTime;

    @Schema(description = "系统运行时长（秒）")
    private Long uptime;

    @Schema(description = "系统时区", example = "Asia/Shanghai")
    private String timeZone;
}

