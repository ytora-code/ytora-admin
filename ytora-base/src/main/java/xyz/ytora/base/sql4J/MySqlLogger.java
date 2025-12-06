package xyz.ytora.base.sql4J;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ytora.sql4j.log.ISqlLogger;

/**
 * 默认的 SQL 日志记录器
 */
public class MySqlLogger implements ISqlLogger {
    private static final Logger log = LoggerFactory.getLogger(MySqlLogger.class);

    @Override
    public void error(Object msg, Object... args) {
        log.error(String.valueOf(msg), args);
    }

    @Override
    public void warn(Object msg, Object... args) {
        log.warn(String.valueOf(msg), args);
    }

    @Override
    public void info(Object msg, Object... args) {
        log.info(String.valueOf(msg), args);
    }

    @Override
    public void debug(Object msg, Object... args) {
        log.debug(String.valueOf(msg), args);
    }
}
