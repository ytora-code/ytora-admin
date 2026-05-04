package xyz.ytora.core.sys.dict.model;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import xyz.ytora.core.sys.dict.model.data.SysDictData;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.model.excel.SysDictExcel;
import xyz.ytora.core.sys.dict.model.param.SysDictParam;

/**
 * 字典模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SysDictMapper {
    SysDictMapper mapper = Mappers.getMapper(SysDictMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysDict toEntity(SysDictParam param);

    /**
     * ENTITY 转 DATA
     */
    SysDictData toData(SysDict entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysDict toEntity(SysDictExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysDictExcel toExcel(SysDictData data);
}
