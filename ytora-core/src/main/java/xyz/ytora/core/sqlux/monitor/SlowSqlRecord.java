package xyz.ytora.core.sqlux.monitor;

/**
 * 慢 SQL 记录。
 *
 * @param timestamp      记录时间戳
 * @param sqlType        SQL类型
 * @param elapsedMillis  执行耗时
 * @param sql            SQL文本
 * @param paramsText     参数文本
 * @param exceptionClass 异常类型
 * @param exceptionMsg   异常消息
 * @param success        是否执行成功
 */
public record SlowSqlRecord(
        long timestamp,
        String sqlType,
        long elapsedMillis,
        String sql,
        String paramsText,
        String exceptionClass,
        String exceptionMsg,
        boolean success
) {
}
