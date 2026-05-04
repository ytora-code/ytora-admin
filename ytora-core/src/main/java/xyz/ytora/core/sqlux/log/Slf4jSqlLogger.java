package xyz.ytora.core.sqlux.log;

import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import xyz.ytora.sqlux.core.enums.SqlType;
import xyz.ytora.sqlux.interceptor.log.SqlLogEvent;
import xyz.ytora.sqlux.interceptor.log.SqlLogger;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * 日期记录器
 *
 * @author ytora 
 * @since 1.0
 */
public class Slf4jSqlLogger implements SqlLogger {

    private static final String FQCN = Slf4jSqlLogger.class.getName();

    private static final LocationAwareLogger log =
            (LocationAwareLogger) LoggerFactory.getLogger("sql");

    @Override
    public void beforeExecute(SqlLogEvent event) {
        // 成功/失败时统一打印完整块，避免一条 SQL 被拆成多段日志影响阅读。
    }

    @Override
    public void afterSuccess(SqlLogEvent event) {
        info(buildSuccessMessage(event));
    }

    @Override
    public void afterFailure(SqlLogEvent event) {
        Throwable exception = event.getException();
        log.log(null, FQCN,
                LocationAwareLogger.ERROR_INT,
                buildFailureMessage(event),
                null,
                exception);
    }

    @Override
    public void log(Level level, String msg) {
        if (Level.INFO.equals(level)) {
            log.info(msg);
        } else if (Level.WARNING.equals(level)) {
            log.warn(msg);
        }else if (Level.SEVERE.equals(level)) {
            log.error(msg);
        }else if (Level.FINE.equals(level) || Level.FINER.equals(level)) {
            log.debug(msg);
        }else if (Level.FINEST.equals(level)) {
            log.trace(msg);
        }
    }

    private void info(String msg) {
        log.log(null, FQCN, LocationAwareLogger.INFO_INT, msg, null, null);
    }

    private String buildSuccessMessage(SqlLogEvent event) {
        StringBuilder sb = new StringBuilder(512);
        appendBanner(sb, "SUCCESS", event);
        appendLine(sb, "SQL", formatSql(event.getSql()));
        appendLine(sb, "PARAMS", formatParams(event.getParams()));
        appendLine(sb, "COST", event.getElapsedMillis() + " ms");
        appendLine(sb, "RESULT", summarizeResult(event.getResult()));
        appendOptionalLine(sb, "GEN_KEYS", formatGeneratedKeyTargets(event.getGeneratedKeyTargets()));
        appendOptionalLine(sb, "ATTRS", formatAttributes(event.getAttributes()));
        appendFooter(sb);
        return sb.toString();
    }

    private String buildFailureMessage(SqlLogEvent event) {
        StringBuilder sb = new StringBuilder(512);
        appendBanner(sb, "FAIL", event);
        appendLine(sb, "SQL", formatSql(event.getSql()));
        appendLine(sb, "PARAMS", formatParams(event.getParams()));
        appendLine(sb, "COST", event.getElapsedMillis() + " ms");
        appendLine(sb, "ERROR", summarizeThrowable(event.getException()));
        appendOptionalLine(sb, "RESULT_TYPE", event.getResultType() == null ? null : event.getResultType().getName());
        appendOptionalLine(sb, "ATTRS", formatAttributes(event.getAttributes()));
        appendFooter(sb);
        return sb.toString();
    }

    private void appendBanner(StringBuilder sb, String status, SqlLogEvent event) {
        sb.append(System.lineSeparator());
        sb.append("┌─────────────────────── SQL ").append(status)
                .append(" [").append(formatSqlType(event.getSqlType())).append("] ──────────────────────────────");
    }

    private void appendLine(StringBuilder sb, String label, String value) {
        sb.append(System.lineSeparator())
                .append("│ ")
                .append(padRight(label, 11))
                .append(": ")
                .append(value);
    }

    private void appendOptionalLine(StringBuilder sb, String label, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        appendLine(sb, label, value);
    }

    private void appendFooter(StringBuilder sb) {
        sb.append(System.lineSeparator())
                .append("└───────────────────────────────────────────────────────────────────────────");
    }

    private String formatSqlType(SqlType sqlType) {
        return sqlType == null ? "UNKNOWN" : sqlType.name();
    }

    private String formatSql(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            return "<empty>";
        }
        return sql.trim().replaceAll("\\s+", " ");
    }

    private String formatParams(List<Object> params) {
        if (params == null || params.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(formatValue(params.get(i)));
        }
        return sb.append("]").toString();
    }

    private String formatGeneratedKeyTargets(List<?> generatedKeyTargets) {
        if (generatedKeyTargets == null || generatedKeyTargets.isEmpty()) {
            return null;
        }
        return summarizeResult(generatedKeyTargets);
    }

    private String formatAttributes(Map<String, Object> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder("{");
        int index = 0;
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            if (index++ > 0) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append("=").append(formatValue(entry.getValue()));
        }
        return sb.append("}").toString();
    }

    private String summarizeResult(Object result) {
        if (result == null) {
            return "null";
        }
        if (result instanceof Collection<?> collection) {
            return result.getClass().getSimpleName() + "(size=" + collection.size() + ")";
        }
        if (result instanceof Map<?, ?> map) {
            return result.getClass().getSimpleName() + "(size=" + map.size() + ")";
        }
        if (result.getClass().isArray()) {
            return result.getClass().getComponentType().getSimpleName() + "[](size=" + Array.getLength(result) + ")";
        }
        if (result instanceof Number || result instanceof Boolean) {
            return String.valueOf(result);
        }
        String text = String.valueOf(result).replaceAll("\\s+", " ").trim();
        if (text.length() > 200) {
            return text.substring(0, 200) + "...";
        }
        return text;
    }

    private String summarizeThrowable(Throwable throwable) {
        if (throwable == null) {
            return "unknown";
        }
        String message = throwable.getMessage();
        if (message == null || message.trim().isEmpty()) {
            return throwable.getClass().getName();
        }
        return throwable.getClass().getName() + ": " + message.trim();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof CharSequence
                || value instanceof Temporal
                || value instanceof Date) {
            return "'" + value + "'(" + detectTypeName(value) + ")";
        }
        if (value instanceof Collection<?> collection) {
            return value.getClass().getSimpleName() + "(size=" + collection.size() + ")";
        }
        if (value instanceof Map<?, ?> map) {
            return value.getClass().getSimpleName() + "(size=" + map.size() + ")";
        }
        if (value.getClass().isArray()) {
            return value.getClass().getComponentType().getSimpleName() + "[](size=" + Array.getLength(value) + ")";
        }
        if (value instanceof Number) {
            return value + "(number)";
        }
        if (value instanceof Boolean) {
            return value + "(bool)";
        }
        if (value instanceof Enum<?>) {
            return "'" + value + "'(enum)";
        }
        return "'" + value + "'(" + detectTypeName(value) + ")";
    }

    private String detectTypeName(Object value) {
        if (value instanceof CharSequence) {
            return "str";
        }
        if (value instanceof LocalDate) {
            return "date";
        }
        if (value instanceof LocalDateTime
                || value instanceof OffsetDateTime
                || value instanceof ZonedDateTime
                || value instanceof Date) {
            return "datetime";
        }
        if (value instanceof LocalTime) {
            return "time";
        }
        if (value instanceof Temporal) {
            return "datetime";
        }
        return value.getClass().getSimpleName();
    }

    private String padRight(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        return text + " ".repeat(width - text.length());
    }
}
