package xyz.ytora.core.sys.scheduler.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 定时任务表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_scheduler_task", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "定时任务表")
public class SysSchedulerTask extends BaseEntity<SysSchedulerTask> {

    /**
     * 任务名称
     */
    @Index(1)
    @Column(comment = "任务名称")
    private String taskName;

    /**
     * 任务执行周期
     */
    @Index(2)
    @Column(comment = "任务执行周期")
    private String cron;

    /**
     * 任务类型
     */
    @Index(3)
    @Column(comment = "任务类型")
    private Byte type;

    /**
     * 任务Bean名称
     */
    @Index(4)
    @Column(comment = "任务Bean名称")
    private String beanName;

    /**
     * 任务参数
     */
    @Index(5)
    @Column(comment = "任务参数")
    private String parameter;

    @Override
    public BaseResp<SysSchedulerTask> toResp() {
        return null;
    }
}