package xyz.ytora.core.sys.log.logback.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 正常日志过滤器
 *
 * <p>只记录error以下级别的非sql日志</p>
 *
 * @author ytora 
 * @since 1.0
 */
public class NormalLogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event == null) {
            return FilterReply.DENY;
        }

        String loggerName = event.getLoggerName();
        if ("sql".equals(loggerName) || (loggerName != null && loggerName.startsWith("sql."))) {
            return FilterReply.DENY;
        }

        Level level = event.getLevel();
        if (Level.ERROR_INT > level.levelInt) {
            return FilterReply.ACCEPT;
        }

        return FilterReply.DENY;
    }
}
