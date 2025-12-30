package xyz.ytora.core.rbac.role.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.req.SysRoleReq;
import xyz.ytora.core.rbac.role.model.resp.SysRoleResp;

/**
 * 用户模块类型转换mapper
 */
@Mapper
public interface SysRoleMapper {
    SysRoleMapper mapper = Mappers.getMapper(SysRoleMapper.class);

    /**
     * REQ 转为 ENTITY
     */
    SysRole toEntity(SysRoleReq sysRoleReq);

    /**
     * ENTITY 转 RESP
     */
    SysRoleResp toResp(SysRole sysRole);
}
