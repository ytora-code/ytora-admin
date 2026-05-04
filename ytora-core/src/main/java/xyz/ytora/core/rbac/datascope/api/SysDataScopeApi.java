package xyz.ytora.core.rbac.datascope.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.rbac.datascope.logic.SysDataScopeLogic;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeData;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.core.rbac.datascope.model.excel.SysDataScopeExcel;
import xyz.ytora.core.rbac.datascope.model.param.SysDataScopeParam;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeGroupParam;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 数据范围模块的API层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "数据范围")
@RestController
@RequestMapping("/rbac/data-scope")
@RequiredArgsConstructor
public class SysDataScopeApi extends BaseApi<SysDataScopeLogic> {

    // ============================== CRUD =================================>

    /**
     * 查询指定分组下的数据范围
     */
    @Operation(summary = "查询指定分组下的数据范围", description = "查询指定分组下的数据范围")
    @GetMapping("/listByGroupId")
    public List<SysDataScopeData> listByGroupId(String groupId) {
        return logic.listByGroupId(groupId);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysDataScopeData> queryById(@RequestParam String id) {
        SysDataScope entity = logic.queryById(id);
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
    public String upsert(@RequestBody SysDataScopeParam param) {
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
    public List<SysDataScopeExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysDataScopeExcel> data) {
        List<SysDataScope> list = data.stream().map(SysDataScopeExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysDataScopeExcel> exportToExcel(@ParameterObject SysDataScopeParam param) {
        List<SysDataScope> list = logic.list(param);
        return list.stream()
                .map(SysDataScope::toData)
                .map(SysDataScopeData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>
    /**
     * 查询指定角色在指定分组下面的数据范围
     */
    @Operation(summary = "查询指定角色在指定分组下面的数据范围", description = "查询指定角色在指定分组下面的数据范围")
    @GetMapping("/listDataScopeByGroupId")
    public List<String> listDataScopeByGroupId(@RequestParam String roleId, @RequestParam String groupId) {
        return logic.listDataScopeByGroupId(Collections.singletonList(roleId), groupId);
    }

    /**
     * 更新指定角色在指定分组下的数据范围
     */
    @Operation(summary = "更新指定角色在指定分组下的数据范围", description = "更新指定角色在指定分组下的数据范围")
    @PostMapping("/refreshRoleGroupDataScope")
    public String refreshRoleGroupDataScope(@RequestBody SysRoleDataScopeParam param) {
        logic.refreshRoleGroupDataScope(param);
        return "更新成功";
    }

}
