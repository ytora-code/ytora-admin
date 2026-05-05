package xyz.ytora.core.rbac.permission.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import xyz.ytora.core.rbac.permission.model.data.SysFormSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysFormSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysFormSchemaExcel;
import xyz.ytora.core.rbac.permission.model.param.SysFormSchemaParam;

/**
 * 表格列结构模块类型转换mapper
 *
 * @author 杨桐
 * @since 1.0
 */
@Mapper(config = GlobalMapperConfig.class)
public interface SysFormSchemaMapper {

    SysFormSchemaMapper mapper = Mappers.getMapper(SysFormSchemaMapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    SysFormSchema toEntity(SysFormSchemaParam param);

    /**
     * ENTITY 转 DATA
     */
    SysFormSchemaData toData(SysFormSchema entity);

    /**
     * EXCEL 转 ENTITY
     */
    SysFormSchema toEntity(SysFormSchemaExcel excel);

    /**
     * DATA 转 EXCEL
     */
    SysFormSchemaExcel toExcel(SysFormSchemaData data);

}
