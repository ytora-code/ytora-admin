package xyz.ytora.base.scheduler.timewheel;

import java.util.concurrent.CountDownLatch;

/**
 * 取消任务命令，由时间轮线程串行处理
 *
 * @author ytora 
 * @since 1.0
 */
final class CancelRequest {
    final TimeWheelTask task;
    CountDownLatch latch = new CountDownLatch(1);
    boolean cancelled;

    CancelRequest(TimeWheelTask task) {
        this.task = task;
    }
}
