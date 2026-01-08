package xyz.ytora.core.rbac.depart.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.excel.SysDepartExcel;
import xyz.ytora.core.rbac.depart.model.req.SysDepartReq;
import xyz.ytora.core.rbac.depart.model.resp.SysDepartResp;

/**
 * created by YT on 2025/9/1 15:58:27
 * <br/>
 */
@Mapper
public interface SysDepartMapper {
    SysDepartMapper mapper = Mappers.getMapper(SysDepartMapper.class);

    SysDepart reqToEntity(SysDepartReq req);

    SysDepartResp entityToResp(SysDepart SysDepart);

    /**
     * EXCEL 转 ENTITY
     */
    SysDepart toEntity(SysDepartExcel SysDepartExcel);

    /**
     * ENTITY 转 EXCEL
     */
    SysDepartExcel toExcel(SysDepartResp SysDepartResp);
}
