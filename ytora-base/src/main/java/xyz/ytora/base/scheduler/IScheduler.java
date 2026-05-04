package xyz.ytora.base.scheduler;

/**
 * 定时任务调度器
 */
public interface IScheduler {
    /**
     * 启动调度器
     */
    void start();

    /**
     * 停止调度器
     *
     * @throws InterruptedException 中断异常
     */
    void stop() throws InterruptedException;
}
