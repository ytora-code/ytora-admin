package xyz.ytora.core.rbac.user.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.user.model.data.SysUserData;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.core.rbac.user.model.param.SysUserParam;

/**
 * 用户模块类型转换mapper
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysUserMapper {
    SysUserMapper mapper = Mappers.getMapper(SysUserMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysUser toEntity(SysUserParam param);

    /**
     * ENTITY 转 DATA
     */
    SysUserData toData(SysUser entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysUser toEntity(SysUserExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysUserExcel toExcel(SysUserData data);
}
