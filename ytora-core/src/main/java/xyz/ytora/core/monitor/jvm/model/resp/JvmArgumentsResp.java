package xyz.ytora.core.monitor.jvm.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Schema(description = "JVM启动参数信息")
public class JvmArgumentsResp {

    @Schema(description = "JVM输入参数列表")
    private List<String> allArguments;

    @Schema(description = "系统属性")
    private Map<String, String> systemProperties;

    @Schema(description = "内存相关参数")
    private Map<String, String> memoryParameters;

    @Schema(description = "GC相关参数")
    private Map<String, String> gcParameters;
}
