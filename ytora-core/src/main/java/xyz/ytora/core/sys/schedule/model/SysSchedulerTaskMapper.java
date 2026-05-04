package xyz.ytora.core.sys.schedule.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.schedule.model.data.SysSchedulerTaskData;
import xyz.ytora.core.sys.schedule.model.entity.SysSchedulerTask;
import xyz.ytora.core.sys.schedule.model.excel.SysSchedulerTaskExcel;
import xyz.ytora.core.sys.schedule.model.param.SysSchedulerTaskParam;

/**
 * 调度任务模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysSchedulerTaskMapper {
    SysSchedulerTaskMapper mapper = Mappers.getMapper(SysSchedulerTaskMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysSchedulerTask toEntity(SysSchedulerTaskParam param);

    /**
     * ENTITY 转 DATA
     */
    SysSchedulerTaskData toData(SysSchedulerTask entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysSchedulerTask toEntity(SysSchedulerTaskExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysSchedulerTaskExcel toExcel(SysSchedulerTaskData data);
}
