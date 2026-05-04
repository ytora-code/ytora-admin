package xyz.ytora.base.scheduler.timewheel;

import xyz.ytora.base.scheduler.IScheduler;

/**
 * 基于时间轮的调度器
 */
public sealed interface ITimeWheelScheduler extends IScheduler permits TimeWheelSchedulerImpl {

    /**
     * 添加任务
     * @param task 任务对象
     * @return 任务id
     */
    String addTask(TimeWheelTask task);

    /**
     * 取消任务
     * @param taskId 任务id
     * @return 取消任务成功与否
     */
    Boolean cancelTask(String taskId);

    static ITimeWheelScheduler instance() {
        return TimeWheelSchedulerImpl.INSTANCE;
    }
}
