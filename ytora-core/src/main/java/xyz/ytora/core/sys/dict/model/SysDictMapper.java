package xyz.ytora.core.sys.dict.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.core.rbac.user.model.req.SysUserReq;
import xyz.ytora.core.rbac.user.model.resp.SysUserResp;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.model.excel.SysDictExcel;
import xyz.ytora.core.sys.dict.model.req.SysDictReq;
import xyz.ytora.core.sys.dict.model.resp.SysDictResp;

/**
 * 用户模块类型转换mapper
 */
@Mapper
public interface SysDictMapper {
    SysDictMapper mapper = Mappers.getMapper(SysDictMapper.class);

    /**
     * REQ 转为 ENTITY
     */
    SysDict toEntity(SysDictReq param);

    /**
     * ENTITY 转 RESP
     */
    SysDictResp toResp(SysDict entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDict toEntity(SysDictExcel data);

    /**
     * ENTITY 转 EXCEL
     */
    SysDictExcel toExcel(SysDictResp data);
}
