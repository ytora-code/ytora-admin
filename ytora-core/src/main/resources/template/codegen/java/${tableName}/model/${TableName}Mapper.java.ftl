<#assign packageBase = path>
<#if module?? && module?has_content>
    <#assign packageBase = packageBase + "." + module>
</#if>
<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
package ${packageBase}.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import xyz.ytora.base.mapper.GlobalMapperConfig;
import ${packageBase}.model.data.${TableName}Data;
import ${packageBase}.model.entity.${TableName};
import ${packageBase}.model.excel.${TableName}Excel;
import ${packageBase}.model.param.${TableName}Param;

/**
 * ${businessName}模块类型转换mapper
 *
 * @author ${currentUser!"ytora"}
 * @since ${currentVersion!"1.0"}
 */
@Mapper(config = GlobalMapperConfig.class)
public interface ${TableName}Mapper {

    ${TableName}Mapper mapper = Mappers.getMapper(${TableName}Mapper.class);

    /**
     * PARAM 转为 ENTITY
     */
    ${TableName} toEntity(${TableName}Param param);

    /**
     * ENTITY 转 DATA
     */
    ${TableName}Data toData(${TableName} entity);

    /**
     * EXCEL 转 ENTITY
     */
    ${TableName} toEntity(${TableName}Excel excel);

    /**
     * DATA 转 EXCEL
     */
    ${TableName}Excel toExcel(${TableName}Data data);

}
