package xyz.ytora.core.sys.log.logback.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 只记录SQL日志
 *
 * @author ytora
 * @since 1.0
 */
public class SqlLogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event == null) {
            return FilterReply.DENY;
        }

        String loggerName = event.getLoggerName();
        if (loggerName == null || loggerName.isBlank()) {
            return FilterReply.DENY;
        }

        if ("sql".equals(loggerName) || loggerName.startsWith("sql.")) {
            return FilterReply.ACCEPT;
        }

        return FilterReply.DENY;
    }

}
