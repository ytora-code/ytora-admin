package xyz.ytora.base.sqlux;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import xyz.ytora.sqlux.core.IConnectionProvider;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.sqlux.core.SqluxContext;
import xyz.ytora.sqlux.core.SqluxGlobal;
import xyz.ytora.sqlux.core.enums.DbType;
import xyz.ytora.sqlux.core.json.SqluxJson;
import xyz.ytora.sqlux.interceptor.Interceptor;
import xyz.ytora.sqlux.interceptor.log.SqlLogger;
import xyz.ytora.sqlux.meta.DefaultMetaService;
import xyz.ytora.sqlux.meta.IMetaService;
import xyz.ytora.sqlux.orm.creator.TableCreators;
import xyz.ytora.toolkit.text.Strs;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Sqlux配置类
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SqluxProperty.class)
public class SqluxConfig {

    private final static String SQLUX_GLOBAL = "sqluxGlobal";

    /**
     * 记录下面每个数据源和数据库类型的映射
     */
    private final Map<DataSource, DbType> dataSourceTypeMapper = new HashMap<>();

    @Bean
    @Primary
    @ConditionalOnMissingBean(DataSource.class)
    public DynamicDataSource dynamicDataSource(SqluxProperty sqluxProperty, Environment environment) {
        // 数据源路由
        DynamicDataSource dynamicDs = new DynamicDataSource(sqluxProperty);
        Map<Object, Object> targetDataSources = new HashMap<>();
        Binder binder = Binder.get(environment);

        // 遍历所有数据源
        for (String dsName : sqluxProperty.getDynamicDs().keySet()) {
            DataSourceProperties properties = sqluxProperty.getDynamicDs().get(dsName);
            DataSource ds = properties.initializeDataSourceBuilder()
                    .type(determineDataSourceType(properties))
                    .build();
            // 绑定数据库连接池对象的参数
            if (sqluxProperty.getParam() != null) {
                ds = binder.bind("ytora.sqlux.param", Bindable.ofInstance(ds)).get();
            }

            targetDataSources.put(dsName, ds);
            // 根据数据库驱动判断每个数据源的数据库类型
            determineDatabaseType(ds, properties);

            log.info("数据源 {} 加载完毕.", dsName);
        }

        // 给动态数据源设置数据源路由
        dynamicDs.setTargetDataSources(targetDataSources);
        // 给动态数据源设置主数据源
        dynamicDs.setDefaultTargetDataSource(targetDataSources.get(sqluxProperty.primaryKey));
        return dynamicDs;
    }

    @Bean(SQLUX_GLOBAL)
    public static SqluxGlobal sqluxGlobal(SqluxProperty sqluxProperty,
                                        DynamicDataSource dynamicDs,
                                        List<Interceptor> interceptors,
                                        SpringConnectionProvider connectionProvider,
                                        SqluxJson sqluxJson) {
        SqluxGlobal sqluxGlobal = new SqluxGlobal();
        // 设置全局的数据库连接提供器
        sqluxGlobal.registerConnectionProvider(connectionProvider);
        // 设置全局的拦截器
        for (Interceptor interceptor : interceptors) {
            sqluxGlobal.registerInterceptor(interceptor);
        }
        // 设置日志记录器
        Class<? extends SqlLogger> sqlLoggerImpl = sqluxProperty.getSqlLoggerImpl();
        if (sqlLoggerImpl != null) {
            try {
                sqluxGlobal.registerSqlLogger(sqlLoggerImpl.getConstructor().newInstance());
            } catch (Throwable e) {
                log.error("构造日志记录器[" + sqlLoggerImpl.getName() + "]失败", e);
            }
        }

        // 设置json处理器
        sqluxGlobal.registerSqluxJson(sqluxJson);

        Map<Object, DataSource> dataSourceMap = dynamicDs.getResolvedDataSources();
        for (DataSource ds : dataSourceMap.values()) {
            try (Connection connection = ds.getConnection()) {
                // 自动建表
                if (sqluxProperty.getAutoCreateTable() && Strs.isNotEmpty(sqluxProperty.getEntityPath())) {
                    // 数据库产品名称
                    DatabaseMetaData metaData = connection.getMetaData();
                    String productName = metaData.getDatabaseProductName();
                    SqluxContext.setDbType(DbType.fromString(productName));
                    sqluxGlobal.setEntityPath(sqluxProperty.getEntityPath());
                    TableCreators.createMissingTables(connection);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                SqluxContext.clear();
            }
        }

        // 给SQL注册全局的sqluxGlobal对象
        SQL.registerSqluxGlobal(sqluxGlobal);

        return sqluxGlobal;
    }

    @Bean
    public IMetaService metaService(IConnectionProvider connectionProvider) {
        return new DefaultMetaService(connectionProvider);
    }

    /**
     * 根据DataSourceProperties判断应该创建什么类型的数据库连接池
     */
    private Class<? extends DataSource> determineDataSourceType(DataSourceProperties dataSourceProperties) {
        // 如果某个数据源中配置了连接池类型
        if (dataSourceProperties.getType() != null) {
            return dataSourceProperties.getType();
        }
        // 没有配置，则使用默认
        return HikariDataSource.class;
    }

    private void determineDatabaseType(DataSource ds, DataSourceProperties dsp) {
        String driverClassName = dsp.getDriverClassName();
        if (driverClassName == null || driverClassName.isEmpty()) {
            throw new IllegalArgumentException("driverClassName 不能为空!");
        }

        String lower = driverClassName.toLowerCase(Locale.ROOT);
        DbType dbType;

        if (lower.contains("postgresql")) {
            dbType = DbType.POSTGRESQL;
        } else if (lower.contains("mysql")) {
            dbType = DbType.MYSQL;
        } else if (lower.contains("mariadb")) {
            dbType = DbType.MARIADB;
        } else if (lower.contains("oracle")) {
            dbType = DbType.ORACLE;
        } else if (lower.contains("sqlserver") || lower.contains("microsoft.sqlserver") || lower.contains("jtds")) {
            dbType = DbType.SQLSERVER;
        } else if (lower.contains("db2")) {
            dbType = DbType.DB2;
        } else if (lower.contains("h2")) {
            dbType = DbType.H2;
        } else if (lower.contains("sqlite")) {
            dbType = DbType.SQLite;
        } else {
            throw new IllegalArgumentException("未知的数据库驱动：" + driverClassName);
        }

        dataSourceTypeMapper.put(ds, dbType);
    }

}
