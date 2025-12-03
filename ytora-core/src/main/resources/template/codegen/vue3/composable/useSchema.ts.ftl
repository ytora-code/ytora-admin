import type FormItem from "@/components/form/type/FormItem";
import type TableColumn from "@/components/table/type/TableColumn.ts";

<#assign fieldLength = columnMetas?size>
const useSchema = () => {
    /**
    * 查询条件表单结构数据
    */
    const searchSchema: FormItem[] = [
    <#-- 每6个字段为一行row，row从0开始 -->
    <#list columnMetas as field>
        {row: ${(field_index / 6)?floor}, span: 4, label: "${field.columnComment}", key: "${field.columnName}", type: "input", attr:{clearable: true}},
    </#list>

    <#-- 计算操作列的行位置：如果最后一行还有位置（不满6个）就用当前行，否则用下一行 -->
    <#assign lastRowIndex = ((fieldLength - 1) / 6)?floor>
    <#assign lastRowCount = fieldLength % 6>
    <#assign operateRow = (lastRowCount == 0)?then(lastRowIndex + 1, lastRowIndex)>
        {row: ${operateRow}, span: 4, label: "操作", key: "operate", type: "slot"},
    ]

    /**
    * 表格结构数据
    */
    const tableColumns: TableColumn[] = [
        {key: "id", title: "序号", width: 80, align: "center", type: "index"},
    <#list columnMetas as field>
        {key: "${field.columnName}", title: "${field.columnComment}", align: "center", type: "string"},
    </#list>

        {key: "operate", title: "操作", width: 150, type: "slot", align: "center"},
    ]

    /**
    * dialog结构数据
    */
    const dialogScheme: FormItem[] = [
    <#-- 每2个字段为一行row，row从0开始 -->
    <#list columnMetas as field>
        {row: ${(field_index / 2)?floor}, span: 12, label: "${field.columnComment}", key: "${field.columnName}", placeholder: "${field.columnComment}", type: "input"},
    </#list>
    ]

    return {searchSchema, tableColumns, dialogScheme}
}

export default useSchema