package xyz.ytora.core.monitor.db.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 数据库动态监控信息。
 */
@Data
@Builder
@Schema(description = "数据库动态监控信息")
public class DbDynamicData {

    @Schema(description = "累计SQL总数")
    private Long totalSqlCount;

    @Schema(description = "累计成功SQL数")
    private Long successSqlCount;

    @Schema(description = "累计失败SQL数")
    private Long failureSqlCount;

    @Schema(description = "SQL平均耗时(毫秒)")
    private Double averageDurationMs;

    @Schema(description = "SQL最大耗时(毫秒)")
    private Long maxDurationMs;

    @Schema(description = "累计慢SQL数量")
    private Long slowSqlCount;

    @Schema(description = "慢SQL阈值(毫秒)")
    private Long slowSqlThreshold;

    @Schema(description = "最近慢SQL列表")
    private List<DbSlowSqlItem> recentSlowSqls;

    @Schema(description = "热点慢SQL排行")
    private List<DbHotSlowSqlItem> hotSlowSqls;

    @Schema(description = "数据源运行状态")
    private List<DbDataSourceRuntimeItem> dataSources;

    @Schema(description = "采样时间戳")
    private Long timestamp;
}
