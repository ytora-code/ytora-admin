package xyz.ytora.core.monitor.db.logic;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.sqlux.CustomerDataSourceProperties;
import xyz.ytora.base.sqlux.DynamicDataSource;
import xyz.ytora.base.sqlux.SqluxProperty;
import xyz.ytora.core.sqlux.monitor.HotSlowSqlRecord;
import xyz.ytora.core.sqlux.monitor.SlowSqlRecord;
import xyz.ytora.core.sqlux.monitor.SqlMetricsCollector;
import xyz.ytora.core.sqlux.monitor.SqlMetricsSnapshot;
import xyz.ytora.core.monitor.db.model.data.DbDataSourceItem;
import xyz.ytora.core.monitor.db.model.data.DbDataSourceRuntimeItem;
import xyz.ytora.core.monitor.db.model.data.DbDynamicData;
import xyz.ytora.core.monitor.db.model.data.DbHotSlowSqlItem;
import xyz.ytora.core.monitor.db.model.data.DbSlowSqlItem;
import xyz.ytora.core.monitor.db.model.data.DbStaticData;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by YT on 2026/4/24
 * <br/>
 * 数据库监控逻辑。
 * <p>
 * 数据库监控分为两层：
 * <br/>
 * 1. 数据源与连接池状态；
 * <br/>
 * 2. SQL 与慢 SQL 执行统计。
 */
@Service
@RequiredArgsConstructor
public class DbLogic {

    private final DynamicDataSource dynamicDataSource;
    private final SqluxProperty sqluxProperty;
    private final SqlMetricsCollector sqlMetricsCollector;

    /**
     * 获取数据源信息。
     */
    public DbStaticData getDataSources() {
        List<DbDataSourceItem> dataSources = new ArrayList<>();
        Map<String, CustomerDataSourceProperties> configuredDataSources = sqluxProperty.getDynamicDs();

        if (configuredDataSources != null) {
            configuredDataSources.forEach((name, properties) -> dataSources.add(buildStaticDataSourceItem(name, properties)));
        }

        return DbStaticData.builder()
                .primaryKey(sqluxProperty.getPrimaryKey())
                .dataSources(dataSources)
                .build();
    }

    /**
     * 获取数据库动态监控信息。
     */
    public DbDynamicData getDynamicData() {
        List<DbDataSourceRuntimeItem> dataSources = new ArrayList<>();

        dynamicDataSource.getResolvedDataSources().forEach((name, dataSource) ->
                dataSources.add(buildRuntimeDataSourceItem(String.valueOf(name), dataSource)));

        SqlMetricsSnapshot sqlMetrics = sqlMetricsCollector.snapshot(resolveSlowSqlThreshold());

        List<DbSlowSqlItem> slowSqlItems = sqlMetrics.recentSlowSqls().stream()
                .map(this::buildSlowSqlItem)
                .toList();
        List<DbHotSlowSqlItem> hotSlowSqlItems = sqlMetrics.hotSlowSqls().stream()
                .map(this::buildHotSlowSqlItem)
                .toList();

        return DbDynamicData.builder()
                .totalSqlCount(sqlMetrics.totalSqlCount())
                .successSqlCount(sqlMetrics.successSqlCount())
                .failureSqlCount(sqlMetrics.failureSqlCount())
                .averageDurationMs(sqlMetrics.averageDurationMs())
                .maxDurationMs(sqlMetrics.maxDurationMs())
                .slowSqlCount(sqlMetrics.slowSqlCount())
                .slowSqlThreshold(sqlMetrics.slowSqlThreshold())
                .recentSlowSqls(slowSqlItems)
                .hotSlowSqls(hotSlowSqlItems)
                .dataSources(dataSources)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 构建静态数据源信息。
     */
    private DbDataSourceItem buildStaticDataSourceItem(String name, CustomerDataSourceProperties properties) {
        return DbDataSourceItem.builder()
                .name(name)
                .description(properties.getDesc())
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .type(properties.getType() == null ? null : properties.getType().getName())
                .build();
    }

    /**
     * 构建动态数据源状态。
     * <p>
     * 当前优先适配 HikariCP。
     * 若未来接入其他连接池，也可以在这里继续扩展适配逻辑。
     */
    private DbDataSourceRuntimeItem buildRuntimeDataSourceItem(String name, DataSource dataSource) {
        if (dataSource instanceof HikariDataSource hikariDataSource) {
            HikariPoolMXBean poolBean = hikariDataSource.getHikariPoolMXBean();
            return DbDataSourceRuntimeItem.builder()
                    .name(name)
                    .poolType(HikariDataSource.class.getSimpleName())
                    .activeConnections(poolBean == null ? 0 : poolBean.getActiveConnections())
                    .idleConnections(poolBean == null ? 0 : poolBean.getIdleConnections())
                    .totalConnections(poolBean == null ? 0 : poolBean.getTotalConnections())
                    .threadsAwaitingConnection(poolBean == null ? 0 : poolBean.getThreadsAwaitingConnection())
                    .jdbcUrl(hikariDataSource.getJdbcUrl())
                    .databaseProductName(resolveDatabaseProductName(hikariDataSource))
                    .build();
        }

        return DbDataSourceRuntimeItem.builder()
                .name(name)
                .poolType(dataSource.getClass().getSimpleName())
                .activeConnections(null)
                .idleConnections(null)
                .totalConnections(null)
                .threadsAwaitingConnection(null)
                .jdbcUrl(null)
                .databaseProductName(resolveDatabaseProductName(dataSource))
                .build();
    }

    /**
     * 构建慢 SQL DTO。
     */
    private DbSlowSqlItem buildSlowSqlItem(SlowSqlRecord record) {
        return DbSlowSqlItem.builder()
                .timestamp(record.timestamp())
                .sqlType(record.sqlType())
                .elapsedMillis(record.elapsedMillis())
                .sql(record.sql())
                .paramsText(record.paramsText())
                .exceptionClass(record.exceptionClass())
                .exceptionMsg(record.exceptionMsg())
                .success(record.success())
                .build();
    }

    /**
     * 构建热点慢 SQL DTO。
     */
    private DbHotSlowSqlItem buildHotSlowSqlItem(HotSlowSqlRecord record) {
        return DbHotSlowSqlItem.builder()
                .fingerprint(record.fingerprint())
                .sqlType(record.sqlType())
                .sampleSql(record.sampleSql())
                .sampleParamsText(record.sampleParamsText())
                .count(record.count())
                .averageDurationMs(record.averageDurationMs())
                .maxDurationMs(record.maxDurationMs())
                .firstSeenTime(record.firstSeenTime())
                .lastSeenTime(record.lastSeenTime())
                .score(record.score())
                .build();
    }

    /**
     * 获取慢 SQL 阈值。
     */
    private long resolveSlowSqlThreshold() {
        Long threshold = sqluxProperty.getSlowSqlThreshold();
        return threshold == null || threshold <= 0L ? 3000L : threshold;
    }

    /**
     * 尝试读取数据库产品名称。
     */
    private String resolveDatabaseProductName(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            return metaData.getDatabaseProductName();
        } catch (SQLException e) {
            return null;
        }
    }
}
