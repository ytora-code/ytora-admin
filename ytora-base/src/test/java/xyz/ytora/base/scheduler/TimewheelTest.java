package xyz.ytora.base.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xyz.ytora.base.scheduler.timewheel.ITimeWheelScheduler;
import xyz.ytora.base.scheduler.timewheel.TimeWheelTask;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 时间轮算法测试
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
public class TimewheelTest {

    private static final DateTimeFormatter LOG_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int DEFAULT_DURATION_MINUTES = 1;
    private static final int DEFAULT_MONITOR_INTERVAL_SECONDS = 30;
    private static final int DEFAULT_CANCEL_AFTER_EXECUTIONS = 5;
    private static final int DEFAULT_BURST_TASK_COUNT = 16;
    private static final int DEFAULT_CHURN_BATCH_SIZE = 4;
    private static final int DEFAULT_CHURN_CREATE_INTERVAL_SECONDS = 2;
    private static final int DEFAULT_CHURN_CANCEL_DELAY_MILLIS = 2500;
    private static final int WAIT_SET_DELAY_MINUTES = 12;

    @Test
    public void testTimewheel() throws Exception {
        int durationMinutes = Integer.getInteger("timewheel.duration.minutes", DEFAULT_DURATION_MINUTES);
        int monitorIntervalSeconds = Integer.getInteger("timewheel.monitor.interval.seconds", DEFAULT_MONITOR_INTERVAL_SECONDS);
        int cancelAfterExecutions = Integer.getInteger("timewheel.cancel.after.executions", DEFAULT_CANCEL_AFTER_EXECUTIONS);
        int burstTaskCount = Integer.getInteger("timewheel.burst.task.count", DEFAULT_BURST_TASK_COUNT);
        int churnBatchSize = Integer.getInteger("timewheel.churn.batch.size", DEFAULT_CHURN_BATCH_SIZE);
        int churnCreateIntervalSeconds = Integer.getInteger("timewheel.churn.create.interval.seconds", DEFAULT_CHURN_CREATE_INTERVAL_SECONDS);
        int churnCancelDelayMillis = Integer.getInteger("timewheel.churn.cancel.delay.millis", DEFAULT_CHURN_CANCEL_DELAY_MILLIS);

        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("timewheel.duration.minutes 必须大于 0");
        }
        if (monitorIntervalSeconds <= 0) {
            throw new IllegalArgumentException("timewheel.monitor.interval.seconds 必须大于 0");
        }
        if (cancelAfterExecutions <= 0) {
            throw new IllegalArgumentException("timewheel.cancel.after.executions 必须大于 0");
        }
        if (burstTaskCount <= 0) {
            throw new IllegalArgumentException("timewheel.burst.task.count 必须大于 0");
        }
        if (churnBatchSize <= 0) {
            throw new IllegalArgumentException("timewheel.churn.batch.size 必须大于 0");
        }
        if (churnCreateIntervalSeconds <= 0) {
            throw new IllegalArgumentException("timewheel.churn.create.interval.seconds 必须大于 0");
        }
        if (churnCancelDelayMillis <= 0) {
            throw new IllegalArgumentException("timewheel.churn.cancel.delay.millis 必须大于 0");
        }

        Duration runDuration = Duration.ofMinutes(durationMinutes);
        long startMillis = System.currentTimeMillis();
        long finishMillis = startMillis + runDuration.toMillis();

        ITimeWheelScheduler scheduler = ITimeWheelScheduler.instance();
        scheduler.start();

        AtomicInteger fastTaskCount = new AtomicInteger();
        AtomicInteger sameTimeTaskACount = new AtomicInteger();
        AtomicInteger sameTimeTaskBCount = new AtomicInteger();
        AtomicInteger cancelledBeforeRegisterCount = new AtomicInteger();
        AtomicInteger cancelledAfterRunningCount = new AtomicInteger();
        AtomicBoolean runningTaskCancelled = new AtomicBoolean(false);
        AtomicInteger waitSetTaskCount = new AtomicInteger();
        AtomicInteger burstTotalCount = new AtomicInteger();
        AtomicInteger churnCreatedCount = new AtomicInteger();
        AtomicInteger churnCancelRequestedCount = new AtomicInteger();
        AtomicInteger churnCancelSucceededCount = new AtomicInteger();
        AtomicInteger churnExecutionCount = new AtomicInteger();
        AtomicInteger churnPostCancelExecutionCount = new AtomicInteger();
        List<Thread> churnCancelThreads = new CopyOnWriteArrayList<>();
        List<AtomicInteger> burstCounters = new ArrayList<>(burstTaskCount);
        for (int i = 0; i < burstTaskCount; i++) {
            burstCounters.add(new AtomicInteger());
        }

        String fastTaskId = scheduler.addTask(new TimeWheelTask("*/1 * * * * ?", () -> {
            fastTaskCount.incrementAndGet();
        }));

        String sameTimeTaskAId = scheduler.addTask(new TimeWheelTask("*/2 * * * * ?", () -> {
            sameTimeTaskACount.incrementAndGet();
        }));

        String sameTimeTaskBId = scheduler.addTask(new TimeWheelTask("*/2 * * * * ?", () -> {
            sameTimeTaskBCount.incrementAndGet();
        }));

        String cancelledAfterRunningTaskId = scheduler.addTask(new TimeWheelTask("*/1 * * * * ?", () -> {
            cancelledAfterRunningCount.incrementAndGet();
        }));

        for (int i = 0; i < burstTaskCount; i++) {
            final int index = i;
            scheduler.addTask(new TimeWheelTask("*/5 * * * * ?", () -> {
                burstCounters.get(index).incrementAndGet();
                burstTotalCount.incrementAndGet();
            }));
        }

        String waitSetTaskId = null;
        LocalDateTime waitSetExpectedTime = LocalDateTime.now().plusMinutes(WAIT_SET_DELAY_MINUTES).plusSeconds(2);
        if (finishMillis - startMillis >= Duration.ofMinutes(WAIT_SET_DELAY_MINUTES).toMillis()) {
            String oneShotCron = String.format("%d %d %d %d %d ? %d",
                    waitSetExpectedTime.getSecond(),
                    waitSetExpectedTime.getMinute(),
                    waitSetExpectedTime.getHour(),
                    waitSetExpectedTime.getDayOfMonth(),
                    waitSetExpectedTime.getMonthValue(),
                    waitSetExpectedTime.getYear());
            waitSetTaskId = scheduler.addTask(new TimeWheelTask(oneShotCron, () -> {
                int count = waitSetTaskCount.incrementAndGet();
                log.info("[WAIT_SET] time={} expected={} count={}",
                        nowText(),
                        waitSetExpectedTime.format(LOG_TIME_FORMATTER),
                        count);
            }));
            log.info("[WAIT_SET] 已注册超过时间轮边界的任务 expected={} cron={}",
                    waitSetExpectedTime.format(LOG_TIME_FORMATTER),
                    oneShotCron);
        } else {
            log.info("[WAIT_SET] 本次运行时长不足 {} 分钟，跳过长延迟任务验证", WAIT_SET_DELAY_MINUTES);
        }

        for (int i = 0; i < 200; i++) {
            String taskId = scheduler.addTask(new TimeWheelTask("*/1 * * * * ?", () -> {
                int count = cancelledBeforeRegisterCount.incrementAndGet();
                log.error("[CANCELLED_BEFORE_REGISTER] time={} unexpectedCount={}", nowText(), count);
            }));
            scheduler.cancelTask(taskId);
        }
        log.info("[CANCELLED_BEFORE_REGISTER] 已提交并立即取消 200 个任务");

        Thread cancelThread = Thread.ofPlatform().name("timewheel-cancel-observer").start(() -> {
            while (System.currentTimeMillis() < finishMillis) {
                if (cancelledAfterRunningCount.get() >= cancelAfterExecutions) {
                    boolean cancelled = scheduler.cancelTask(cancelledAfterRunningTaskId);
                    runningTaskCancelled.set(cancelled);
                    log.info("[RUNNING_CANCEL] cancelAtCount={} cancelled={} time={}",
                            cancelledAfterRunningCount.get(),
                            cancelled,
                            nowText());
                    return;
                }
                sleepMillis(100);
            }
        });

        Thread monitorThread = Thread.ofPlatform().name("timewheel-monitor").start(() -> {
            while (System.currentTimeMillis() < finishMillis) {
                sleepMillis(monitorIntervalSeconds * 1000L);
                long elapsedSeconds = Math.max((System.currentTimeMillis() - startMillis) / 1000, 1);
                int burstMin = burstCounters.stream().mapToInt(AtomicInteger::get).min().orElse(0);
                int burstMax = burstCounters.stream().mapToInt(AtomicInteger::get).max().orElse(0);
                log.info("[SUMMARY] elapsedSeconds={} fast={} sameA={} sameB={} runningCancel={} cancelledImmediately={} burstTotal={} burstMin={} burstMax={} waitSet={} churnCreated={} churnCancelRequested={} churnCancelSucceeded={} churnExec={} churnPostCancelExec={}",
                        elapsedSeconds,
                        fastTaskCount.get(),
                        sameTimeTaskACount.get(),
                        sameTimeTaskBCount.get(),
                        cancelledAfterRunningCount.get(),
                        cancelledBeforeRegisterCount.get(),
                        burstTotalCount.get(),
                        burstMin,
                        burstMax,
                        waitSetTaskCount.get(),
                        churnCreatedCount.get(),
                        churnCancelRequestedCount.get(),
                        churnCancelSucceededCount.get(),
                        churnExecutionCount.get(),
                        churnPostCancelExecutionCount.get());
            }
        });

        Thread churnCreateThread = Thread.ofPlatform().name("timewheel-churn-create").start(() -> {
            long stopCreateMillis = finishMillis - Math.max(churnCancelDelayMillis + 2000L, 5000L);
            while (System.currentTimeMillis() < stopCreateMillis) {
                for (int i = 0; i < churnBatchSize; i++) {
                    AtomicBoolean cancelCompleted = new AtomicBoolean(false);
                    String taskId = scheduler.addTask(new TimeWheelTask("*/1 * * * * ?", () -> {
                        churnExecutionCount.incrementAndGet();
                        if (cancelCompleted.get()) {
                            churnPostCancelExecutionCount.incrementAndGet();
                        }
                    }));
                    churnCreatedCount.incrementAndGet();

                    Thread cancelWorker = Thread.ofPlatform().name("timewheel-churn-cancel").start(() -> {
                        sleepMillis(churnCancelDelayMillis);
                        churnCancelRequestedCount.incrementAndGet();
                        boolean cancelled = scheduler.cancelTask(taskId);
                        if (cancelled) {
                            cancelCompleted.set(true);
                            churnCancelSucceededCount.incrementAndGet();
                        }
                    });
                    churnCancelThreads.add(cancelWorker);
                }
                sleepMillis(churnCreateIntervalSeconds * 1000L);
            }
        });

        try {
            while (System.currentTimeMillis() < finishMillis) {
                sleepMillis(1000);
            }
        } finally {
            scheduler.cancelTask(fastTaskId);
            scheduler.cancelTask(sameTimeTaskAId);
            scheduler.cancelTask(sameTimeTaskBId);
            scheduler.cancelTask(cancelledAfterRunningTaskId);
            if (waitSetTaskId != null) {
                scheduler.cancelTask(waitSetTaskId);
            }
            cancelThread.join();
            monitorThread.join();
            churnCreateThread.join();
            for (Thread churnCancelThread : churnCancelThreads) {
                churnCancelThread.join();
            }
            sleepMillis(1500);
            scheduler.stop();
        }

        int burstMin = burstCounters.stream().mapToInt(AtomicInteger::get).min().orElse(0);
        int burstMax = burstCounters.stream().mapToInt(AtomicInteger::get).max().orElse(0);
        log.info("[FINAL] durationMinutes={} fast={} sameA={} sameB={} runningCancel={} cancelledImmediately={} burstTotal={} burstMin={} burstMax={} waitSet={} runningTaskCancelled={} churnCreated={} churnCancelRequested={} churnCancelSucceeded={} churnExec={} churnPostCancelExec={}",
                durationMinutes,
                fastTaskCount.get(),
                sameTimeTaskACount.get(),
                sameTimeTaskBCount.get(),
                cancelledAfterRunningCount.get(),
                cancelledBeforeRegisterCount.get(),
                burstTotalCount.get(),
                burstMin,
                burstMax,
                waitSetTaskCount.get(),
                runningTaskCancelled.get(),
                churnCreatedCount.get(),
                churnCancelRequestedCount.get(),
                churnCancelSucceededCount.get(),
                churnExecutionCount.get(),
                churnPostCancelExecutionCount.get());

        Assertions.assertTrue(fastTaskCount.get() > 0, "高频任务未执行");
        Assertions.assertTrue(sameTimeTaskACount.get() > 0, "同一时刻任务A未执行");
        Assertions.assertTrue(sameTimeTaskBCount.get() > 0, "同一时刻任务B未执行");
        Assertions.assertEquals(0, cancelledBeforeRegisterCount.get(), "立即取消的任务不应执行");
        Assertions.assertTrue(runningTaskCancelled.get(), "运行中取消任务未成功");
        Assertions.assertTrue(cancelledAfterRunningCount.get() <= cancelAfterExecutions + 1, "运行中取消任务执行次数超出预期");
        Assertions.assertTrue(burstMin > 0, "突发任务中存在未执行的任务");
        Assertions.assertTrue(burstMax - burstMin <= 1, "同批突发任务执行次数差异过大");
        Assertions.assertTrue(churnCreatedCount.get() > 0, "动态增删任务未创建");
        Assertions.assertEquals(churnCancelRequestedCount.get(), churnCancelSucceededCount.get(), "动态增删任务存在取消失败");
        Assertions.assertEquals(0, churnPostCancelExecutionCount.get(), "动态增删任务在取消完成后仍有执行");
        if (durationMinutes >= WAIT_SET_DELAY_MINUTES) {
            Assertions.assertEquals(1, waitSetTaskCount.get(), "长延迟任务执行次数异常");
        }
    }

    private static String nowText() {
        return LocalDateTime.now().format(LOG_TIME_FORMATTER);
    }

    private static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
