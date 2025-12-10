package xyz.ytora.core.rbac.user.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.core.rbac.user.model.req.SysUserReq;
import xyz.ytora.core.rbac.user.model.resp.SysUserResp;

/**
 * 用户模块类型转换mapper
 */
@Mapper
public interface SysUserMapper {
    SysUserMapper mapper = Mappers.getMapper(SysUserMapper.class);

    /**
     * REQ 转为 ENTITY
     */
    SysUser toEntity(SysUserReq sysUserReq);

    /**
     * ENTITY 转 RESP
     */
    SysUserResp toResp(SysUser sysUser);

    /**
     * EXCEL 转 ENTITY
     */
    SysUser toEntity(SysUserExcel sysUserExcel);

    /**
     * ENTITY 转 EXCEL
     */
    SysUserExcel toExcel(SysUserResp sysUserResp);
}
