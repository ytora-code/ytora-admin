package xyz.ytora.core.online.db.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.enums.Mimes;
import xyz.ytora.base.mvc.result.anno.DownloadMapper;
import xyz.ytora.core.online.db.logic.CodeGenLogic;
import xyz.ytora.core.online.db.logic.DatabaseLogic;
import xyz.ytora.core.online.db.model.data.DataSourceDesc;
import xyz.ytora.core.online.db.model.param.FetchDataParam;
import xyz.ytora.sqlux.meta.model.*;
import xyz.ytora.sqlux.orm.Page;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 数据库相关API接口
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "数据库")
@RestController
@RequestMapping("/online/database")
@RequiredArgsConstructor
public class DatabaseApi {

    private final CodeGenLogic codeGenService;
    private final DatabaseLogic databaseLogic;

    @Operation(summary = "根据表名称生成代码", description = "根据表名称生成代码")
    @DownloadMapper(value = "genByTable", mime = Mimes.APPLICATION_ZIP, filename = "#tableName + '.zip'")
    public byte[] genByTable(@RequestParam String path,
                             @RequestParam String catalog,
                             @RequestParam String schema,
                             @RequestParam String tableName) {
        return codeGenService.gen(path, catalog, schema, tableName);
    }

    @GetMapping(value = "/dataSources")
    @Operation(summary = "获取当前系统所有数据源", description = "获取当前系统所有数据源")
    public Collection<DataSourceDesc> dataSources() {
        return databaseLogic.dsMap.values();
    }

    @GetMapping(value = "/schemas")
    @Operation(summary = "获取指定数据源下面所有schemas", description = "获取指定数据源下面所有schemas")
    public List<String> schemas(@RequestParam String ds) throws Throwable {
        return databaseLogic.schemas(ds);
    }

    @GetMapping(value = "/tables")
    @Operation(summary = "获取数据源指定schema的下面的table", description = "获取数据源指定schema的下面的table")
    public List<TableMeta> tables(@RequestParam String ds, @RequestParam String schema) throws Throwable {
        return databaseLogic.tables(ds, schema);
    }

    @GetMapping(value = "/views")
    @Operation(summary = "获取数据源指定schema的下面的view", description = "获取数据源指定schema的下面的view")
    public List<ViewMeta> views(@RequestParam String ds, @RequestParam String schema) throws Throwable {
        return databaseLogic.views(ds, schema);
    }

    @GetMapping(value = "/functions")
    @Operation(summary = "获取指定数据源指定schema的下面的function", description = "获取指定数据源指定schema的下面的function")
    public List<FunctionMeta> functions(@RequestParam String ds, @RequestParam String schema) throws Throwable {
        return databaseLogic.functions(ds, schema);
    }

    @GetMapping(value = "/procedures")
    @Operation(summary = "获取指定数据源指定schema的下面的procedure", description = "获取指定数据源指定schema的下面的procedure")
    public List<ProcedureMeta> procedures(@RequestParam String ds, @RequestParam String schema) throws Throwable {
        return databaseLogic.procedures(ds, schema);
    }

    @GetMapping(value = "/sequences")
    @Operation(summary = "获取指定数据源指定schema的下面的sequences", description = "获取指定数据源指定schema的下面的sequences")
    public List<SequenceMeta> sequences(@RequestParam String ds, @RequestParam String schema) throws Throwable {
        return databaseLogic.sequences(ds, schema);
    }

    @PostMapping(value = "/fetchData")
    @Operation(summary = "表和视图的真实数据内容", description = "表和视图的真实数据内容")
    public Page<Map<String, Object>> fetchData(@RequestBody FetchDataParam data,
                                               @RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize) throws Throwable {
        return databaseLogic.fetchData(data, pageNo, pageSize);
    }

}
