package xyz.ytora.core.rbac.datascope.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeData;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.core.rbac.datascope.model.excel.SysDataScopeExcel;
import xyz.ytora.core.rbac.datascope.model.param.SysDataScopeParam;

/**
 * 数据范围模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysDataScopeMapper {

    SysDataScopeMapper mapper = Mappers.getMapper(SysDataScopeMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDataScope toEntity(SysDataScopeParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDataScopeData toData(SysDataScope entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDataScope toEntity(SysDataScopeExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDataScopeExcel toExcel(SysDataScopeData data);

}
