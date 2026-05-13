package xyz.ytora.core.rbac.permission.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.permission.model.data.SysRoleFormSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleFormSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysRoleFormSchemaExcel;
import xyz.ytora.core.rbac.permission.model.param.SysRoleFormSchemaParam;

/**
 * 角色-表单项关系模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysRoleFormSchemaMapper {

    SysRoleFormSchemaMapper mapper = Mappers.getMapper(SysRoleFormSchemaMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysRoleFormSchema toEntity(SysRoleFormSchemaParam param);

    /**
     * ENTITY 转 DATA
     */
    SysRoleFormSchemaData toData(SysRoleFormSchema entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysRoleFormSchema toEntity(SysRoleFormSchemaExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysRoleFormSchemaExcel toExcel(SysRoleFormSchemaData data);

}
