package xyz.ytora.core.online.db.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.base.sqlux.CustomerDataSourceProperties;
import xyz.ytora.base.sqlux.SqluxProperty;
import xyz.ytora.core.online.db.model.data.DataSourceDesc;
import xyz.ytora.core.online.db.model.param.FetchDataParam;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.sqlux.core.enums.DbType;
import xyz.ytora.sqlux.meta.IMetaService;
import xyz.ytora.sqlux.meta.model.*;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.sqlux.sql.model.ColumnRef;
import xyz.ytora.sqlux.sql.stage.select.AbsSelect;
import xyz.ytora.sqlux.sql.stage.select.OrderByStage;
import xyz.ytora.sqlux.sql.stage.select.SelectWhereStage;
import xyz.ytora.sqlux.util.NamedUtil;
import xyz.ytora.toolkit.collection.Colls;
import xyz.ytora.toolkit.text.Strs;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static xyz.ytora.sqlux.core.SQL.select;
import static xyz.ytora.sqlux.sql.func.SqlFuncAggregation.count;

/**
 * 数据库相关LOGIC
 *
 * @author ytora 
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class DatabaseLogic {

    private final DataSource dataSource;
    private final IMetaService metaService;
    public final Map<String, DataSourceDesc> dsMap = new LinkedHashMap<>();

    @Autowired
    public void autowired(SqluxProperty sqluxProperty) {
        Map<String, CustomerDataSourceProperties> dynamicDs = sqluxProperty.getDynamicDs();
        for (String ds : dynamicDs.keySet()) {
            CustomerDataSourceProperties customerDataSourceProperties = dynamicDs.get(ds);
            DataSourceDesc dataSourceDesc = new DataSourceDesc();
            dataSourceDesc.setName(ds);
            dataSourceDesc.setDesc(customerDataSourceProperties.getDesc());
            dataSourceDesc.setDsType(customerDataSourceProperties.getType().getName());

            ScopedValue.where(ScopedValueContext.DS_CONTEXT, ds).run(() -> {
                // 获取当前数据源的一个连接
                try (Connection connection = dataSource.getConnection()) {
                    DatabaseMetaData metaData = connection.getMetaData();
                    String productName = metaData.getDatabaseProductName();
                    DbType dbType = DbType.fromString(productName);
                    String catalog = connection.getCatalog();
                    String schema = connection.getSchema();

                    dataSourceDesc.setDbType(dbType.getProductName());
                    dataSourceDesc.setCatalog(catalog);
                    dataSourceDesc.setSchema(schema);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            dsMap.put(ds, dataSourceDesc);
        }
    }

    /**
     * 获取指定数据源下面所有schemas
     *
     * @param ds 数据库名称
     * @return schemas名称列表
     */
    public List<String> schemas(String ds) throws Throwable {
        DataSourceDesc dataSourceDesc = check(ds);

        List<String> schemas = Scopes.start()
                .where(ScopedValueContext.DS_CONTEXT, ds)
                .run(() -> metaService.listSchemas(dataSourceDesc.getCatalog()));

        // 有的数据库，比如MySQL、MariaDB没有schema概率，其catalog就是schema，所以这种情况使用catalog作为schema
        if (Colls.isEmpty(schemas)) {
            schemas = Collections.singletonList(dataSourceDesc.getCatalog());
        }
        return schemas;
    }

    /**
     * 获取数据源指定schema的下面的table
     * @param ds 数据源
     * @param schema 模式名称
     * @return 表元数据
     */
    public List<TableMeta> tables(String ds, String schema) throws Throwable {
        DataSourceDesc dataSourceDesc = check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.start().where(ScopedValueContext.DS_CONTEXT, ds).run(() -> {
            // 获取所有表
            return metaService.listTables(catalog, schema, null);
        });
    }

    /**
     * 获取数据源指定schema的下面的view
     * @param ds 数据源
     * @param schema 模式名称
     * @return 视图元数据
     */
    public List<ViewMeta> views(String ds, String schema) throws Throwable {
        DataSourceDesc dataSourceDesc = check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.start().where(ScopedValueContext.DS_CONTEXT, ds).run(() -> {
            // 获取视图
            return metaService.listViews(catalog, schema, null);
        });
    }

    /**
     * 获取指定数据源指定schema的下面的function
     * @param ds 数据源
     * @param schema 模式名称
     * @return 函数元数据
     */
    public List<FunctionMeta> functions(String ds, String schema) throws Throwable {
        DataSourceDesc dataSourceDesc = check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.start().where(ScopedValueContext.DS_CONTEXT, ds).run(() -> {
            // 获取函数
            return metaService.listFunctions(catalog, schema, null);
        });
    }

    /**
     * 获取指定数据源指定schema的下面的procedure
     * @param ds 数据源
     * @param schema 模式名称
     * @return procedure元数据
     */
    public List<ProcedureMeta> procedures(String ds, String schema) throws Throwable {
        DataSourceDesc dataSourceDesc = check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.start().where(ScopedValueContext.DS_CONTEXT, ds).run(() -> {
            // 获取procedure
            return metaService.listProcedures(catalog, schema, null);
        });

    }

    /**
     * 获取指定数据源指定schema的下面的sequences
     * @param ds 数据源
     * @param schema 模式名称
     * @return procedure元数据
     */
    public List<SequenceMeta> sequences(String ds, String schema) throws Throwable {
        DataSourceDesc dataSourceDesc = check(ds);
        String catalog = dataSourceDesc.getCatalog();
        return Scopes.start().where(ScopedValueContext.DS_CONTEXT, ds).run(() -> {
            // 获取sequences
            return metaService.listSequences(catalog, schema, null);
        });
    }

    /**
     * 表和视图的真实数据内容
     */
    public Page<Map<String, Object>> fetchData(FetchDataParam data, Integer pageNo, Integer pageSize) throws Throwable {
        check(data.getDs());

        // 要查询的表/视图名称
        StringBuilder tableName = new StringBuilder();
        if (Strs.isNotEmpty(data.getSchema())) {
            tableName.append(data.getSchema()).append(".");
        }
        tableName.append(data.getName());

        return Scopes.start().where(ScopedValueContext.DS_CONTEXT, data.getDs()).run(() -> {
            SelectWhereStage selectSql = select()
                    .from(tableName.toString())
                    .where(w -> w.raw(Strs.isNotEmpty(data.getWhere()), data.getWhere()));

            AbsSelect sql = selectSql;

            // 排序
            String orderCol = data.getOrderCol();
            if (Strs.isNotEmpty(orderCol)) {
                String[] orderCols = orderCol.split(",");
                for (String col : orderCols) {
                    String colName = col.substring(0, orderCol.length() - 1);
                    if (orderCol.endsWith("↑")) {
                        sql = selectSql.orderByAsc(ColumnRef.raw(colName));
                    } else {
                        sql = selectSql.orderByDesc(ColumnRef.raw(colName));
                    }
                }
            }

            return sql.submit(Page.of(pageNo, pageSize));
        });
    }

    private DataSourceDesc check(String ds) {
        DataSourceDesc dataSourceDesc = dsMap.get(ds);
        if (dataSourceDesc == null) {
            throw new BaseException("数据源[" + ds + "]不存在");
        }
        return dataSourceDesc;
    }

}
