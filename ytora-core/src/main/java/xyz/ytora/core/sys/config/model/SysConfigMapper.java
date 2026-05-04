package xyz.ytora.core.sys.config.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.config.model.data.SysConfigData;
import xyz.ytora.core.sys.config.model.entity.SysConfig;
import xyz.ytora.core.sys.config.model.excel.SysConfigExcel;
import xyz.ytora.core.sys.config.model.param.SysConfigParam;

/**
 * 系统配置模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysConfigMapper {

    SysConfigMapper mapper = Mappers.getMapper(SysConfigMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysConfig toEntity(SysConfigParam param);

    /**
     * ENTITY 转 DATA
     */
    SysConfigData toData(SysConfig entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysConfig toEntity(SysConfigExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysConfigExcel toExcel(SysConfigData data);

}
