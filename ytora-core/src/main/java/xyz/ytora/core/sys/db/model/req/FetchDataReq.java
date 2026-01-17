package xyz.ytora.core.sys.db.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * created by YT on 2026/1/18 00:32:25
 * <br/>
 * 表/视图数据拉取请求
 */
@Data
@Schema(description = "表/视图数据拉取请求")
public class FetchDataReq {
    @Schema(description = "目标数据的数据源")
    private String ds;

    @Schema(description = "目标数据的schema")
    private String schema;

    @Schema(description = "目标数据所在表/视图的名称")
    private String name;

    @Schema(description = "查询条件")
    private String where;

    @Schema(description = "排序字段，默认：id↑")
    private String orderCol;

}
