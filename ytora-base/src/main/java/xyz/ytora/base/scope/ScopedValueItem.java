package xyz.ytora.base.scope;

/**
 * 统一维护所有的 ScopedValue
 */
public interface ScopedValueItem {
    /**
     * 定时任务日志ID上下文
     */
    ScopedValue<String> TASK_LOG_ID = ScopedValue.newInstance();

    /**
     * SQL操作的来源
     */
    ScopedValue<String> SQL_SOURCE = ScopedValue.newInstance();

    /**
     * 数据源上下文
     */
    ScopedValue<String> DS_CONTEXT = ScopedValue.newInstance();
}


