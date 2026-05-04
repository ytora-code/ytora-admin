package xyz.ytora.core.monitor.db.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 数据源静态信息。
 */
@Data
@Builder
@Schema(description = "数据源静态信息")
public class DbStaticData {

    @Schema(description = "主数据源Key")
    private String primaryKey;

    @Schema(description = "数据源列表")
    private List<DbDataSourceItem> dataSources;
}
