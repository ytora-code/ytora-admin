package xyz.ytora.core.monitor.db.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 热点慢 SQL。
 */
@Data
@Builder
@Schema(description = "热点慢SQL")
public class DbHotSlowSqlItem {

    @Schema(description = "SQL指纹")
    private String fingerprint;

    @Schema(description = "SQL类型")
    private String sqlType;

    @Schema(description = "代表性SQL文本")
    private String sampleSql;

    @Schema(description = "最近一次参数文本")
    private String sampleParamsText;

    @Schema(description = "命中次数")
    private Long count;

    @Schema(description = "平均耗时(毫秒)")
    private Double averageDurationMs;

    @Schema(description = "最大耗时(毫秒)")
    private Long maxDurationMs;

    @Schema(description = "首次出现时间戳")
    private Long firstSeenTime;

    @Schema(description = "最近出现时间戳")
    private Long lastSeenTime;

    @Schema(description = "综合评分")
    private Double score;
}
