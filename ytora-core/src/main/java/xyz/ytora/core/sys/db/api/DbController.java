package xyz.ytora.core.sys.db.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.base.sql4J.Sql4JProperty;
import xyz.ytora.core.sys.db.logic.DbLogic;
import xyz.ytora.core.sys.db.model.req.FetchDataReq;
import xyz.ytora.core.sys.db.model.resp.DataSourceDesc;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.meta.IMetaService;
import xyz.ytora.sql4j.meta.model.*;
import xyz.ytora.ytool.coll.Colls;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * DB 接口
 */
@Slf4j
@RestController
@RequestMapping("/sys/db")
@Tag(name = "数据库")
public class DbController {

    @Resource
    private DbLogic logic;
    @Resource
    private Sql4JProperty sql4JProperty;
    @Resource
    private IMetaService metaService;
    @Resource
    private DataSource dataSource;
    @Resource
    private SQLHelper sqlHelper;

    @GetMapping(value = "/dataSources")
    @Operation(summary = "获取当前系统所有数据源", description = "获取当前系统所有数据源")
    public Collection<DataSourceDesc> dataSources() {
        return logic.dsMap.values();
    }

    @GetMapping(value = "/schemas")
    @Operation(summary = "获取指定数据源的所有模式", description = "数据源都有指定的catalog，所以这里直接获取数据源所属catalog的所有模式，有些数据库比如MySQL的模式为空")
    public List<String> catalogs(String ds) {
        DataSourceDesc dataSourceDesc = logic.check(ds);

        List<String> schemas = Scopes.run(ScopedValueItem.DS_CONTEXT, ds, () -> metaService.listSchemas(dataSourceDesc.getCatalog()));

        // 有的数据库，比如MySQL、MariaDB没有schema概率，其catalog就是schema，所以这种情况使用catalog作为schema
        if (Colls.isEmpty(schemas)) {
            schemas = Collections.singletonList(dataSourceDesc.getCatalog());
        }
        return schemas;
    }

    // ========================================= SCHEMA下面的对象 =========================================>

    @GetMapping(value = "/tables")
    @Operation(summary = "获取指定数据源指定schema的下面的table", description = "获取指定数据源指定schema的下面的table")
    public List<TableMeta> tables(String ds, String schema) {
        DataSourceDesc dataSourceDesc = logic.check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.run(ScopedValueItem.DS_CONTEXT, ds, () -> {
            // 获取表
            return metaService.listTables(catalog, schema, null);
        });
    }

    @GetMapping(value = "/views")
    @Operation(summary = "获取指定数据源指定schema的下面的view", description = "获取指定数据源指定schema的下面的view")
    public List<ViewMeta> views(String ds, String schema) {
        DataSourceDesc dataSourceDesc = logic.check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.run(ScopedValueItem.DS_CONTEXT, ds, () -> {
            // 获取表
            return metaService.listViews(catalog, schema, null);
        });
    }

    @GetMapping(value = "/functions")
    @Operation(summary = "获取指定数据源指定schema的下面的function", description = "获取指定数据源指定schema的下面的function")
    public List<FunctionMeta> functions(String ds, String schema) {
        DataSourceDesc dataSourceDesc = logic.check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.run(ScopedValueItem.DS_CONTEXT, ds, () -> {
            // 获取表
            return metaService.listFunctions(catalog, schema, null);
        });
    }

    @GetMapping(value = "/procedures")
    @Operation(summary = "获取指定数据源指定schema的下面的procedure", description = "获取指定数据源指定schema的下面的procedure")
    public List<ProcedureMeta> procedures(String ds, String schema) {
        DataSourceDesc dataSourceDesc = logic.check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.run(ScopedValueItem.DS_CONTEXT, ds, () -> {
            // 获取表
            return metaService.listProcedures(catalog, schema, null);
        });
    }

    @GetMapping(value = "/sequences")
    @Operation(summary = "获取指定数据源指定schema的下面的sequences", description = "获取指定数据源指定schema的下面的sequences")
    public List<SequenceMeta> sequences(String ds, String schema) {
        DataSourceDesc dataSourceDesc = logic.check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.run(ScopedValueItem.DS_CONTEXT, ds, () -> {
            // 获取表
            return metaService.listSequences(catalog, schema, null);
        });
    }

    // ========================================= CRUD =========================================>

    @PostMapping(value = "/fetchData")
    @Operation(summary = "表和视图的真实数据内容", description = "表和视图的真实数据内容")
    public List<Map<String, Object>> fetchData(@RequestBody FetchDataReq data,
                                               @RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "100") Integer pageSize) {
        return logic.fetchData(data, pageNo, pageSize);
    }

    @PostMapping(value = "/fetchCount")
    @Operation(summary = "表和视图的真实数据总量", description = "表和视图的真实数据总量")
    public Long fetchCount(@RequestBody FetchDataReq data) {
        return logic.fetchCount(data);
    }

}
