package xyz.ytora.base.scheduler;

/**
 * 任务对象
 */
@FunctionalInterface
public interface Task {

    /**
     * 执行任务
     */
    void doTask();

}
