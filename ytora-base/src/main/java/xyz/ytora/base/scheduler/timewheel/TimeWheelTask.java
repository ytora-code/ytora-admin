package xyz.ytora.base.scheduler.timewheel;

import  xyz.ytora.base.scheduler.Task;
import xyz.ytora.ytool.date.Crons;

/**
 * 基于时间轮的任务
 */
public class TimeWheelTask implements Comparable<TimeWheelTask> {
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

    /**
     * 确保waitSet中的元素都是按照执行时间有序存放，在检索时，只要找到第一个不满足条件的节点，后面的节点一定是不满足条件的
     */
    @Override
    public int compareTo(TimeWheelTask other) {
        return Long.compare(execMilli, other.execMilli);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TimeWheelTask && task.equals(((TimeWheelTask) other).task);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
