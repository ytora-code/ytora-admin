package xyz.ytora.core.rbac.permission.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.rbac.permission.logic.SysFormSchemaLogic;
import xyz.ytora.core.rbac.permission.model.data.SysFormSchemaData;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.entity.SysFormSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysFormSchemaExcel;
import xyz.ytora.core.rbac.permission.model.param.SysFormSchemaParam;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 表格列结构模块的API层
 *
 * @author 杨桐
 * @since 1.0
 */
@Tag(name = "表格列结构")
@RestController
@RequestMapping("/sys/form-schema")
@RequiredArgsConstructor
public class SysFormSchemaApi extends BaseApi<SysFormSchemaLogic> {

    // ============================== CRUD =================================>

    /**
     * 列表查询指定资源下的表单
     */
    @Operation(summary = "列表查询指定资源下的表单", description = "列表查询指定资源下的表单")
    @GetMapping("/listForms")
    public List<SysPermissionData> listForms(@RequestParam String permissionId) {
        return logic.listForms(permissionId);
    }

    /**
     * 列表查询表单的表单项Schema数据
     */
    @Operation(summary = "列表查询表单的表单项Schema数据", description = "列表查询表单的表单项Schema数据")
    @GetMapping("/listSchemas")
    public List<SysFormSchemaData> listSchemas(@ParameterObject SysFormSchemaParam param) {
        List<SysFormSchema> list = logic.list(param);
        return list.stream().map(SysFormSchema::toData).toList();
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysFormSchemaData> queryById(@RequestParam String id) {
        SysFormSchema entity = logic.queryById(id);
        if (entity == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(entity.toData());
    }

    /**
     * 新增或编辑
     */
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysFormSchemaParam param) {
        return logic.upsert(param.toEntity());
    }

    /**
     * 根据ID删除
     */
    @Operation(summary = "根据ID删除", description = "根据ID删除")
    @DeleteMapping("/deleteByIds")
    public String deleteByIds(@RequestParam String ids) {
        int affectRows = logic.deleteByIds(ids);
        return affectRows > 0 ? "本次成功删除" + affectRows + "条数据" : "本次未删除任何数据";
    }

    // ============================== EXCEL导入导出 =================================>

    /**
     * 下载导入模板
     */
    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysFormSchemaExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysFormSchemaExcel> data) {
        List<SysFormSchema> list = data.stream().map(SysFormSchemaExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysFormSchemaExcel> exportToExcel(@ParameterObject SysFormSchemaParam param) {
        List<SysFormSchema> list = logic.list(param);
        return list.stream()
                .map(SysFormSchema::toData)
                .map(SysFormSchemaData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

}
