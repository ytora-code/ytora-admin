import BaseApi from '@/api/BaseApi'
import type PageData from '@/types/PageData'
import type PageParam from '@/types/PageParam'
import type ${TableName}Data from '../type/${TableName}Data'
import type ${TableName}Param from '../type/${TableName}Param'

class ${TableName}Api extends BaseApi {

  constructor() {
    super('/${table_name?replace("_", "/")}')
  }

  // ============================== CRUD =================================>

  /**
   * 分页请求
   */
  page = (params: ${TableName}Param & PageParam) => {
    return this.get<PageData<${TableName}Data>, ${TableName}Param & PageParam>('page', params)
  }

  /**
   * 根据ID查询
   */
  queryById = (id: string | number) => {
    return this.get<${TableName}Data, { id: string | number }>('queryById', { id })
  }

  /**
   * 新增或编辑
   */
  upsert = (data: ${TableName}Param) => {
    return this.post<unknown, ${TableName}Param>('upsert', data)
  }

  /**
   * 删除数据
   */
  deleteByIds = (ids: Array<string | number>) => {
    if (!ids || ids.length === 0) {
      return Promise.reject('待删除数据的id数组不能为空')
    }
    return this.delete<unknown, { ids: string }>('deleteByIds', { ids: ids.join(',') })
  }

  // ============================== EXCEL导入导出 =================================>

  /**
   * 下载导入模板
   */
  downloadTemplate = () => {
    return this.download<unknown>('downloadTemplate')
  }

  /**
   * 导入数据
   */
  import = (
    formData: FormData,
    progress?: (loaded: number, total: number, percent: number) => void,
  ) => {
    return this.upload<string>('import', formData, progress)
  }

  /**
   * 导出数据
   */
  export = (params: ${TableName}Param) => {
    return this.download<${TableName}Param>('export', params)
  }

  // ============================== 其他 =================================>

}

export default new ${TableName}Api()
