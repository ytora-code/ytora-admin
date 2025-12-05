package xyz.ytora.base.scheduler.timewheel;

import lombok.extern.slf4j.Slf4j;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.ytool.date.Crons;
import  xyz.ytora.base.scheduler.Task;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * created by yangtong on 2025/5/21 15:06:36
 * <br />
 * 基于时间轮算法的调度器
 */
@Slf4j
public final class TimeWheelSchedulerImpl implements ITimeWheelScheduler {

    //时间轮，本质是数组，通过求余模拟时间轮的刻度
    private final TimeWheelTask[] wheel;

    /**
     * 时间轮线程
     */
    private final Thread wheelThread;

    /**
     * 时间轮的槽位，默认65536
     */
    private int slots = 65536;
    /**
     * 时间轮每一个槽位的刻度默认是10ms
     */
    private long tick = 10;

    /**
     * 每个时间槽的纳秒间隔时间，根据tick计算得来，用于更精准的计算
     */
    private final long tickNano;

    /**
     * 由slots * tick 进行计算得来，代表了时间轮中可容纳的最大毫秒延迟的任务
     * 当时间轮指针从0出发移动再返回至0，总共经历了bound毫秒
     * 如果某个任务的延迟时间超过了bound，则会被放入waitSet中
     */
    private final long bound;

    /**
     * mask = slots - 1， 当slots是2的次幂时，mask是其掩码，用于方便求余
     * 比如slots是1000，则mask是0111，通过currentSlot & mask得到余数，也就是当前槽位在时间轮上的索引
     * 所以slots必须是2的次幂
     */
    private final int mask;

    /**
     * 由mask >> 1计算而来，用于判断访问TreeSet集合的时机
     * 如果(slot & cMask) == 0，则访问waitSet，判断是否需要将元素从集合转移至时间轮中，也就是时间轮开始时和转到一半时访问一次waitSet
     */
    private final int cMask;

    /**
     * 存放时间间隔较长的任务
     * waitSet中的任务执行期限均要超过bound值，那么我们只需要以小于bound值的间隔周期访问TreeSet集合即可
     */
    private final TreeSet<TimeWheelTask> waitSet = new TreeSet<>(TimeWheelTask::compareTo);

    /**
     * 注册的新任务，暂时存放在该队列里，等待时间轮线程将其放入对应插槽
     * 取消任务、停止时间轮本身也是任务，也放在该队列里
     */
    private final Queue<TimeWheelTask> newTaskQueue;

    /**
     * 特殊任务，用于表示停止时间轮的任务
     */
    private final TimeWheelTask exitTask = new TimeWheelTask("0 0 0 1 1 ? 1970", () -> {});

    /**
     * 保存当前调度器中存在的所有任务
     */
    private final Map<String, TimeWheelTask> existTask;

    private static final String WHEEL_THREAD_NAME_PREFIX = "wheel-thead";

    /**
     * 表示该类是否实例化，用来保证单例
     */
    private static final AtomicBoolean INSTANCE_Flag = new AtomicBoolean(false);

    /**
     * 表示时间轮是否已经启动
     */
    private static final AtomicBoolean RUN_Flag = new AtomicBoolean(false);

    /**
     * 创建单例对象
     */
    public static final TimeWheelSchedulerImpl INSTANCE = new TimeWheelSchedulerImpl(null, null);

    private TimeWheelSchedulerImpl(Integer slots, Long tick) {
        if (slots != null) {
            this.slots = slots;
        }
        if (tick != null) {
            this.tick = tick;
        }

        if (!INSTANCE_Flag.compareAndSet(false, true)) {
            throw new BaseException("时间轮对象不能重复创建");
        }
        if (this.slots < 256) {
            throw new BaseException("时间轮槽位数不能小于256");
        }
        if (Integer.bitCount(this.slots) != 1) {
            throw new BaseException("时间轮槽位数必须是2的次幂");
        }
        if (this.tick < 10) {
            throw new BaseException("时间轮槽位的刻度不能低于10ms");
        }
        this.mask = this.slots - 1;
        this.tickNano = Duration.ofMillis(this.tick).toNanos();
        this.bound = this.slots * this.tick;
        this.cMask = mask >> 1;
        this.wheel = new TimeWheelTask[this.slots];
        this.newTaskQueue = new ConcurrentLinkedQueue<>();
        this.existTask = new ConcurrentHashMap<>(1024);
        //创建一个虚拟线程作为时间轮线程，但并不启动
        this.wheelThread = Thread.ofPlatform().name("time-wheel-thead").unstarted(this::run);
    }

    /**
     * 时间轮线程的主体方法<br/>
     * 1.获取当前槽位对应的时间，计算出下一个槽位的位置及其对应时间<br/>
     * 2.检索newTaskQueue，执行任务的注册与反注册，并判断是否需要退出时间轮<br/>
     * 3.使用当前槽位和cMask进行 按位与 操作，来判断是否需要检索waitSet结构，将临近执行的任务转移到时间轮中<br/>
     * 4.执行当前槽位中的所有任务<br/>
     */
    private void run() {
        //时间轮的指针从0开始
        int slot = 0;

        //得到当前纳秒和毫秒数
        long nano = System.nanoTime();
        long milli = System.currentTimeMillis();

        //时间轮线程不断循环
        while (true) {
            final long currentMilli = milli;
            final int currentSlot = slot;
            //计算下一次执行的时间
            nano = nano + tickNano;
            milli = milli + tick;
            //通过求余，计算当前槽位索引
            slot = (slot + 1) & mask;

            //时间轮线程的每次执行，都要经过几个步骤
            //step1，从newTaskQueue中取出新任务，注册到时间轮上
            for (; ; ) {
                if (log.isDebugEnabled()) {
                    //log.debug("开始新一轮循环，时间轮当前刻度：{},总刻度：{}", currentSlot, mask + 1);
                }
                TimeWheelTask newTask = newTaskQueue.poll();
                //取不到新任务，则跳出循环
                if (newTask == null) {
                    break;
                }
                //停止时间轮，结束while true死循环
                else if (newTask == exitTask) {
                    return;
                }
                //注册新任务
                else {
                    //计算新任务下一次执行时间
                    long delayMillis = Math.max(newTask.getExecMilli() - currentMilli, 0);
                    int pos = calculatePos(currentSlot, delayMillis);
                    register(newTask, pos, delayMillis);
                }
            }

            //step2，判断该不该访问waitSet，并将waitSet中的任务转移到时间轮中
            //(currentSlot & cMask) == 0，表示每轮时间轮开始、以及到一半的时候，该条件才成立
            if ((currentSlot & cMask) == 0) {
                //遍历waitSet中的所有任务
                Iterator<TimeWheelTask> iterator = waitSet.iterator();
                while (iterator.hasNext()) {
                    TimeWheelTask task = iterator.next();
                    long delayMillis = task.getExecMilli() - currentMilli;
                    //计算下一次任务的延迟时间，如果小于bound，则说明可以放入时间轮
                    if (delayMillis < bound) {
                        int pos = calculatePos(currentSlot, delayMillis);
                        iterator.remove();
                        register(task, pos, delayMillis);
                    }
                    //如果该任务下一次执行时间距当前时间的间隔大于bound，则继续将该任务放在waitSet
                    //某一个任务延迟时间大于bound，那么后面的任务肯定大于bound，直接break，退出step2
                    else {
                        break;
                    }
                }
            }

            //step3，开始真正执行时间轮当前槽位上的所有任务
            TimeWheelTask current = wheel[currentSlot];
            //获取到当前槽位所有任务后，立刻清空当前槽位，避免循环引用
            wheel[currentSlot] = null;
            while (current != null) {
                Task currentTask = current.getTask();
                //先得到下一个任务，避免被后面的current.next = null影响
                TimeWheelTask next = current.getNext();
                //只有该任务的pos跟当前槽位的索引匹配才执行任务，这是为了方便取消任务
                if (current.getPos() == currentSlot) {
                    //使用虚拟线程来执行任务，避免任务阻塞时间轮线程
                    Thread.ofVirtual().name(WHEEL_THREAD_NAME_PREFIX).start(currentTask::doTask);
                    //根据cron表达式，计算下一次执行时间距离现在的时间间隔
                    long nextExecMilli = Crons.nextTimeByCron(current.getCron(), current.getExecMilli());
                    long nextPeriod = nextExecMilli - current.getExecMilli();
//                    long now = System.currentTimeMillis();
//                    long nextPeriod = nextExecMilli - now;
                    // 避免落入当前槽位
                    if (nextPeriod <= 0) {
                        nextPeriod = tick;
                    }
                    int pos = calculatePos(currentSlot, nextPeriod);
                    //说明该任务后面不需要再执行了，将该任务从任务链表中断开
                    if (nextExecMilli == -1) {
                        current.setNext(null);
                    } else {
                        current.setExecMilli(nextExecMilli);
                        //在重新注册任务前，将该任务从链表中移除，避免循环引用
                        current.setNext(null);
                        //继续注册该任务的下一次执行
                        register(current, pos, nextPeriod);
                    }
                }
                //如果槽位与pos不匹配，说明该任务被取消，也将该任务从任务链表中断开
                else {
                    current.setNext(null);
                }
                //继续处理下一个任务
                current = next;
            }

            //当前槽位的所有任务都执行完毕了，将其置空
            //这一行注释掉，因为可能导致上面while循环里面的register方法影响正在遍历的链表
            //wheel[currentSlot] = null;

            //休眠一段时间后，进入下一个槽位
            LockSupport.parkNanos(nano - System.nanoTime());
        }
    }

    /**
     * 根据某个任务的delay延迟时间，计算该任务应该放在时间轮的哪个槽位
     *
     * @param currentSlot 当前槽位
     * @param delayMillis 延迟时间
     * @return 槽位索引
     */
    private int calculatePos(int currentSlot, long delayMillis) {
        return (int) ((currentSlot + (delayMillis / tick)) & mask);
    }

    /**
     * 将任务注册到时间轮上
     *
     * @param task         任务
     * @param pos         时间轮刻度
     * @param delayMillis 延迟毫秒
     */
    private void register(TimeWheelTask task, int pos, long delayMillis) {
        //延迟时间大于bound，说明该任务至少下一轮才能执行，放进waitSet中
        if (delayMillis >= bound) {
            task.setPos(-1);
            waitSet.add(task);
        }
        //直接放进时间轮中
        else {
            task.setPos(pos);
            TimeWheelTask current = wheel[pos];
            //如果当前槽位中已经有任务了，就将新任务插入到链表的头部
            if (current != null) {
                task.setNext(current);
            }
            wheel[pos] = task;
        }
    }

    @Override
    public void start() {
        if (!RUN_Flag.compareAndSet(false, true)) {
            throw new BaseException("已经处于启动状态，无法重复启动");
        }
        wheelThread.start();
        log.info("时间轮正在转动...");
    }

    @Override
    public void stop() throws InterruptedException {
        //插入一个exitTask任务，时间轮线程下一次循环发现新任务队列中有exitTask，就停止时间轮线程
        if (!newTaskQueue.offer(exitTask)) {
            throw new BaseException("新任务队列满了");
        }
        //走到这说明停止任务注册成功，等待时间轮的停止
        wheelThread.join();
        log.info("时间轮成功停止!");
    }

    @Override
    public String addTask(TimeWheelTask task) {
        //注册新任务
        if (!newTaskQueue.offer(task)) {
            throw new BaseException("新任务队列满了");
        }

        //产生该任务的唯一id
        UUID id = UUID.randomUUID();
        existTask.put(id.toString(), task);

        return id.toString();
    }

    @Override
    public Boolean cancelTask(String taskId) {
        TimeWheelTask task = existTask.get(taskId);
        if (task == null) {
            return false;
        }
        //从waitSet中移除该任务，如果有的话
        waitSet.remove(task);
        //如果该任务存在于时间轮中，则将其pos修改为-1
        task.setPos(-1);
        existTask.remove(taskId);
        return true;
    }
}
