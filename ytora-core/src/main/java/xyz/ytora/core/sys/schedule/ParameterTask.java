package xyz.ytora.core.sys.schedule;

import jakarta.annotation.Resource;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scheduler.Task;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.base.util.json.Jsons;
import xyz.ytora.core.sys.log.LogType;
import xyz.ytora.core.sys.log.logic.SysLogLogic;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.sqlux.orm.type.Text;
import xyz.ytora.toolkit.id.Ids;
import xyz.ytora.toolkit.text.Strs;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 带参数的任务
 *
 * @author ytora
 * @since 1.0
 */
public abstract class ParameterTask<T> implements Task {

    private String sourceParams;
    private T param;

    @Resource
    private SysLogLogic logLogic;

    /**
     * 注册参数
     * @param params 参数
     */
    @SuppressWarnings("unchecked")
    public void registerParams(String params) {
        if (Strs.isEmpty(params)) {
            params = "{}";
        }
        this.sourceParams = params;
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType pt) {
            Type actualType = pt.getActualTypeArguments()[0];
            if (actualType instanceof Class<?> actualClazz) {
                this.param = Jsons.fromJsonStr(params, (Class<T>) actualClazz);
                return;
            }
        }
        throw new IllegalArgumentException("ParameterTask 必须指定具体的泛型类型");
    }

    @Override
    public void doTask() {
        String traceId = Ids.nextUuid();
        SysLog log = new SysLog();
        log.setTraceId(traceId);
        log.setThread(Thread.currentThread().getName());
        log.setHappenPlace(this.getClass().getName());
        log.setParams(Text.of(sourceParams));
        try {
            Scopes.start().where(ScopedValueContext.TRACE_ID, traceId).run(() -> {
                doTask(param);
                return null;
            });
            log.setType(LogType.SCHEDULE_TASK_LOG);
            log.setContent(Text.of(this.getClass().getName() + "执行成功"));
        } catch (Throwable e) {
            log.setType(LogType.ERROR_LOG);
            log.setContent(Text.of(this.getClass().getName() + "执行失败: " + e.getMessage()));
            log.setErrorStack(e);

            throw new BaseException(e);
        } finally {
            logLogic.doLog(log);
        }
    }

    /**
     * 供定时任务方便地记录日志
     * @param msg 日志信息
     * @param args 参数
     */
    protected void log(String msg, Object... args) {
        SysLog log = new SysLog();
        log.setType(LogType.OTHER_LOG);
        log.setTraceId(ScopedValueContext.TRACE_ID.get());
        log.setThread(Thread.currentThread().getName());
        log.setHappenPlace(this.getClass().getName());
        log.setContent(Text.of(Strs.format(msg, args)));
        log.setParams(Text.of(sourceParams));

        logLogic.doLog(log);
    }

    public abstract void doTask(T params);
}
