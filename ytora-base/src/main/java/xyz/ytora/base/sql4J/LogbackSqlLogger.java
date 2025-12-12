package xyz.ytora.base.sql4J;

import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import xyz.ytora.sql4j.log.ISqlLogger;

/**
 * 默认的 SQL 日志记录器
 */
public class LogbackSqlLogger implements ISqlLogger {

    private static final String FQCN = LogbackSqlLogger.class.getName();

    private static final LocationAwareLogger log =
            (LocationAwareLogger) LoggerFactory.getLogger("sql");

    @Override
    public void info(Object msg, Object... args) {
        log.log(null, FQCN,
                LocationAwareLogger.INFO_INT,
                String.valueOf(msg),
                args,
                null);
    }

    @Override
    public void debug(Object msg, Object... args) {
        log.log(null, FQCN,
                LocationAwareLogger.DEBUG_INT,
                String.valueOf(msg),
                args,
                null);
    }

    @Override
    public void warn(Object msg, Object... args) {
        log.log(null, FQCN,
                LocationAwareLogger.WARN_INT,
                String.valueOf(msg),
                args,
                null);
    }

    @Override
    public void error(Object msg, Object... args) {
        log.log(null, FQCN,
                LocationAwareLogger.ERROR_INT,
                String.valueOf(msg),
                args,
                null);
    }
}