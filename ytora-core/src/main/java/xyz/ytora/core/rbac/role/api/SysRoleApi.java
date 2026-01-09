package xyz.ytora.core.rbac.role.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.download.DownloadMapper;
import xyz.ytora.base.download.Mimes;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.role.logic.SysRoleLogic;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.excel.SysRoleExcel;
import xyz.ytora.core.rbac.role.model.req.SysRoleReq;
import xyz.ytora.core.rbac.role.model.req.SysUserRoleReq;
import xyz.ytora.core.rbac.role.model.resp.SysRoleResp;
import xyz.ytora.core.rbac.role.model.resp.SysUserRoleResp;
import xyz.ytora.core.rbac.role.repo.SysRoleRepo;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 角色 控制器
 */
@Tag(name = "角色")
@RestController
@RequestMapping("/rbac/role")
@RequiredArgsConstructor
public class SysRoleApi extends BaseApi<SysRole, SysRoleLogic, SysRoleRepo> {

    // ============================== CRUD =================================>

    /**
     * 分页查询角色
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询角色", description = "分页查询角色")
    public Page<SysRoleResp> page(@ParameterObject SysRoleReq userdata,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        SelectBuilder selectBuilder = query();
        Page<SysRole> page = repository.page(pageNo, pageSize, selectBuilder);
        return Pages.transPage(page, SysRole::toResp);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysRoleResp queryById(@RequestParam String id) {
        SysRole entity = repository.one(w -> w.eq(SysRoleReq::getId, id));
        if (entity == null) {
            return null;
        }
        return entity.toResp();
    }

    /**
     * 新增或编辑
     */
    @PostMapping("/insertOrUpdate")
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    public R<String> insertOrUpdate(@RequestBody SysRoleReq SysRoleReq) {
        if (SysRoleReq.getId() == null) {
            repository.insert(SysRoleReq.toEntity());
            return R.success("新增成功");
        } else {
            repository.update(SysRoleReq.toEntity(), w -> w.eq(SysRole::getId, SysRoleReq.getId()));
            return R.success("编辑成功");
        }
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> delete(String id) {
        SelectBuilder selectBuilder = query();
        repository.delete(selectBuilder.getWhereStage().getWhere());
        return R.success("删除成功");
    }

    // ============================== EXCEL =================================>

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @DownloadMapper(value = "downloadTemplate", filename = "导入模板.xlsx", type = SysRoleExcel.class, mime = Mimes.APPLICATION_XLSX, showExpertInfo = false)
    public List<SysRoleExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @PostMapping("import")
    @Operation(summary = "导入", description = "导入")
    @Transactional(rollbackFor = Exception.class)
    public R<String> importFromExcel(@Excel(fileName = "file") List<SysRoleExcel> data) {
        List<SysRole> list = data.stream().map(SysRoleExcel::toEntity).toList();
        repository.insert(list);
        return R.success("导入成功");
    }

    @Operation(summary = "导出", description = "导出")
    @DownloadMapper(value = "export", filename = "用户信息.xlsx", type = SysRoleExcel.class, mime = Mimes.APPLICATION_XLSX)
    public List<SysRoleExcel> importFromExcel(@ParameterObject SysRoleReq param) {
        Page<SysRoleResp> page = page(param, 1, Integer.MAX_VALUE);
        return page.getRecords().stream().map(SysRoleResp::toExcel).toList();
    }

    // ============================== 其他 =================================>

    /**
     * 获取用户-角色关系
     */
    @GetMapping("/listUserRoleMapper")
    @Operation(summary = "获取用户-角色关系", description = "获取用户-角色关系")
    public Page<SysUserRoleResp> listUserRoleMapper(@RequestParam String userId, String roleName, String roleCode,
                                                    @RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.listUserRoleMapper(userId, roleName, roleCode, pageNo, pageSize);
    }

    /**
     * 更新用户-角色关系
     */
    @PostMapping("/refreshUserRoleMapper")
    @Operation(summary = "更新用户-角色关系", description = "更新用户-角色关系")
    public R<String> refreshUserRoleMapper(@RequestBody SysUserRoleReq userRoleReq) {
        logic.refreshUserRoleMapper(userRoleReq);
        return R.success(userRoleReq.getAdd() ? "更新成功" : "移除成功");
    }

}
