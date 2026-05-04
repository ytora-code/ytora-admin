<#function tsType type>
    <#if type == "java.lang.Boolean" || type == "boolean">
        <#return "boolean">
    <#elseif type == "java.sql.Date" || type == "java.sql.Time" || type == "java.sql.Timestamp" || type == "java.util.Date" || type == "java.time.LocalDate" || type == "java.time.LocalTime" || type == "java.time.LocalDateTime">
        <#return "Date">
    <#elseif type == "java.lang.Integer" || type == "int" || type == "java.lang.Long" || type == "long" || type == "java.lang.Short" || type == "short" || type == "java.lang.Byte" || type == "byte" || type == "java.lang.Double" || type == "double" || type == "java.lang.Float" || type == "float" || type == "java.math.BigDecimal" || type == "java.math.BigInteger">
        <#return "number">
    <#else>
        <#return "string">
    </#if>
</#function>
<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
import type BaseData from '@/types/BaseData'

/**
 * ${businessName}响应数据
 */
export default interface ${TableName}Data extends BaseData {
<#list columnMetas as column>

    <#if column.columnName != "version">
  /**
   * ${column.columnComment!column.columnName}
   */
  ${column.columnName}?: ${tsType(column.javaType!"java.lang.String")}
    </#if>
</#list>

}
