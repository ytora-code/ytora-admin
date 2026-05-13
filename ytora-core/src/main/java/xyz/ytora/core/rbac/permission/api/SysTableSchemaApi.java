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
import xyz.ytora.core.rbac.permission.logic.SysRoleTableSchemaLogic;
import xyz.ytora.core.rbac.permission.logic.SysTableSchemaLogic;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.data.SysTableSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysTableSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysTableSchemaExcel;
import xyz.ytora.core.rbac.permission.model.param.SysRoleTableSchemaParam;
import xyz.ytora.core.rbac.permission.model.param.SysTableSchemaParam;
import xyz.ytora.sqlux.orm.Page;
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
@RequestMapping("/sys/table-schema")
@RequiredArgsConstructor
public class SysTableSchemaApi extends BaseApi<SysTableSchemaLogic> {

    private final SysRoleTableSchemaLogic roleTableSchemaLogic;

    // ============================== CRUD =================================>

    /**
     * 分页查询指定资源下的表格
     */
    @Operation(summary = "分页查询指定资源下的表格", description = "分页查询指定资源下的表格")
    @GetMapping("/pageTables")
    public Page<SysPermissionData> pageTables(@RequestParam String permissionId,
                                              @RequestParam(defaultValue = "1") Integer pageNo,
                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.pageTables(permissionId, pageNo, pageSize);
    }

    /**
     * 列表查询数据表格列Schema数据
     */
    @Operation(summary = "列表查询数据表格列Schema数据", description = "列表查询数据表格列Schema数据")
    @GetMapping("/listSchemas")
    public List<SysTableSchemaData> listSchemas(@ParameterObject SysTableSchemaParam param) {
        List<SysTableSchema> list = logic.list(param);
        return list.stream().map(SysTableSchema::toData).toList();
    }

    /**
     * 根据表格code查询数据表格列Schema数据
     */
    @Operation(summary = "根据表格code查询数据表格列Schema数据", description = "根据表格code查询数据表格列Schema数据")
    @GetMapping("/listSchemasByTableCode")
    public List<SysTableSchemaData> listSchemasByTableCode(@RequestParam String tableCode) {
        return logic.listSchemasByTableCode(tableCode);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysTableSchemaData> queryById(@RequestParam String id) {
        SysTableSchema entity = logic.queryById(id);
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
    public String upsert(@RequestBody SysTableSchemaParam param) {
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
    public List<SysTableSchemaExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysTableSchemaExcel> data) {
        List<SysTableSchema> list = data.stream().map(SysTableSchemaExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysTableSchemaExcel> exportToExcel(@ParameterObject SysTableSchemaParam param) {
        List<SysTableSchema> list = logic.list(param);
        return list.stream()
                .map(SysTableSchema::toData)
                .map(SysTableSchemaData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

    /**
     * 查询指定角色在指定资源下拥有的表格
     */
    @Operation(summary = "查询指定角色在指定资源下拥有的表格", description = "查询指定角色在指定资源下拥有的表格")
    @GetMapping("/listTablesForRole")
    public List<String> listTablesForRole(@RequestParam String roleId, @RequestParam String permissionId) {
        return logic.listTablesForRole(roleId, permissionId);
    }

    /**
     * 更新指定角色在指定资源下拥有的表格
     */
    @Operation(summary = "更新指定角色在指定资源下的分组", description = "更新指定角色在指定资源下的分组")
    @PostMapping("/refreshTablesForRole")
    public String refreshTablesForRole(@RequestBody SysRoleTableSchemaParam param) {
        logic.refreshTablesForRole(param);
        return "更新成功";
    }

    /**
     * 查询指定角色在指定表格下拥有的表格列字段
     */
    @Operation(summary = "查询指定角色在指定表格下拥有的表格列字段", description = "查询指定角色在指定表格下拥有的表格列字段")
    @GetMapping("/listSchemasForRole")
    public List<String> listSchemasForRole(@RequestParam String roleId, @RequestParam String tableId) {
        return logic.listSchemasForRole(roleId, tableId);
    }

    /**
     * 更新指定角色在指定表格下拥有的表格列字段
     */
    @Operation(summary = "更新指定角色在指定表格下拥有的表格列字段", description = "更新指定角色在指定表格下拥有的表格列字段")
    @PostMapping("/refreshSchemasForRole")
    public String refreshSchemasForRole(@RequestBody SysRoleTableSchemaParam param) {
        logic.refreshSchemasForRole(param);
        return "更新成功";
    }

}
