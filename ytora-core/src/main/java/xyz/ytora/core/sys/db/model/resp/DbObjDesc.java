package xyz.ytora.core.sys.db.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sql4j.meta.model.*;

import java.util.List;

/**
 * created by YT on 2026/1/17 02:41:19
 * <br/>
 * 数据库对象
 */
@Data
@Schema(description = "数据源基础信息")
public class DbObjDesc {

    @Schema(description = "表")
    private List<TableMeta> tableMetas;

    @Schema(description = "视图")
    private List<ViewMeta> viewMetas;

    @Schema(description = "函数")
    private List<FunctionMeta> functionMetas;

    @Schema(description = "存储过程")
    private List<ProcedureMeta> procedureMetas;

    @Schema(description = "序列")
    private List<SequenceMeta> sequenceMetas;

}
