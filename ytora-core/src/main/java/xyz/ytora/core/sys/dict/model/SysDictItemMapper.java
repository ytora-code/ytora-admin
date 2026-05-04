package xyz.ytora.core.sys.dict.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.sys.dict.model.data.SysDictItemData;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;
import xyz.ytora.core.sys.dict.model.excel.SysDictItemExcel;
import xyz.ytora.core.sys.dict.model.param.SysDictItemParam;

/**
 * 字典项模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SysDictItemMapper {
    SysDictItemMapper mapper = Mappers.getMapper(SysDictItemMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDictItem toEntity(SysDictItemParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDictItemData toData(SysDictItem entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDictItem toEntity(SysDictItemExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDictItemExcel toExcel(SysDictItemData data);
}
