package xyz.ytora.core.rbac.permission.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.excel.SysPermissionExcel;
import xyz.ytora.core.rbac.permission.model.req.SysPermissionReq;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;

/**
 * created by YT on 2025/9/1 15:58:27
 * <br/>
 */
@Mapper
public interface SysPermissionMapper {
    SysPermissionMapper mapper = Mappers.getMapper(SysPermissionMapper.class);

    SysPermission toEntity(SysPermissionReq req);

    SysPermissionResp toResp(SysPermission SysPermission);

    /**
     * EXCEL 转 ENTITY
     */
    SysPermission toEntity(SysPermissionExcel SysPermissionExcel);

    /**
     * ENTITY 转 EXCEL
     */
    SysPermissionExcel toExcel(SysPermissionResp SysPermissionResp);
}
