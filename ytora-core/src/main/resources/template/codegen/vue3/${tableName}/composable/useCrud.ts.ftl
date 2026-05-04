<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
import { computed, ref } from 'vue'
import { createDiscreteApi } from 'naive-ui'

import type ${TableName}Data from '../type/${TableName}Data'
import type ${TableName}Param from '../type/${TableName}Param'
import ${tableName}Api from '../api/${TableName}Api'

interface DrawerFormInstance {
  validate: () => Promise<void>
}

/**
 * 初始化搜索框的表单model
 */
const createInitialSearchFormModel = (): ${TableName}Param => ({})

/**
 * 初始化抽屉的表单model
 */
const createInitialDrawerFormModel = (): ${TableName}Param => ({
<#list columnMetas as column>
<#if column.columnName != "version">
  <#assign fieldName = column.columnName>
  <#assign javaType = column.javaType!"java.lang.String">
  <#if javaType == "java.lang.Boolean" || javaType == "boolean">
  ${fieldName}: false,
  <#elseif javaType == "java.sql.Date" || javaType == "java.sql.Time" || javaType == "java.sql.Timestamp" || javaType == "java.util.Date" || javaType == "java.time.LocalDate" || javaType == "java.time.LocalTime" || javaType == "java.time.LocalDateTime">
  ${fieldName}: undefined,
  <#elseif javaType == "java.lang.Integer" || javaType == "int" || javaType == "java.lang.Long" || javaType == "long" || javaType == "java.lang.Short" || javaType == "short" || javaType == "java.lang.Byte" || javaType == "byte" || javaType == "java.lang.Double" || javaType == "double" || javaType == "java.lang.Float" || javaType == "float" || javaType == "java.math.BigDecimal" || javaType == "java.math.BigInteger">
  ${fieldName}: <#if fieldName == "status">1<#else>undefined</#if>,
  <#else>
  ${fieldName}: '',
  </#if>
</#if>
</#list>
})

/**
 * 增删改查
 */
const useCrud = () => {
  const { message, dialog } = createDiscreteApi(['message', 'dialog'])

  const searchFormModel = ref<${TableName}Param>(createInitialSearchFormModel())
  const pageNo = ref<number>(1)
  const pageSize = ref<number>(10)
  const pages = ref<number>()
  const total = ref<number>()
  const records = ref<${TableName}Data[]>([])
  const tableLoading = ref<boolean>(false)
  const checkedRowKeys = ref<Array<string | number>>([])

  const page = async () => {
    tableLoading.value = true
    try {
      const result = await ${tableName}Api.page({
        ...searchFormModel.value,
        pageNo: pageNo.value,
        pageSize: pageSize.value,
      })
      pageNo.value = result.pageNo
      pageSize.value = result.pageSize
      pages.value = result.pages
      total.value = result.total
      records.value = result.records
    } catch (error) {
      console.error(error)
    } finally {
      tableLoading.value = false
    }
  }

  const search = async () => {
    pageNo.value = 1
    await page()
  }

  const resetSearch = async () => {
    searchFormModel.value = createInitialSearchFormModel()
    pageNo.value = 1
    await page()
  }

  const pageChange = async (newPageNo: number) => {
    pageNo.value = newPageNo
    await page()
  }

  const pageSizeChange = async (newPageSize: number) => {
    pageNo.value = 1
    pageSize.value = newPageSize
    await page()
  }

  const drawerVisible = ref<boolean>(false)
  const drawerLoading = ref<boolean>(false)
  const submitLoading = ref<boolean>(false)
  const drawerMode = ref<'create' | 'edit'>('create')
  const drawerFormRef = ref<DrawerFormInstance | null>(null)
  const drawerFormModel = ref<${TableName}Param>(createInitialDrawerFormModel())
  const drawerTitle = computed(() =>
    drawerMode.value === 'create' ? '新增${businessName}' : '编辑${businessName}',
  )

  const openCreateDrawer = () => {
    drawerMode.value = 'create'
    drawerFormModel.value = createInitialDrawerFormModel()
    drawerVisible.value = true
  }

  const openEditDrawer = async (id?: string | number) => {
    if (!id) {
      message.error('缺少ID，无法编辑')
      return
    }

    drawerMode.value = 'edit'
    drawerVisible.value = true
    drawerLoading.value = true
    drawerFormModel.value = createInitialDrawerFormModel()

    try {
      const result = await ${tableName}Api.queryById(id)
      drawerFormModel.value = {
        ...createInitialDrawerFormModel(),
        ...result,
      } as ${TableName}Param
    } catch (error) {
      console.error(error)
      drawerVisible.value = false
    } finally {
      drawerLoading.value = false
    }
  }

  const closeDrawer = () => {
    drawerVisible.value = false
  }

  const handleDrawerAfterLeave = () => {
    drawerLoading.value = false
    submitLoading.value = false
    drawerMode.value = 'create'
    drawerFormModel.value = createInitialDrawerFormModel()
  }

  const submitDrawer = async () => {
    await drawerFormRef.value?.validate()
    submitLoading.value = true

    try {
      await ${tableName}Api.upsert({
        ...drawerFormModel.value,
      } as ${TableName}Param)
      drawerVisible.value = false
      await page()
    } catch (error) {
      console.error(error)
    } finally {
      submitLoading.value = false
    }
  }

  const deleteLoading = ref<boolean>(false)

  const deleteByIds = async (ids: Array<string | number>, adjustPage = false) => {
    deleteLoading.value = true

    try {
      await ${tableName}Api.deleteByIds(ids)

      if (adjustPage && records.value.length === ids.length && pageNo.value > 1) {
        pageNo.value -= 1
      }

      const deletingKeys = new Set(ids.map((item) => String(item)))
      checkedRowKeys.value = checkedRowKeys.value.filter((key) => !deletingKeys.has(String(key)))
      await page()
    } catch (error) {
      console.error(error)
    } finally {
      deleteLoading.value = false
    }
  }

  const confirmDelete = (ids: Array<string | number>, adjustPage = false) => {
    if (!ids.length) {
      message.warning('请先选择要删除的数据')
      return
    }

    dialog.warning({
      title: '删除确认',
      content: '删除后不可恢复，确定继续吗？',
      positiveText: '确定',
      negativeText: '取消',
      onPositiveClick: async () => {
        await deleteByIds(ids, adjustPage)
      },
    })
  }

  const deleteRow = (row: ${TableName}Data) => {
    if (row?.id) {
      void deleteByIds([row.id], true)
    }
  }

  const deleteBatch = () => {
    confirmDelete(checkedRowKeys.value)
  }

  return {
    ...{
      searchFormModel,
      pageNo,
      pageSize,
      pages,
      total,
      records,
      tableLoading,
      checkedRowKeys,
      page,
      search,
      resetSearch,
      pageChange,
      pageSizeChange,
    },
    ...{
      drawerVisible,
      drawerLoading,
      submitLoading,
      drawerMode,
      drawerTitle,
      drawerFormRef,
      drawerFormModel,
      openCreateDrawer,
      openEditDrawer,
      closeDrawer,
      handleDrawerAfterLeave,
      submitDrawer,
    },
    ...{
      deleteLoading,
      deleteRow,
      deleteBatch,
    },
  }
}

export type ${TableName}UpsertModel = ${TableName}Param
export default useCrud
