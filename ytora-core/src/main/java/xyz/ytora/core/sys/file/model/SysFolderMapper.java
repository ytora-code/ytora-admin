package xyz.ytora.core.sys.file.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.sys.file.model.data.SysFolderData;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.excel.SysFolderExcel;
import xyz.ytora.core.sys.file.model.param.SysFolderParam;

/**
 * 系统文件夹模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysFolderMapper {
    SysFolderMapper mapper = Mappers.getMapper(SysFolderMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysFolder toEntity(SysFolderParam param);

    /**
     * ENTITY 转 DATA
     */
    SysFolderData toData(SysFolder entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysFolder toEntity(SysFolderExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysFolderExcel toExcel(SysFolderData data);
}
