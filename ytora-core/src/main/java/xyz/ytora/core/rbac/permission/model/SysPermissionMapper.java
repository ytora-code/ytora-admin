package xyz.ytora.core.rbac.permission.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.excel.SysPermissionExcel;
import xyz.ytora.core.rbac.permission.model.param.SysPermissionParam;

/**
 * 用户模块类型转换mapper
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysPermissionMapper {
    SysPermissionMapper mapper = Mappers.getMapper(SysPermissionMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysPermission toEntity(SysPermissionParam param);

    /**
     * ENTITY 转 DATA
     */
    SysPermissionData toData(SysPermission entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysPermission toEntity(SysPermissionExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysPermissionExcel toExcel(SysPermissionData data);
}
