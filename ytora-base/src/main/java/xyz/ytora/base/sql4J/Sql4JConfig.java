package xyz.ytora.base.sql4J;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import xyz.ytora.sql4j.Sql4JException;
import xyz.ytora.sql4j.caster.Caster;
import xyz.ytora.sql4j.caster.ITypeCaster;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.DbType;
import xyz.ytora.sql4j.interceptor.SqlInterceptor;
import xyz.ytora.sql4j.log.ISqlLogger;
import xyz.ytora.sql4j.orm.TableCreatorManager;
import xyz.ytora.sql4j.orm.scanner.EntityScanner;
import xyz.ytora.sql4j.orm.scanner.RepoScanner;
import xyz.ytora.sql4j.translate.ITranslator;
import xyz.ytora.ytool.convert.TypePair;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Sql4J配置类
 */
@Slf4j
@Configuration
public class Sql4JConfig implements EnvironmentAware {
    private Environment environment;

    @Resource
    private Sql4JProperty sql4JProperty;

    /**
     * 记录下面每个数据源和数据库类型的映射
     */
    private final Map<DataSource, DbType> dataSourceTypeMapper = new HashMap<>();

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    /**
     * 多数据源核心配置
     * 该数据源将被优先加载，替换 Spring 默认的数据源对象
     * 当从 DynamicDataSource 类型的数据源获取连接时，内部将先调用 determineCurrentLookupKey 方法获取 key，再根据 key 从 targetDataSources 里面获取数据源对象
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDs = new DynamicDataSource();
        // 数据源路由
        Map<Object, Object> targetDataSources = new HashMap<>();

        Binder binder = Binder.get(environment);
        // 遍历所有数据源
        for (String dsName : sql4JProperty.listDsNames()) {
            DataSourceProperties properties = sql4JProperty.getDsByName(dsName);
            DataSource ds = properties.initializeDataSourceBuilder()
                    .type(determineDataSourceType(properties))
                    .build();
            // 绑定参数
            if (sql4JProperty.getParam() != null) {
                ds = binder.bind("sql4j.param", Bindable.ofInstance(ds)).get();
            }
            targetDataSources.put(dsName, ds);

            // 根据数据库驱动判断每个数据源的数据库类型
            determineDatabaseType(ds, properties);
        }

        // 给动态数据源设置数据源路由
        dynamicDs.setTargetDataSources(targetDataSources);
        // 给动态数据源设置主数据源
        dynamicDs.setDefaultTargetDataSource(targetDataSources.get(Sql4JProperty.PRIMARY_KEY));
        return dynamicDs;
    }

    @Bean
    @Transactional(rollbackFor = Exception.class)
    public SQLHelper sqlHelper(DataSource ds, List<SqlInterceptor> interceptors, List<Caster<?, ?>> casters) {
        SQLHelper sqlHelper = new SQLHelper();
        // 注册数据库连接提供器
        sqlHelper.registerConnectionProvider(new SpringConnectionProvider(ds));
        // 注册读取时的类型转换器
        registerCaster(sqlHelper);
        // 注册拦截器
        for (SqlInterceptor interceptor : interceptors) {
            sqlHelper.addSqlInterceptor(interceptor);
        }
        // 注册日志记录器
        Class<? extends ISqlLogger> sqlLogClass = sql4JProperty.getSqlLogImpl();
        if (sqlLogClass != null) {
            try {
                ISqlLogger sqlLogger = sqlLogClass.getConstructor().newInstance();
                sqlHelper.registerLogger(sqlLogger);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                log.error("注册 Sql4J 日志记录器时出错：" + e.getMessage(), e);
            }
        }

        // 注册 SQL 翻译器
        Class<? extends ITranslator> sqlTranslatorClass = sql4JProperty.getSqlTranslatorImp();
        if (sqlTranslatorClass != null) {
            try {
                ITranslator translator = sqlTranslatorClass.getConstructor().newInstance();
                sqlHelper.registerTranslator(translator);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                log.error("注册 SQL 翻译器时出错：" + e.getMessage(), e);
            }
        }

        // 注册 TypeCaster：读入数据时的类型转换
        Class<? extends ITypeCaster> typeCasterClass = sql4JProperty.getTypeCasterImpl();
        if (typeCasterClass != null) {
            try {
                ITypeCaster typeCaster = typeCasterClass.getConstructor().newInstance();
                sqlHelper.registerTypeCaster(typeCaster);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                log.error("注册 TypeCaster 出错：" + e.getMessage(), e);
            }
        }

        // 注册 caster：负责不同类型之间具体的转换逻辑
        for (Caster<?, ?> caster : casters) {
            sqlHelper.addCaster(new TypePair(caster.getSourceType(), caster.getTargetType()), caster);
        }

        // 注册 TableCreatorManager
        Class<? extends TableCreatorManager> tableCreatorManagerImpl = sql4JProperty.getTableCreatorManagerImpl();
        if (tableCreatorManagerImpl != null) {
            try {
                TableCreatorManager tableCreatorManager = tableCreatorManagerImpl.getConstructor().newInstance();
                sqlHelper.registerTableCreatorManager(tableCreatorManager);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                log.error("注册 TableCreatorManager 时出错：" + e.getMessage(), e);
            }
        }

        // 扫描实体类，并决定是否创建表
        if (sql4JProperty.getCreateTableIfNotExist()) {
            EntityScanner scanner = new EntityScanner(sqlHelper, sql4JProperty.getEntityPath());
            scanner.createTableIfNotExist();
        }
        // 扫描Repo接口，创建代理类
        String repoPath = sql4JProperty.getRepoPath();
        if (repoPath != null) {
            RepoScanner scanner = new RepoScanner(sqlHelper, repoPath);
            scanner.createProxyRepo();
        }
        return sqlHelper;
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

    private void registerCaster(SQLHelper sqlHelper) {

    }


    private void determineDatabaseType(DataSource ds, DataSourceProperties dsp) {
        String driverClassName = dsp.getDriverClassName();
        if (driverClassName == null || driverClassName.isEmpty()) {
            throw new Sql4JException("driverClassName 不能为空!");
        }

        String lower = driverClassName.toLowerCase(Locale.ROOT);
        DbType dbType;

        if (lower.contains("postgresql")) {
            // org.postgresql.Driver
            dbType = DbType.POSTGRESQL;
        } else if (lower.contains("mysql")) {
            dbType = DbType.MYSQL;
        } else if (lower.contains("mariadb")) {
            dbType = DbType.MARIADB;
        } else if (lower.contains("oracle")) {
            dbType = DbType.ORACLE;
        } else if (lower.contains("sqlserver") || lower.contains("microsoft.sqlserver") || lower.contains("jtds")) {
            dbType = DbType.SQLSERVER;
        }  else if (lower.contains("db2")) {
            dbType = DbType.DB2;
        } else if (lower.contains("h2")) {
            dbType = DbType.H2;
        } else if (lower.contains("sqlite")) {
            dbType = DbType.SQLite;
        } else {
            throw new Sql4JException("未知的数据库驱动：" + driverClassName);
        }

        dataSourceTypeMapper.put(ds, dbType);
    }
}
