package xyz.ytora.core.rbac.role.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.rbac.role.logic.SysRoleLogic;
import xyz.ytora.core.rbac.role.model.data.SysRoleData;
import xyz.ytora.core.rbac.role.model.data.SysUserRoleData;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.excel.SysRoleExcel;
import xyz.ytora.core.rbac.role.model.param.SysRoleParam;
import xyz.ytora.core.rbac.role.model.param.SysUserRoleMapperParam;
import xyz.ytora.core.rbac.role.model.param.SysUserRoleRefreshParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 角色模块的API层
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "角色")
@RestController
@RequestMapping("/rbac/role")
@RequiredArgsConstructor
public class SysRoleApi extends BaseApi<SysRoleLogic> {

    // ============================== CRUD =================================>

    /**
     * 分页查询用户
     */
    @Operation(summary = "分页查询用户", description = "分页查询用户")
    @GetMapping("/page")
    public Page<SysRoleData> page(@ParameterObject SysRoleParam param,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysRole> page = logic.page(param);
        return page.trans(SysRole::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysRoleData> queryById(@RequestParam String id) {
        SysRole role = logic.queryById(id);
        if (role == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(role.toData());
    }

    /**
     * 新增或编辑
     */
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysRoleParam param) {
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

    // ============================== EXCEL =================================>

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysRoleExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysRoleExcel> data) {
        List<SysRole> list = data.stream().map(SysRoleExcel::toEntity).toList();
        // 批量导入数据
        logic.upsertBatch(list);
        return "导入成功";
    }

    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysRoleExcel> exportToExcel(@ParameterObject SysRoleParam param) {
        List<SysRole> list = logic.list(param);
        return list.stream()
                .map(SysRole::toData)
                .map(SysRoleData::toExcel)
                .toList();
    }

    // ============================== 用户-角色关系 =================================>

    /**
     * 获取用户-角色关系
     */
    @GetMapping("/listUserRoleMapper")
    @Operation(summary = "获取用户-角色关系", description = "获取用户-角色关系")
    public Page<SysUserRoleData> listUserRoleMapper(@ParameterObject SysUserRoleMapperParam param,
                                                    @RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.listUserRoleMapper(param, pageNo, pageSize);
    }

    /**
     * 更新用户-角色关系
     */
    @PostMapping("/refreshUserRoleMapper")
    @Operation(summary = "更新用户-角色关系", description = "更新用户-角色关系")
    public R<String> refreshUserRoleMapper(@RequestBody SysUserRoleRefreshParam param) {
        logic.refreshUserRoleMapper(param);
        return R.success(param.getAdd() ? "更新成功" : "移除成功");
    }

}
