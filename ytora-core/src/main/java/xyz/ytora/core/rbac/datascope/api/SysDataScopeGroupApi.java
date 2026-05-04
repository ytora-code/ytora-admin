package xyz.ytora.core.rbac.datascope.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.rbac.datascope.logic.SysDataScopeGroupLogic;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeGroupData;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.excel.SysDataScopeGroupExcel;
import xyz.ytora.core.rbac.datascope.model.param.SysDataScopeGroupParam;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeGroupParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 数据范围组模块的API层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "数据范围组")
@RestController
@RequestMapping("/rbac/data-scope-group")
@RequiredArgsConstructor
public class SysDataScopeGroupApi extends BaseApi<SysDataScopeGroupLogic> {

    // ============================== CRUD =================================>

    /**
     * 分页查询数据
     */
    @Operation(summary = "分页查询数据范围组", description = "分页查询数据范围组")
    @GetMapping("/page")
    public Page<SysDataScopeGroupData> page(@ParameterObject SysDataScopeGroupParam param,
                                            @RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysDataScopeGroup> page = logic.page(param);
        return page.trans(SysDataScopeGroup::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysDataScopeGroupData> queryById(@RequestParam String id) {
        SysDataScopeGroup entity = logic.queryById(id);
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
    public String upsert(@RequestBody SysDataScopeGroupParam param) {
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
    public List<SysDataScopeGroupExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysDataScopeGroupExcel> data) {
        List<SysDataScopeGroup> list = data.stream().map(SysDataScopeGroupExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysDataScopeGroupExcel> exportToExcel(@ParameterObject SysDataScopeGroupParam param) {
        List<SysDataScopeGroup> list = logic.list(param);
        return list.stream()
                .map(SysDataScopeGroup::toData)
                .map(SysDataScopeGroupData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

    /**
     * 查询指定角色在指定资源下的分组
     */
    @Operation(summary = "查询指定角色在指定资源下的分组", description = "查询指定角色在指定资源下的分组")
    @GetMapping("/listGroup")
    public List<String> listGroup(@RequestParam String roleId, @RequestParam String permissionId) {
        return logic.listGroup(roleId, permissionId);
    }

    /**
     * 更新指定角色在指定资源下的分组
     */
    @Operation(summary = "更新指定角色在指定资源下的分组", description = "更新指定角色在指定资源下的分组")
    @PostMapping("/refreshRoleGroup")
    public String refreshRoleGroup(@RequestBody SysRoleDataScopeGroupParam param) {
        logic.refreshRoleGroup(param);
        return "更新成功";
    }

}
