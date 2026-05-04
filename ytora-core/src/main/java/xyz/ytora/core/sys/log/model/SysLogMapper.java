package xyz.ytora.core.sys.log.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.log.model.data.SysLogData;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.core.sys.log.model.excel.SysLogExcel;
import xyz.ytora.core.sys.log.model.param.SysLogParam;

/**
 * 日志模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysLogMapper {
    SysLogMapper mapper = Mappers.getMapper(SysLogMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysLog toEntity(SysLogParam param);

    /**
     * ENTITY 转 DATA
     */
    SysLogData toData(SysLog entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysLog toEntity(SysLogExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysLogExcel toExcel(SysLogData data);
}
