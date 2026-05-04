package xyz.ytora.core.rbac.role.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.role.model.data.SysRoleData;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.excel.SysRoleExcel;
import xyz.ytora.core.rbac.role.model.param.SysRoleParam;

/**
 * 用户模块类型转换mapper
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysRoleMapper {
    SysRoleMapper mapper = Mappers.getMapper(SysRoleMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysRole toEntity(SysRoleParam param);

    /**
     * ENTITY 转 DATA
     */
    SysRoleData toData(SysRole entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysRole toEntity(SysRoleExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysRoleExcel toExcel(SysRoleData data);
}
