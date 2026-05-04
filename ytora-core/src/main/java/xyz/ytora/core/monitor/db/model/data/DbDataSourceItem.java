package xyz.ytora.core.monitor.db.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 数据源配置项。
 */
@Data
@Builder
@Schema(description = "数据源配置项")
public class DbDataSourceItem {

    @Schema(description = "数据源名称")
    private String name;

    @Schema(description = "数据源描述")
    private String description;

    @Schema(description = "驱动类名")
    private String driverClassName;

    @Schema(description = "连接地址")
    private String url;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "数据源类型")
    private String type;
}
