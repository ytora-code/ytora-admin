import type IRespModel from "@/type/IRespModel";
import type I${TableName} from "../entity/I${TableName}.ts"

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}请求参数
 * <br/>
 */
export default interface I${TableName}Resp extends IRespModel<I${TableName}> {
<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    ${field.columnName}?: ${field.tsType};

</#list>
}