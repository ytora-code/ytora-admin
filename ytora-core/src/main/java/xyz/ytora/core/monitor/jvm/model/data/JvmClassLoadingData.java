package xyz.ytora.core.monitor.jvm.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * JVM 类加载信息。
 */
@Data
@Builder
@Schema(description = "JVM类加载信息")
public class JvmClassLoadingData {

    @Schema(description = "当前已加载类数量")
    private Integer loadedClassCount;

    @Schema(description = "累计已卸载类数量")
    private Long unloadedClassCount;

    @Schema(description = "JVM启动以来累计加载类数量")
    private Long totalLoadedClassCount;

    @Schema(description = "是否开启类加载详细日志")
    private Boolean verbose;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
