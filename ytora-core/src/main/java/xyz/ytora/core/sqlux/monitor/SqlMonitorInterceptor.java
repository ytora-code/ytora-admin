package xyz.ytora.core.sqlux.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.ytora.base.sqlux.SqluxProperty;
import xyz.ytora.sqlux.interceptor.Interceptor;
import xyz.ytora.sqlux.interceptor.SqlExecutionContext;

/**
 * SQL 监控拦截器。
 * <p>
 * 该拦截器挂载在 Sqlux 的执行链上，用于完成两件事：
 * <br/>
 * 1. 统计 SQL 执行总数、成功数、失败数、平均耗时、最大耗时；
 * <br/>
 * 2. 按配置阈值记录慢 SQL。
 * <p>
 * 展示层可以在后续数据库监控模块中读取 {@link SqlMetricsCollector} 的快照。
 *
 * <p>该拦截器会优先被执行，以免被后续拦截器报错影响</p>
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
public class SqlMonitorInterceptor implements Interceptor {

    private final SqluxProperty sqluxProperty;
    private final SqlMetricsCollector sqlMetricsCollector;

    /**
     * SQL 成功执行后记录统计。
     */
    @Override
    public void afterSuccess(SqlExecutionContext context) {
        sqlMetricsCollector.record(context, slowSqlThreshold(), true);
    }

    /**
     * SQL 失败执行后记录统计。
     */
    @Override
    public void afterFailure(SqlExecutionContext context) {
        sqlMetricsCollector.record(context, slowSqlThreshold(), false);
    }

    /**
     * 获取慢 SQL 阈值。
     * <p>
     * 未配置时使用 3000ms 作为默认值，避免监控组件因配置缺失失效。
     */
    private long slowSqlThreshold() {
        Long threshold = sqluxProperty.getSlowSqlThreshold();
        return threshold == null || threshold <= 0L ? 3000L : threshold;
    }
}
