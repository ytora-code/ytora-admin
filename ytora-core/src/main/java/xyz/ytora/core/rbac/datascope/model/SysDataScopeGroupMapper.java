package xyz.ytora.core.rbac.datascope.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeGroupData;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.excel.SysDataScopeGroupExcel;
import xyz.ytora.core.rbac.datascope.model.param.SysDataScopeGroupParam;

/**
 * 数据范围组模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysDataScopeGroupMapper {

    SysDataScopeGroupMapper mapper = Mappers.getMapper(SysDataScopeGroupMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDataScopeGroup toEntity(SysDataScopeGroupParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDataScopeGroupData toData(SysDataScopeGroup entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDataScopeGroup toEntity(SysDataScopeGroupExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDataScopeGroupExcel toExcel(SysDataScopeGroupData data);

}
