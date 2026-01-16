package xyz.ytora.core.sys.db.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sql4j.enums.DbType;

/**
 * created by YT on 2026/1/17 02:41:19
 * <br/>
 */
@Data
@Schema(description = "数据源基础信息")
public class DataSourceDesc {

    @Schema(description = "数据源名称")
    private String name;

    @Schema(description = "数据源描述")
    private String desc;

    @Schema(description = "数据源类型")
    private String dsType;

    @Schema(description = "数据库产品")
    private String dbType;

    @Schema(description = "当前数据源连接的数据库")
    private String database;

    @Schema(description = "当前数据源连接的schema")
    private String schema;
}
