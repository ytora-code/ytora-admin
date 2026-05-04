package xyz.ytora.core.sys.recyclebin.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.recyclebin.model.data.SysRecycleBinData;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import xyz.ytora.core.sys.recyclebin.model.excel.SysRecycleBinExcel;
import xyz.ytora.core.sys.recyclebin.model.param.SysRecycleBinParam;

/**
 * 回收站模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysRecycleBinMapper {

    SysRecycleBinMapper mapper = Mappers.getMapper(SysRecycleBinMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysRecycleBin toEntity(SysRecycleBinParam param);

    /**
     * ENTITY 转 DATA
     */
    SysRecycleBinData toData(SysRecycleBin entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysRecycleBin toEntity(SysRecycleBinExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysRecycleBinExcel toExcel(SysRecycleBinData data);

}
