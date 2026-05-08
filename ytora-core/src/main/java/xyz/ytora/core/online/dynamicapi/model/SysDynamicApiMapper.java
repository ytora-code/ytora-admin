package xyz.ytora.core.online.dynamicapi.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiData;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiExcel;
import xyz.ytora.core.online.dynamicapi.model.param.SysDynamicApiParam;

/**
 * 动态API接口模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysDynamicApiMapper {

    SysDynamicApiMapper mapper = Mappers.getMapper(SysDynamicApiMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDynamicApi toEntity(SysDynamicApiParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDynamicApiData toData(SysDynamicApi entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDynamicApi toEntity(SysDynamicApiExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDynamicApiExcel toExcel(SysDynamicApiData data);

}
