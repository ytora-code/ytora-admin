package xyz.ytora.core.sys.file.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.file.model.data.SysFileData;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.excel.SysFileExcel;
import xyz.ytora.core.sys.file.model.param.SysFileParam;

/**
 * 系统文件模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysFileMapper {
    SysFileMapper mapper = Mappers.getMapper(SysFileMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysFile toEntity(SysFileParam param);

    /**
     * ENTITY 转 DATA
     */
    SysFileData toData(SysFile entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysFile toEntity(SysFileExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysFileExcel toExcel(SysFileData data);
}
