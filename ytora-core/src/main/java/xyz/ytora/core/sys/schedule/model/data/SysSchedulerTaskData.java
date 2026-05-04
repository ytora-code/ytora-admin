package xyz.ytora.core.sys.schedule.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.schedule.model.SysSchedulerTaskMapper;
import xyz.ytora.core.sys.schedule.model.entity.SysSchedulerTask;
import xyz.ytora.core.sys.schedule.model.excel.SysSchedulerTaskExcel;
import xyz.ytora.sqlux.core.anno.Column;

/**
 * 调度任务响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "调度任务表响应数据")
public class SysSchedulerTaskData extends BaseData<SysSchedulerTask> {

    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    private String taskName;

    /**
     * 任务code
     */
    @Schema(description = "任务code")
    private String taskCode;

    /**
     * 任务执行CRON
     */
    @Schema(description = "任务执行CRON")
    private String cron;

    /**
     * 任务类型
     */
    @Schema(description = "任务类型")
    private Short type;

    /**
     * 任务参数
     */
    @Schema(description = "任务参数")
    private String params;

    /**
     * 任务状态
     */
    @Schema(description = "任务状态，1-未启动/2-运行中")
    private Short status;

    @Override
    public SysSchedulerTaskExcel toExcel() {
        SysSchedulerTaskMapper mapper = SysSchedulerTaskMapper.mapper;
        return mapper.toExcel(this);
    }
}
