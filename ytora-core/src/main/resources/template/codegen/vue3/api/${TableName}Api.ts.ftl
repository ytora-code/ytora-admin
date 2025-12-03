import BaseApi from "@/api/BaseApi.ts";
import type IPageResp from "@/type/IPageResp.ts";
import type I${TableName}Req from "../type/req/I${TableName}Req.ts";
import type I${TableName}Resp from "../type/resp/I${TableName}Resp.ts";

class ${TableName}Api extends BaseApi {

    constructor() {
        super("/${module}/${tableName}")
    }

    /**
     * 分页查询
     */
    page = (${tableName}Req: I${TableName}Req) => {
        return this.get<IPageResp<I${TableName}Resp>, I${TableName}Req>('page', ${tableName}Req)
    }

    /**
     * 根据id查询数据
     */
    queryById = (id: String) => {
        return this.get('queryById', {id})
    }

    /**
     * 根据有无id新增或编辑数据
     */
    addOrUpdate = (${tableName}Req: I${TableName}Req) => {
        return this.post<string, I${TableName}Req>('addOrUpdate', ${tableName}Req)
    }

    /**
     * 根据id删除数据
     */
    deleteById = (ids: string[]) => {
        return this.delete<string>('delete', ids)
    }

    /**
    * 文件上传下载
    */
    downloadTemplate = () => {
        return this.download('downloadTemplate')
    }

    uploadXlsx = (userReq: ISysUserReq) => {
        return this.download('export', userReq)
    }
}

export default new ${TableName}Api()