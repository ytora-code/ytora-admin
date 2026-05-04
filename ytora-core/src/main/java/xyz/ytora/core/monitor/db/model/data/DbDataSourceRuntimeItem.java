package xyz.ytora.core.monitor.db.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 数据源运行状态项。
 */
@Data
@Builder
@Schema(description = "数据源运行状态项")
public class DbDataSourceRuntimeItem {

    @Schema(description = "数据源名称")
    private String name;

    @Schema(description = "连接池类型")
    private String poolType;

    @Schema(description = "当前活跃连接数")
    private Integer activeConnections;

    @Schema(description = "当前空闲连接数")
    private Integer idleConnections;

    @Schema(description = "当前总连接数")
    private Integer totalConnections;

    @Schema(description = "等待连接线程数")
    private Integer threadsAwaitingConnection;

    @Schema(description = "JDBC地址")
    private String jdbcUrl;

    @Schema(description = "数据库产品名称")
    private String databaseProductName;
}
