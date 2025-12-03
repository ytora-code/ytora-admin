import {reactive, ref, toRaw} from "vue";
import type IPageResp from "@/type/IPageResp.ts";
import resetDefault from "@/util/reset-default.ts";
import type IPageReq from "@/type/IPageReq.ts";
import type I${TableName}Req from "../type/req/I${TableName}Req.ts";
import type I${TableName}Resp from "../type/resp/I${TableName}Resp.ts";
import ${tableName}Api from "../api/${TableName}Api.ts";

/**
 * ${TableComment}的CRUD相关代码
 */
export const use${TableName}Crud = () => {

    /**
     * 分页数据
     */
    const pageModel = reactive<IPageReq>({
        pageNo: 1,
        pageSize: 10
    })

    /**
     * search表单数据
     */
    const searchModel = reactive<I${TableName}Req>({

    })

    /**
     * 表格数据
     */
    const tableModel = reactive<IPageResp<I${TableName}Resp>>({
        pageNo: 1,
        pageSize: 10,
        pages: 0,
        total: 0,
        records: []
    })

    /**
     * 新增和编辑框数据
     */
    const dialogModel = reactive<I${TableName}Req>({
    })

    /**
     * 分页查询
     */
    let loading = ref<boolean>(false)

    const page = () => {
        loading.value = true
        ${tableName}Api.page({...toRaw(searchModel), ...toRaw(pageModel)})
            .then(result => {
                tableModel.pageNo = result.pageNo
                tableModel.pageSize = result.pageSize
                tableModel.pages = result.pages
                tableModel.total = result.total
                tableModel.records = result.records
            })
            .catch(() => {
                //查询出错，就清空数据
                tableModel.records = []
            })
            .finally(() => {
                loading.value = false
            })
    }

    const reset = () => {
        resetDefault(searchModel)
        page()
    }

    const pageChange = (pageNo: number, pageSize: number) => {
        pageModel.pageNo = pageNo
        pageModel.pageSize = pageSize
        page()
    }

    /**
    * 新增和编辑的dialog
    */
    let dialogVisible = ref(false)
    const openDialog = (row: any) => {
        if (row) {
            Object.assign(dialogModel, row)
        }
        dialogVisible.value = true
    }
                    
    const closeDialog = () => {
        resetDefault(dialogModel)
        dialogVisible.value = false
    }
                    
    const addOrUpdate = async () => {
        await ${tableName}Api.addOrUpdate(dialogModel)
        closeDialog()
        page()
    }

    /**
    * 删除
    */
    const deleteByIds = async (ids: string[]) => {
        await ${tableName}Api.deleteById(ids)
        page()
    }

    return {
        searchModel,
        tableModel,
        dialogModel,
        loading,
        dialogVisible,
        recycleBinDialogVisible,
        recycleBinTableModel,
        showImportDialog,
        page,
        reset,
        pageChange,
        openDialog,
        closeDialog,
        addOrUpdate,
        deleteByIds,
        openRecycleBinDialog,
        restore,
        deleteCompletely,
        closeRecycleBinDialog,
        downloadTemplate,
        uploadXlsx
    }
}