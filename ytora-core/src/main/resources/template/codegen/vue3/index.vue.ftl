<script setup lang="ts">
import {onMounted, ref} from "vue"
import type IFormExpos from "@/components/input/type/expose.ts"
import useSchema from "./composable/useSchema.ts";
import {use${TableName}Crud} from "./composable/use${TableName}Crud.ts"

const dialogFormRef = ref<IFormExpos | null>(null)

const {searchSchema, tableColumns, dialogScheme} = useSchema()

const {
  dialogModel,
  dialogVisible,
  loading,
  searchModel,
  tableModel,
  openDialog,
  page,
  reset,
  pageChange,
  closeDialog,
  addOrUpdate,
  deleteByIds,
} = use${TableName}Crud()

const confirm = () => {
  dialogFormRef.value?.validate().then(isPass => {
    if (isPass) {
      addOrUpdate()
    } else {
      console.error("表单校验未通过")
    }
  })
}

onMounted(() => {
  page()
})
</script>

<template>
  <!-- 搜索框 -->
  <t-form :schema="searchSchema" v-model="searchModel">
    <template #operate>
      <div class="search-operate">
        <t-btn icon="search" type="primary" @click="page">搜 索</t-btn>
        <t-btn icon="reset" ghost @click="reset">重 置</t-btn>
      </div>
    </template>
  </t-form>

  <!-- 表格主体数据 -->
  <t-table :columns="tableColumns"
            :data="tableModel.records"
            :loading="loading">
    <template v-slot:header>
      <t-btn icon="plus" type="success" @click="openDialog(undefined)">新 增</t-btn>
      <t-btn icon="download" @click="uploadXlsx">导 出</t-btn>
      <t-btn icon="upload" @click="showImportDialog = true">导 入</t-btn>
      <t-btn icon="recycle-bin" ghost @click="openRecycleBinDialog">回收站</t-btn>
    </template>
    <template v-slot:operate="{row}">
      <t-btn type="success" text size="small" @click="openDialog(row)">编辑</t-btn>
      <t-btn type="danger" text size="small" @click="deleteByIds([row.id])">删除</t-btn>
    </template>
    <template v-slot:footer>
      <t-pagination :pageNo="tableModel.pageNo" :pageSize="tableModel.pageSize" :total="tableModel.total"
              @showSizeChange="pageChange" @change="pageChange"/>
    </template>
  </t-table>

  <!-- 新增或编辑的弹出框 -->
  <t-dialog :visible="dialogVisible" :title="dialogModel.id ? '编辑' : '新增'" @close="closeDialog"
        @confirm="confirm">
    <t-form ref="dialogFormRef" :schema="dialogScheme" v-model="dialogModel"/>
  </t-dialog>

  <!-- 回收站 -->

  <!-- 文件导入弹框 -->
    <t-dialog :visible="showImportDialog" title="文件上传" width="400px"
              @close="showImportDialog = false" @confirm="doUpload">
      <div class="flex flex-col items-center">
        <t-upload
          ref="xlsxFileUpload"
          :size="180"
          upload-url="${module}/${tableName}/import"
          with-credentials/>
        <t-btn text @click="downloadTemplate">下载模板</t-btn>
      </div>
    </t-dialog>
</template>

<style scoped>
.search-operate {
  display: flex;
}
</style>