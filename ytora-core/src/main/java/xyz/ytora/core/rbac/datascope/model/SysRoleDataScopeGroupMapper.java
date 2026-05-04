package xyz.ytora.core.rbac.datascope.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.datascope.model.data.SysRoleDataScopeGroupData;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.excel.SysRoleDataScopeGroupExcel;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeGroupParam;

/**
 * 角色-数据范围分组关系模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysRoleDataScopeGroupMapper {

    SysRoleDataScopeGroupMapper mapper = Mappers.getMapper(SysRoleDataScopeGroupMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysRoleDataScopeGroup toEntity(SysRoleDataScopeGroupParam param);

    /**
     * ENTITY 转 DATA
     */
    SysRoleDataScopeGroupData toData(SysRoleDataScopeGroup entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysRoleDataScopeGroup toEntity(SysRoleDataScopeGroupExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysRoleDataScopeGroupExcel toExcel(SysRoleDataScopeGroupData data);

}
