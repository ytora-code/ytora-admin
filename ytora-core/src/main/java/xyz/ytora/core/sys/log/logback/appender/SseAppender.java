package xyz.ytora.core.sys.log.logback.appender;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 将捕获的日志通过SSE推送给前端
 *
 * @author ytora
 * @since 1.0
 */
public class SseAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd HH:mm:ss");

    @Override
    protected void append(ILoggingEvent event) {
        if (event == null) {
            return;
        }

        String formattedLog = buildFormattedLog(event);
        // 处理日志 formattedLog
    }

    /**
     * 构造日志文本。
     */
    public String buildFormattedLog(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder(256);
        sb.append(formatTimestamp(event.getTimeStamp()))
                .append(' ')
                .append(padRight(event.getLevel().toString(), 5))
                .append(" -> [")
                .append(padLeft(nullToUnknown(event.getThreadName()), 10))
                .append("] ")
                .append(padRight(nullToUnknown(event.getLoggerName()), 0))
                .append("::")
                .append(padRight(resolveLineNumber(event), 5))
                .append("-> ")
                .append(nullToEmpty(event.getFormattedMessage()));

        String throwableText = buildThrowableText(event.getThrowableProxy());
        if (!throwableText.isEmpty()) {
            sb.append(System.lineSeparator()).append(throwableText);
        }
        return sb.toString();
    }

    private String formatTimestamp(long timestamp) {
        return Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .format(DATE_TIME_FORMATTER);
    }

    private String resolveLineNumber(ILoggingEvent event) {
        StackTraceElement[] callerData = event.getCallerData();
        if (callerData == null || callerData.length == 0) {
            return "?";
        }

        int lineNumber = callerData[0].getLineNumber();
        return lineNumber > 0 ? String.valueOf(lineNumber) : "?";
    }

    private String buildThrowableText(IThrowableProxy throwableProxy) {
        if (throwableProxy == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(512);
        appendThrowable(sb, throwableProxy, false);
        return sb.toString();
    }

    private void appendThrowable(StringBuilder sb, IThrowableProxy throwableProxy, boolean causedBy) {
        if (throwableProxy == null) {
            return;
        }

        if (causedBy) {
            sb.append("Caused by: ");
        }
        sb.append(throwableProxy.getClassName());

        String message = throwableProxy.getMessage();
        if (message != null && !message.isBlank()) {
            sb.append(": ").append(message);
        }
        sb.append(System.lineSeparator());

        StackTraceElementProxy[] stackTrace = throwableProxy.getStackTraceElementProxyArray();
        if (stackTrace != null) {
            for (StackTraceElementProxy item : stackTrace) {
                sb.append("\tat ").append(item).append(System.lineSeparator());
            }
        }

        IThrowableProxy cause = throwableProxy.getCause();
        if (cause != null) {
            appendThrowable(sb, cause, true);
        }
    }

    private String nullToUnknown(String text) {
        return text == null || text.isBlank() ? "unknown" : text;
    }

    private String nullToEmpty(String text) {
        return text == null ? "" : text;
    }

    private String padRight(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        return text + " ".repeat(width - text.length());
    }

    private String padLeft(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        return " ".repeat(width - text.length()) + text;
    }
}
