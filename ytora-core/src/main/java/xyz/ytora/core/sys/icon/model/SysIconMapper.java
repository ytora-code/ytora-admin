package xyz.ytora.core.sys.icon.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.sys.icon.model.data.SysIconData;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;
import xyz.ytora.core.sys.icon.model.excel.SysIconExcel;
import xyz.ytora.core.sys.icon.model.param.SysIconParam;

/**
 * 系统图标库模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SysIconMapper {
    SysIconMapper mapper = Mappers.getMapper(SysIconMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysIcon toEntity(SysIconParam param);

    /**
     * ENTITY 转 DATA
     */
    SysIconData toData(SysIcon entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysIcon toEntity(SysIconExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysIconExcel toExcel(SysIconData data);
}
