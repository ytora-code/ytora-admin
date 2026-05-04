<#function javaTypeName type>
    <#if type == "java.sql.Date">
        <#return "LocalDate">
    <#elseif type == "java.sql.Time">
        <#return "LocalTime">
    <#elseif type == "java.sql.Timestamp">
        <#return "LocalDateTime">
    <#elseif type?starts_with("java.lang.")>
        <#return type?keep_after_last(".")>
    <#elseif type?contains(".")>
        <#return type?keep_after_last(".")>
    <#else>
        <#return type>
    </#if>
</#function>
<#function javaImport type>
    <#if type == "java.sql.Date">
        <#return "java.time.LocalDate">
    <#elseif type == "java.sql.Time">
        <#return "java.time.LocalTime">
    <#elseif type == "java.sql.Timestamp">
        <#return "java.time.LocalDateTime">
    <#elseif type?starts_with("java.lang.")>
        <#return "">
    <#elseif type?contains(".")>
        <#return type>
    <#else>
        <#return "">
    </#if>
</#function>
<#assign packageBase = path>
<#if module?? && module?has_content>
    <#assign packageBase = packageBase + "." + module>
</#if>
<#assign columns = []>
<#list columnMetas as column>
    <#if column.columnName != "version">
        <#assign columns = columns + [column]>
    </#if>
</#list>
<#assign imports = []>
<#list columns as column>
    <#assign importName = javaImport(column.javaType)>
    <#if importName?has_content && !imports?seq_contains(importName)>
        <#assign imports = imports + [importName]>
    </#if>
</#list>
package ${packageBase}.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import ${packageBase}.model.${TableName}Mapper;
import ${packageBase}.model.data.${TableName}Data;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;
<#list imports as importName>
import ${importName};
</#list>

/**
 * ${TableComment!TableName}
 *
 * @author ${currentUser!"ytora"}
 * @since ${currentVersion!"1.0"}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "${table_name}", idType = IdType.SNOWFLAKE, comment = "${TableComment!TableName}")
public class ${TableName} extends BaseEntity<${TableName}> {
<#list columns as column>

    /**
     * ${column.columnComment!column.columnName}
     */
    @Column(comment = "${column.columnComment!column.columnName}"<#if column.nullable?? && !column.nullable>, notNull = true</#if>)
    private ${javaTypeName(column.javaType)} ${column.columnName};
</#list>

    @Override
    public ${TableName}Data toData() {
        ${TableName}Mapper mapper = ${TableName}Mapper.mapper;
        return mapper.toData(this);
    }
}
