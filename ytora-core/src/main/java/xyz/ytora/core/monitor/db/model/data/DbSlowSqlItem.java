package xyz.ytora.core.monitor.db.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 慢 SQL。
 */
@Data
@Builder
@Schema(description = "慢SQL")
public class DbSlowSqlItem {

    @Schema(description = "记录时间戳")
    private Long timestamp;

    @Schema(description = "SQL类型")
    private String sqlType;

    @Schema(description = "执行耗时(毫秒)")
    private Long elapsedMillis;

    @Schema(description = "SQL文本")
    private String sql;

    @Schema(description = "参数文本")
    private String paramsText;

    @Schema(description = "异常类名")
    private String exceptionClass;

    @Schema(description = "异常信息")
    private String exceptionMsg;

    @Schema(description = "是否成功")
    private Boolean success;
}
