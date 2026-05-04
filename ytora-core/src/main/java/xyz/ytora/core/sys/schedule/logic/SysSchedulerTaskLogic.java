package xyz.ytora.core.sys.schedule.logic;

import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.scheduler.timewheel.ITimeWheelScheduler;
import xyz.ytora.base.scheduler.timewheel.TimeWheelTask;
import xyz.ytora.core.sys.schedule.ParameterTask;
import xyz.ytora.core.sys.schedule.TaskStatus;
import xyz.ytora.core.sys.schedule.model.entity.SysSchedulerTask;
import xyz.ytora.core.sys.schedule.repo.SysSchedulerTaskRepo;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;
import static xyz.ytora.sqlux.core.SQL.update;

/**
 * 调度任务模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysSchedulerTaskLogic extends BaseLogic<SysSchedulerTask, SysSchedulerTaskRepo> implements ApplicationRunner {

    /**
     * 时间轮，指定定时任务的主体
     */
    private final ITimeWheelScheduler timeWheelScheduler;

    private final ConfigurableApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) {
        // 清除所有任务的时间轮ID
        update(SysSchedulerTask.class)
                .set(SysSchedulerTask::getTimeWheelTaskId, null)
                .where(w -> w.ge(SysSchedulerTask::getId, 0))
                .submit();

        // 获取所有的调度任务
        List<SysSchedulerTask> tasks = select()
                .from(repository.getEntityClazz())
                .where(w -> w
                        .eq(SysSchedulerTask::getStatus, TaskStatus.RUNNING.status())
                        .isNotNull(SysSchedulerTask::getCron)
                )
                .submit(SysSchedulerTask.class);

        for (SysSchedulerTask task : tasks) {
            addTaskToTimeWheel(task);
        }
    }

    @PreDestroy
    public void preDestroy() {
        // 清除所有任务的时间轮ID
        update(SysSchedulerTask.class)
                .set(SysSchedulerTask::getTimeWheelTaskId, null)
                .where(w -> w.ge(SysSchedulerTask::getId, 0))
                .submit();
    }

    /**
     * 启动定时任务
     * @param id 任务ID
     */
    public void start(String id) {
        SysSchedulerTask task = queryById(id);
        if (TaskStatus.RUNNING.status() == task.getStatus()) {
            throw new IllegalStateException("任务正在运行，无需启动");
        }
        addTaskToTimeWheel(task);
        update(SysSchedulerTask.class)
                .set(SysSchedulerTask::getStatus, TaskStatus.RUNNING.status())
                .where(w -> w.eq(SysSchedulerTask::getId, task.getId()))
                .submit();
    }

    /**
     * 停止定时任务
     * @param id 任务ID
     */
    public void stop(String id) {
        SysSchedulerTask task = queryById(id);
        if (TaskStatus.STOPPED.status() == task.getStatus()) {
            throw new IllegalStateException("任务已经停止，无需再次停止");
        }
        if (task.getTimeWheelTaskId() == null) {
            throw new IllegalStateException("该任务未注册到时间轮，请联系管理员");
        }
        timeWheelScheduler.cancelTask(task.getTimeWheelTaskId());
        update(SysSchedulerTask.class)
                .set(SysSchedulerTask::getStatus, TaskStatus.STOPPED.status())
                .set(SysSchedulerTask::getTimeWheelTaskId, null)
                .where(w -> w.eq(SysSchedulerTask::getId, task.getId()))
                .submit();
    }

    /**
     * 立刻执行一次定时任务
     * @param id 任务ID
     */
    @Async
    public void runOnce(String id) {
        SysSchedulerTask task = queryById(id);
        ParameterTask<?> parameterTask = applicationContext.getBean(task.getTaskCode(), ParameterTask.class);
        parameterTask.doTask();
    }

    private void addTaskToTimeWheel(SysSchedulerTask task) {
        ParameterTask<?> parameterTask = applicationContext.getBean(task.getTaskCode(), ParameterTask.class);
        parameterTask.registerParams(task.getParams());
        TimeWheelTask timeWheelTask = new TimeWheelTask(task.getCron(), parameterTask);
        String taskId = timeWheelScheduler.addTask(timeWheelTask);
        update(SysSchedulerTask.class)
                .set(SysSchedulerTask::getTimeWheelTaskId, taskId)
                .where(w -> w.eq(SysSchedulerTask::getId, task.getId()))
                .submit();

    }
}
