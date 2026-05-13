package xyz.ytora.core.rbac.permission.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.permission.model.data.SysRoleTableSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleTableSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysRoleTableSchemaExcel;
import xyz.ytora.core.rbac.permission.model.param.SysRoleTableSchemaParam;

/**
 * 角色-表字段关系模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysRoleTableSchemaMapper {

    SysRoleTableSchemaMapper mapper = Mappers.getMapper(SysRoleTableSchemaMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysRoleTableSchema toEntity(SysRoleTableSchemaParam param);

    /**
     * ENTITY 转 DATA
     */
    SysRoleTableSchemaData toData(SysRoleTableSchema entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysRoleTableSchema toEntity(SysRoleTableSchemaExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysRoleTableSchemaExcel toExcel(SysRoleTableSchemaData data);

}
