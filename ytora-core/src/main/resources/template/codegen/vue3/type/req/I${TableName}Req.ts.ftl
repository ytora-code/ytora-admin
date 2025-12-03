import type IReqModel from "@/type/IReqModel"
import type I${TableName} from "../entity/I${TableName}.ts"

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}响应类型
 * <br/>
 */
export default interface I${TableName}Req extends IReqModel<I${TableName}> {
<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    ${field.columnName}?: ${field.tsType}

</#list>
}