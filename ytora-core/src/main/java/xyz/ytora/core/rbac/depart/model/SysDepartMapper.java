package xyz.ytora.core.rbac.depart.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.depart.model.data.SysDepartData;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.excel.SysDepartExcel;
import xyz.ytora.core.rbac.depart.model.param.SysDepartParam;

/**
 * 用户模块类型转换mapper
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysDepartMapper {
    SysDepartMapper mapper = Mappers.getMapper(SysDepartMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDepart toEntity(SysDepartParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDepartData toData(SysDepart entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDepart toEntity(SysDepartExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDepartExcel toExcel(SysDepartData data);
}
