<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
<#function isStringLike javaType>
    <#return !(javaType == "java.lang.Boolean" || javaType == "boolean" || javaType == "java.lang.Integer" || javaType == "int" || javaType == "java.lang.Long" || javaType == "long" || javaType == "java.lang.Short" || javaType == "short" || javaType == "java.lang.Byte" || javaType == "byte" || javaType == "java.lang.Double" || javaType == "double" || javaType == "java.lang.Float" || javaType == "float" || javaType == "java.math.BigDecimal" || javaType == "java.math.BigInteger")>
</#function>
<#function isDateColumn column>
    <#assign javaType = column.javaType!"">
    <#assign name = column.columnName!"">
    <#return javaType == "java.sql.Date" || javaType == "java.sql.Time" || javaType == "java.sql.Timestamp" || javaType == "java.time.LocalDate" || javaType == "java.time.LocalTime" || javaType == "java.time.LocalDateTime" || name?contains("date") || name?contains("time")>
</#function>
<#function isImageColumn column>
    <#assign name = column.columnName!"">
    <#return name?contains("avatar") || name?contains("image") || name?contains("img") || name?contains("icon") || name?contains("cover")>
</#function>
<#function isTextareaColumn column>
    <#assign name = column.columnName!"">
    <#return name?contains("remark") || name?contains("content") || name?contains("description") || name?contains("desc") || name?contains("reason")>
</#function>
<#function isStatusColumn column>
    <#assign name = column.columnName!"">
    <#return name == "status">
</#function>
<#assign searchColumns = []>
<#list columnMetas as column>
    <#assign name = column.columnName!"">
    <#if name != "version" && !name?contains("password") && !isTextareaColumn(column) && searchColumns?size lt 4>
        <#assign searchColumns = searchColumns + [column]>
    </#if>
</#list>
<#assign tableColumns = []>
<#list columnMetas as column>
    <#assign name = column.columnName!"">
    <#if name != "version" && !name?contains("password")>
        <#assign tableColumns = tableColumns + [column]>
    </#if>
</#list>
import type { FormRules } from 'naive-ui'

import DynamicFormSchema from '@/components/form/type/DynamicFormSchema'
import type DynamicTableSchema from '@/components/table/type/DynamicTableSchema'
import type ${TableName}Data from '../type/${TableName}Data'
import type ${TableName}Param from '../type/${TableName}Param'

/**
 * 表单和表格的结构
 */
const useSchema = () => {

  /**
   * 条件搜索框的结构
   */
  const searchFormSchemas: DynamicFormSchema<${TableName}Param>[] = [
<#list searchColumns as column>
    {
      span: 5,
      label: '${column.columnComment!column.columnName}',
      type: '<#if isStatusColumn(column)>select<#else>input</#if>',
      dataKey: '${column.columnName}',
      prop: {
<#if isStatusColumn(column)>
        options: [
          { label: '正常', value: 1 },
          { label: '禁用', value: 2 },
        ],
<#else>
        clearable: true,
        <#if isStringLike(column.javaType!"java.lang.String")>maxlength: 100,</#if>
</#if>
      },
    }<#if column_has_next>,</#if>
</#list>
  ]

  /**
   * 新增/编辑表单的结构
   */
  const drawerFormSchemas: DynamicFormSchema<${TableName}Param>[] = [
<#list columnMetas as column>
    <#assign name = column.columnName!"">
    <#if name != "version">
    {
      label: '${column.columnComment!column.columnName}',
      type: '<#if isStatusColumn(column)>select<#elseif isTextareaColumn(column)>textarea<#else>input</#if>',
      dataKey: '${name}',
      prop: {
<#if isStatusColumn(column)>
        options: [
          { label: '正常', value: 1 },
          { label: '禁用', value: 2 },
        ],
<#elseif isTextareaColumn(column)>
        autosize: {
          minRows: 3,
          maxRows: 5,
        },
<#else>
        clearable: true,
        <#if isStringLike(column.javaType!"java.lang.String")>maxlength: 100,</#if>
</#if>
      },
    }<#if column_has_next>,</#if>
    </#if>
</#list>
  ]

  /**
   * 新增/编辑表单的校验规则
   */
  const drawerRules: FormRules = {}

  /**
   * 数据表格的结构
   */
  const tableSchemas: DynamicTableSchema<${TableName}Data>[] = [
    { type: 'selection', key: 'selection', width: 48, fixed: 'left' },
<#list tableColumns as column>
    {
      title: '${column.columnComment!column.columnName}',
      key: '${column.columnName}',
      dataKey: '${column.columnName}',
<#if isImageColumn(column)>
      type: 'image',
      width: 88,
      imageWidth: 40,
      imageHeight: 40,
<#elseif isDateColumn(column)>
      type: 'date',
      width: 140,
<#elseif isTextareaColumn(column)>
      width: 220,
<#else>
      width: 160,
</#if>
    },
</#list>
    { title: '操作', key: 'action', type: 'slot', width: 220, fixed: 'right' },
  ]

  return {
    searchFormSchemas,
    drawerFormSchemas,
    drawerRules,
    tableSchemas,
  }
}

export default useSchema
