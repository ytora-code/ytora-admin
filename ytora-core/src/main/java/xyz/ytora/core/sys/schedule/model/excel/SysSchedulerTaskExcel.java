package xyz.ytora.core.sys.schedule.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.schedule.model.SysSchedulerTaskMapper;
import xyz.ytora.core.sys.schedule.model.entity.SysSchedulerTask;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("调度任务列表")
public class SysSchedulerTaskExcel extends BaseExcel<SysSchedulerTask> {

    /**
     * 任务名称
     */
    @Excel("任务名称")
    private String taskName;

    /**
     * 任务code
     */
    @Excel("任务code")
    private String taskCode;

    /**
     * 任务执行CRON
     */
    @Excel("任务执行CRON")
    private String cron;

    /**
     * 任务类型
     */
    @Excel("任务类型")
    private Short type;

    /**
     * 任务参数
     */
    @Excel("任务参数")
    private String params;

    @Override
    public SysSchedulerTask toEntity() {
        SysSchedulerTaskMapper mapper = SysSchedulerTaskMapper.mapper;
        return mapper.toEntity(this);
    }
}
