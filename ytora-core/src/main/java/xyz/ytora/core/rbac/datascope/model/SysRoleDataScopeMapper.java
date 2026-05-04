package xyz.ytora.core.rbac.datascope.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.datascope.model.data.SysRoleDataScopeData;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;
import xyz.ytora.core.rbac.datascope.model.excel.SysRoleDataScopeExcel;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeParam;

/**
 * 角色-数据范围关系模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysRoleDataScopeMapper {

    SysRoleDataScopeMapper mapper = Mappers.getMapper(SysRoleDataScopeMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysRoleDataScope toEntity(SysRoleDataScopeParam param);

    /**
     * ENTITY 转 DATA
     */
    SysRoleDataScopeData toData(SysRoleDataScope entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysRoleDataScope toEntity(SysRoleDataScopeExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysRoleDataScopeExcel toExcel(SysRoleDataScopeData data);

}
