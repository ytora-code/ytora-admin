package xyz.ytora.core.sys.db.logic;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.base.sql4J.CustomerDataSourceProperties;
import xyz.ytora.base.sql4J.Sql4JProperty;
import xyz.ytora.core.sys.db.model.req.FetchDataReq;
import xyz.ytora.core.sys.db.model.resp.DataSourceDesc;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.DbType;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.creator.TableCreatorManager;
import xyz.ytora.sql4j.sql.select.FromStage;
import xyz.ytora.sql4j.sql.select.OffsetStage;
import xyz.ytora.sql4j.sql.select.OrderByStage;
import xyz.ytora.sql4j.sql.select.SelectWhereStage;
import xyz.ytora.ytool.str.Strs;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * created by YT on 2026/1/18 00:56:30
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class DbLogic {
    private final SQLHelper sqlHelper;
    private final DataSource dataSource;

    public Map<String, DataSourceDesc> dsMap = new LinkedHashMap<>();

    @Resource
    public void autowired(Sql4JProperty sql4JProperty) {
        Map<String, CustomerDataSourceProperties> dynamicDs = sql4JProperty.getDynamicDs();
        for (String ds : dynamicDs.keySet()) {
            CustomerDataSourceProperties customerDataSourceProperties = dynamicDs.get(ds);
            DataSourceDesc dataSourceDesc = new DataSourceDesc();
            dataSourceDesc.setName(ds);
            dataSourceDesc.setDesc(customerDataSourceProperties.getDesc());
            dataSourceDesc.setDsType(customerDataSourceProperties.getType().getName());

            ScopedValue.where(ScopedValueItem.DS_CONTEXT, ds).run(() -> {
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
     * 表和视图的真实数据内容
     */
    public List<Map<String, Object>> fetchData(FetchDataReq data, Integer pageNo, Integer pageSize) {
        check(data.getDs());

        TableCreatorManager tableCreatorManager = sqlHelper.getTableCreatorManager();
        Map<String, Class<?>> tableClassMapper = tableCreatorManager.getTableClassMapper();
        Class<?> tableClazz = tableClassMapper.get(data.getName());

        return Scopes.run(ScopedValueItem.DS_CONTEXT, data.getDs(), () -> {
            // 查询数据
            FromStage from;
            // 要查询的表/视图名称
            StringBuilder tableName = new StringBuilder();
            if (Strs.isNotEmpty(data.getSchema())) {
                tableName.append(data.getSchema()).append(".");
            }
            tableName.append(data.getName());

            if (tableClazz == null) {
                // 可能是视图
                from = sqlHelper.select().from(tableName.toString());
            } else {
                // 物理表
                from = sqlHelper.select(tableClazz).from(tableName.toString());
            }

            // 查询条件
            String where = data.getWhere();
            SelectWhereStage whereStage = from.where(w -> w.apply(Strs.isNotEmpty(where), where));

            // 排序规则
            OrderByStage orderByStage = null;
            if (Strs.isNotEmpty(data.getOrderCol())) {
                String[] orderCols = data.getOrderCol().split(",");
                for (String orderCol : orderCols) {
                    String colName = orderCol.substring(0, orderCol.length() - 1);
                    OrderType orderType;
                    if (orderCol.endsWith("↑")) {
                        orderType = OrderType.ASC;
                    } else if (orderCol.endsWith("↓")) {
                        orderType = OrderType.DESC;
                    }
                    // 默认升序
                    else {
                        colName = orderCol;
                        orderType = OrderType.ASC;
                    }
                    if (orderByStage == null) {
                        orderByStage = whereStage.orderBy(Raw.of(colName), orderType);
                    } else {
                        orderByStage = orderByStage.orderBy(Raw.of(colName), orderType);
                    }
                }
            }

            // 偏移
            int limit = pageSize;
            int offset = (pageNo - 1) * pageSize;

            OffsetStage offsetStage = null;
            if (orderByStage == null) {
                offsetStage = whereStage.limit(limit).offset(offset);
            } else {
                offsetStage = orderByStage.limit(limit).offset(offset);
            }
            return offsetStage.submit();
        });
    }

    public DataSourceDesc check(String ds) {
        DataSourceDesc dataSourceDesc = dsMap.get(ds);
        if (dataSourceDesc == null) {
            throw new BaseException("数据源[" + ds + "]不存在");
        }
        return dataSourceDesc;
    }

    /**
     * 表和视图的真实数据总量
     */
    public Long fetchCount(FetchDataReq data) {
        check(data.getDs());

        // 要查询的表/视图名称
        StringBuilder tableName = new StringBuilder();
        if (Strs.isNotEmpty(data.getSchema())) {
            tableName.append(data.getSchema()).append(".");
        }
        tableName.append(data.getName());

        return Scopes.run(ScopedValueItem.DS_CONTEXT, data.getDs(), () -> {
            // 查询数据
            FromStage from = sqlHelper.select(Raw.of("count(1)").as("count")).from(tableName.toString());

            // 查询条件
            String where = data.getWhere();
            List<Map<String, Object>> countResult = from.where(w -> w.apply(Strs.isNotEmpty(where), where)).submit();
            Map<String, Object> first = countResult.getFirst();
            return (long) first.getOrDefault("count", 0L);
        });
    }
}
