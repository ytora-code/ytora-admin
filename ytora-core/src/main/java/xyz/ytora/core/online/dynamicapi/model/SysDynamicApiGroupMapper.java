package xyz.ytora.core.online.dynamicapi.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiGroupData;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiGroupExcel;
import xyz.ytora.core.online.dynamicapi.model.param.SysDynamicApiGroupParam;

/**
 * 动态API接口分组模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysDynamicApiGroupMapper {

    SysDynamicApiGroupMapper mapper = Mappers.getMapper(SysDynamicApiGroupMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDynamicApiGroup toEntity(SysDynamicApiGroupParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDynamicApiGroupData toData(SysDynamicApiGroup entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDynamicApiGroup toEntity(SysDynamicApiGroupExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDynamicApiGroupExcel toExcel(SysDynamicApiGroupData data);

}
