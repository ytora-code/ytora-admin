package xyz.ytora.core.sys.schedule.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.schedule.model.SysSchedulerTaskMapper;
import xyz.ytora.core.sys.schedule.model.data.SysSchedulerTaskData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 调度任务表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_scheduler_task", idType = IdType.SNOWFLAKE, comment = "调度任务表")
public class SysSchedulerTask extends BaseEntity<SysSchedulerTask> {

    /**
     * 时间轮任务ID
     * <p>定时任务只是数据库静态的数据，要想让其"动“起来，需要将其注册到时间轮上，timeWheelTaskId上就是时间轮上的任务ID</p>
     */
    @Column(comment = "时间轮任务ID")
    private String timeWheelTaskId;

    /**
     * 任务名称
     */
    @Column(comment = "任务名称")
    private String taskName;

    /**
     * 任务code
     */
    @Column(comment = "任务code")
    private String taskCode;

    /**
     * 任务执行CRON
     */
    @Column(comment = "任务执行CRON")
    private String cron;

    /**
     * 任务类型
     */
    @Column(comment = "任务类型")
    private Short type;

    /**
     * 任务参数
     */
    @Column(comment = "任务参数")
    private String params;

    /**
     * 任务状态
     */
    @Column(comment = "任务状态，1-进行中/2-未启动")
    private Short status;

    @Override
    public SysSchedulerTaskData toData() {
        SysSchedulerTaskMapper mapper = SysSchedulerTaskMapper.mapper;
        return mapper.toData(this);
    }
}
