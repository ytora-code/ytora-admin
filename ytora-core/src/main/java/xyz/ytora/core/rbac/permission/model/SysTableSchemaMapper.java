package xyz.ytora.core.rbac.permission.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.permission.model.data.SysTableSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysTableSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysTableSchemaExcel;
import xyz.ytora.core.rbac.permission.model.param.SysTableSchemaParam;

/**
 * 表格列结构模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysTableSchemaMapper {

    SysTableSchemaMapper mapper = Mappers.getMapper(SysTableSchemaMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysTableSchema toEntity(SysTableSchemaParam param);

    /**
     * ENTITY 转 DATA
     */
    SysTableSchemaData toData(SysTableSchema entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysTableSchema toEntity(SysTableSchemaExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysTableSchemaExcel toExcel(SysTableSchemaData data);

}
