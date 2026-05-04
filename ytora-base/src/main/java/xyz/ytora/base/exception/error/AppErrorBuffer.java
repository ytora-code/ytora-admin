package xyz.ytora.base.exception.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.scope.ScopedValueContext;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.LongAdder;

/**
 * 应用错误缓冲区。
 * <p>
 * 仅保留最近 N 条错误，适合作为驾驶舱或后台诊断页面的数据来源。
 * <br/>
 * 存储介质为内存，因此应用重启后会自动清空，符合“最近错误”监控的预期。
 */
@Component
public class AppErrorBuffer {

    /**
     * 最近错误最大保留数量。
     */
    private static final int MAX_ERROR_SIZE = 20;

    /**
     * 累计错误数量。
     */
    private final LongAdder totalErrorCount = new LongAdder();

    /**
     * 最近错误双端队列，头部永远是最新数据。
     */
    private final ConcurrentLinkedDeque<AppErrorRecord> recentErrors = new ConcurrentLinkedDeque<>();

    /**
     * 在当前 ScopedValue 上下文中记录错误。
     */
    public void record(Throwable throwable, String category) {
        HttpServletRequest request = isBound(ScopedValueContext.REQUEST) ? ScopedValueContext.REQUEST.get() : null;
        LoginUser loginUser = isBound(ScopedValueContext.LOGIN_USER) ? ScopedValueContext.LOGIN_USER.get() : null;
        record(throwable, category, request, loginUser);
    }

    /**
     * 显式记录错误。
     */
    public void record(Throwable throwable, String category, HttpServletRequest request, LoginUser loginUser) {
        if (throwable == null) {
            return;
        }

        totalErrorCount.increment();

        AppErrorRecord record = new AppErrorRecord(
                System.currentTimeMillis(),
                category,
                throwable.getClass().getName(),
                throwable.getMessage(),
                request == null ? null : request.getRequestURI(),
                request == null ? null : request.getMethod(),
                request == null ? null : resolveClientIp(request),
                loginUser == null ? null : loginUser.getId(),
                loginUser == null ? null : loginUser.getUserName(),
                toStackTrace(throwable)
        );

        recentErrors.addFirst(record);
        while (recentErrors.size() > MAX_ERROR_SIZE) {
            recentErrors.pollLast();
        }
    }

    /**
     * 获取最近错误列表，按时间倒序。
     */
    public List<AppErrorRecord> recentErrors() {
        return List.copyOf(new ArrayList<>(recentErrors));
    }

    /**
     * 获取累计错误数。
     */
    public long totalErrorCount() {
        return totalErrorCount.sum();
    }

    /**
     * 当前上下文是否绑定了 ScopedValue。
     */
    private boolean isBound(java.lang.ScopedValue<?> scopedValue) {
        return scopedValue.isBound();
    }

    /**
     * 将堆栈转成字符串，便于前端直接展示。
     */
    private String toStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter(2048);
        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            return stringWriter.toString();
        }
    }

    /**
     * 尽量获取真实客户端 IP。
     */
    private String resolveClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }

        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isBlank()) {
            return realIp.trim();
        }

        return Objects.toString(request.getRemoteAddr(), null);
    }
}
