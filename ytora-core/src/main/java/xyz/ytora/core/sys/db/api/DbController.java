package xyz.ytora.core.sys.db.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.base.sql4J.CustomerDataSourceProperties;
import xyz.ytora.base.sql4J.Sql4JProperty;
import xyz.ytora.core.sys.db.model.resp.DataSourceDesc;
import xyz.ytora.sql4j.enums.DbType;
import xyz.ytora.sql4j.meta.IMetaService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * DB 接口
 */
@Slf4j
@RestController
@RequestMapping("/sys/db")
@Tag(name = "数据库")
public class DbController {

    @Resource
    private Sql4JProperty sql4JProperty;
    @Resource
    private IMetaService metaService;
    @Resource
    private DataSource dataSource;

    private Map<String, DataSourceDesc> dsMap;

    @Resource
    public void autowired(Sql4JProperty sql4JProperty) {
        Map<String, CustomerDataSourceProperties> dynamicDs = sql4JProperty.getDynamicDs();
        dsMap = dynamicDs.keySet().stream().map(ds -> {
            CustomerDataSourceProperties customerDataSourceProperties = dynamicDs.get(ds);
            DataSourceDesc dataSourceDesc = new DataSourceDesc();
            dataSourceDesc.setName(ds);
            dataSourceDesc.setDesc(customerDataSourceProperties.getDesc());
            dataSourceDesc.setDsType(customerDataSourceProperties.getType().getName());

            ScopedValue.where(ScopedValueItem.DS_CONTEXT, ds).run(() -> {
                // 获取当前数据源的一个连接
                try(Connection connection = dataSource.getConnection()) {
                    DatabaseMetaData metaData = connection.getMetaData();
                    String productName = metaData.getDatabaseProductName();
                    DbType dbType = DbType.fromString(productName);
                    String catalog = connection.getCatalog();
                    String schema = connection.getSchema();

                    dataSourceDesc.setDbType(dbType.getProductName());
                    dataSourceDesc.setDatabase(catalog);
                    dataSourceDesc.setSchema(schema);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // 获取连接
            return dataSourceDesc;
        }).collect(Collectors.toMap(DataSourceDesc::getName, i -> i));
    }

    @GetMapping(value = "/dataSources")
    @Operation(summary = "获取当前系统所有数据源", description = "获取当前系统所有数据源")
    public Collection<DataSourceDesc> dataSources() {
        return dsMap.values();
    }

    @GetMapping(value = "/catalogs")
    @Operation(summary = "获取指定数据源所有数据库", description = "获取指定数据源所有数据库")
    public List<String> catalogs(String ds) throws Throwable {
        DataSourceDesc dataSourceDesc = dsMap.get(ds);
        if (dataSourceDesc == null) {
            throw new BaseException("数据源[" + ds + "]不存在");
        }

        AtomicReference<Throwable> exceptionRef = new AtomicReference<>();
        AtomicReference<List<String>> resultRef = new AtomicReference<>();
        ScopedValue.where(ScopedValueItem.DS_CONTEXT, ds).run(() -> {
            try {
                List<String> catalogs = metaService.listCatalogs();
                resultRef.set(catalogs);
            } catch (SQLException e) {
                exceptionRef.set(e);
            }
        });

        // 抛出异常
        if (exceptionRef.get() != null) {
            throw exceptionRef.get();
        }

        return resultRef.get();
    }

    @GetMapping(value = "/schemas")
    @Operation(summary = "获取指定数据库的所有模式", description = "获取指定数据库的所有模式")
    public List<String> catalogs(String ds, String catalog) throws Throwable {
        DataSourceDesc dataSourceDesc = dsMap.get(ds);
        if (dataSourceDesc == null) {
            throw new BaseException("数据源[" + ds + "]不存在");
        }

        AtomicReference<Throwable> exceptionRef = new AtomicReference<>();
        AtomicReference<List<String>> resultRef = new AtomicReference<>();
        ScopedValue.where(ScopedValueItem.DS_CONTEXT, ds).run(() -> {
            try {
                List<String> schemas = metaService.listSchemas(catalog);
                resultRef.set(schemas);
            } catch (SQLException e) {
                exceptionRef.set(e);
            }
        });

        // 抛出异常
        if (exceptionRef.get() != null) {
            throw exceptionRef.get();
        }

        return resultRef.get();
    }

}
