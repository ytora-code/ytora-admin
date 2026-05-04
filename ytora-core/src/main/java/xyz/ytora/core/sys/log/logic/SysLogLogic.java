package xyz.ytora.core.sys.log.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.core.sys.log.LogType;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.core.sys.log.repo.SysLogRepo;
import xyz.ytora.sqlux.orm.type.Text;
import xyz.ytora.toolkit.id.Ids;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 日志模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysLogLogic extends BaseLogic<SysLog, SysLogRepo> {

    private static final StackWalker WALKER =
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    /**
     * 记录普通日志
     * @param msg 日志内容
     * @return 日志ID
     */
    public String doLog(String msg) {
        return doLog(LogType.NORMAL_LOG, msg);
    }

    /**
     * 记录指定类型的日志
     * @param logType 日志类型
     * @param msg 日志内容
     * @return 日志ID
     */
    public String doLog(LogType logType, String msg) {
        String threadName = Thread.currentThread().getName();
        // 获取是谁调用了这个方法（调用的类和方法）
        StackWalker.StackFrame caller = WALKER.walk(stream -> Objects.requireNonNull(stream
                .filter(frame -> !frame.getClassName().equals(this.getClass().getName()))
                .findFirst()
                .orElse(null))
        );

        SysLog log = new SysLog();
        log.setType(logType);
        log.setTraceId(Ids.nextUuid());
        log.setThread(threadName);
        log.setHappenPlace(caller.getClassName() + "#" + caller.getMethodName());
        log.setContent(Text.of(msg));

        doLog(log);

        return log.getId();
    }

    /**
     * 记录错误日志
     * @param ex 错误堆栈
     * @param msg 日志内容
     * @return 日志ID
     */
    public String doLog(Throwable ex, String msg) {
        String threadName = Thread.currentThread().getName();
        // 获取是谁调用了这个方法（调用的类和方法）
        StackWalker.StackFrame caller = WALKER.walk(stream -> Objects.requireNonNull(stream
                .filter(frame -> !frame.getClassName().equals(this.getClass().getName()))
                .findFirst()
                .orElse(null))
        );

        SysLog log = new SysLog();
        log.setTraceId(Ids.nextUuid());
        log.setType(LogType.ERROR_LOG);
        log.setErrorStack(ex);
        log.setErrorStack(ex);
        log.setThread(threadName);
        log.setHappenPlace(caller.getClassName() + "#" + caller.getMethodName());
        log.setContent(Text.of(msg));

        doLog(log);

        return log.getId();
    }

    public void doLog(SysLog log) {
        ScopedValue<LoginUser> scopedValue = ScopedValueContext.LOGIN_USER;
        if (scopedValue.isBound()) {
            LoginUser loginUser = scopedValue.get();
            log.setCreateBy(loginUser.getUserName());
        }
        log.setCreateTime(LocalDateTime.now());

        if (HttpUtil.getReq() != null) {
            log.setIp(HttpUtil.getIp());
        }

        repository.upsert(log);
    }

}
