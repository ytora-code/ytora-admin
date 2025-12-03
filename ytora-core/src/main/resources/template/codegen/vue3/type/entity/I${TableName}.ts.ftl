import type IEntityModel from "@/type/IEntityModel";

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}表实体类
 * <br/>
 */
export default interface I${TableName} extends IEntityModel<I${TableName}> {
<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    ${field.columnName}?: ${field.tsType};

</#list>
}