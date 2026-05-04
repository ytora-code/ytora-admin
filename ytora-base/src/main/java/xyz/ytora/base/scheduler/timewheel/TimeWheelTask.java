package xyz.ytora.base.scheduler.timewheel;

import  xyz.ytora.base.scheduler.Task;
import xyz.ytora.toolkit.time.cron.Crons;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于时间轮的任务
 */
public class TimeWheelTask implements Comparable<TimeWheelTask> {
    private static final AtomicLong SEQUENCE = new AtomicLong(0);

    //需要执行的具体任务
    private Task task;

    //该任务下一次执行的毫秒戳
    private long execMilli;

    //该任务的cron表达式
    private String cron;

    //链表中下一个任务的指针
    private TimeWheelTask next;

    //在时间轮中的具体槽位，如果处于waitSet则是-1
    private int pos;

    //任务唯一序号，用于waitSet稳定排序
    private final long sequence = SEQUENCE.incrementAndGet();

    //调度器分配的任务ID
    private String taskId;

    //任务是否已经取消
    private volatile boolean cancelled;

    public TimeWheelTask(String cron, Task task) {
        setCron(cron);
        setTask(task);
    }

    /**
     * 只能在本模块操作的方法
     */
    Task getTask() {
        return task;
    }

    void setTask(Task task) {
        if (task == null) {
            throw new NullPointerException("任务不能为空");
        }
        this.task = task;
    }

    String getCron() {
        return cron;
    }

    void setCron(String cron) {
        if (cron == null) {
            throw new NullPointerException("cron不能为空");
        }
        Long exeMilli = Crons.nextTimeByCron(cron);
        setExecMilli(exeMilli);
        this.cron = cron;
    }

    long getExecMilli() {
        return execMilli;
    }

    void setExecMilli(Long execMilli) {
        this.execMilli = execMilli;
    }

    void setNext(TimeWheelTask next) {
        this.next = next;
    }

    TimeWheelTask getNext() {
        return next;
    }

    void setPos(int pos) {
        this.pos = pos;
    }

    int getPos() {
        return pos;
    }

    long getSequence() {
        return sequence;
    }

    String getTaskId() {
        return taskId;
    }

    void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    boolean isCancelled() {
        return cancelled;
    }

    void cancel() {
        this.cancelled = true;
        this.pos = -1;
    }

    /**
     * 确保waitSet中的元素都是按照执行时间有序存放，在检索时，只要找到第一个不满足条件的节点，后面的节点一定是不满足条件的
     */
    @Override
    public int compareTo(TimeWheelTask other) {
        int compare = Long.compare(execMilli, other.execMilli);
        if (compare != 0) {
            return compare;
        }
        return Long.compare(sequence, other.sequence);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TimeWheelTask that && sequence == that.sequence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence);
    }
}
