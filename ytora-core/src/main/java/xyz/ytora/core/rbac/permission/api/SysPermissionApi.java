package xyz.ytora.core.rbac.permission.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.datarule.model.entity.SysDataRule;
import xyz.ytora.core.rbac.permission.logic.SysPermissionLogic;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.req.SysPermissionReq;
import xyz.ytora.core.rbac.permission.model.req.SysRolePermissionReq;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.core.rbac.permission.model.resp.SysRolePermissionResp;
import xyz.ytora.core.rbac.permission.repo.SysPermissionRepo;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.str.Strs;

import java.util.List;

/**
 * 资源 控制器
 */
@Slf4j
@Tag(name = "资源")
@RestController
@RequestMapping("/rbac/permission")
@RequiredArgsConstructor
public class SysPermissionApi extends BaseApi<SysPermission, SysPermissionLogic, SysPermissionRepo> {

    /**
     * 查询资源
     */
    @GetMapping("/tree")
    @Operation(summary = "查询资源", description = "查询资源")
    public List<SysPermissionResp> list(String permissionName) {
        return logic.tree(permissionName);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysPermissionResp queryById(@RequestParam String id) {
        SysPermission entity = repository.one(w -> w.eq(SysPermissionReq::getId, id));
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
    public R<String> insertOrUpdate(@RequestBody SysPermissionReq data) {
        if (data.getId() == null) {
            if (Strs.isEmpty(data.getPid())) {
                data.setPid("0");
            }
            repository.insert(data.toEntity());
            return R.success("新增成功");
        } else {
            data.setPid(null);
            repository.update(data.toEntity(), w -> w.eq(SysPermission::getId, data.getId()));
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

    /**
     * 获取指定角色的资源树
     */
    @GetMapping("/treePermissionByRoleId")
    @Operation(summary = "获取指定角色的资源树", description = "获取指定角色的资源树")
    public SysRolePermissionResp treePermissionByRoleId(@RequestParam String roleId) {
        return logic.treePermissionByRoleId(roleId);
    }

    /**
     * 更新角色-资源映射
     */
    @PostMapping("/refreshRolePermission")
    @Operation(summary = "更新角色-资源映射", description = "更新角色-资源映射")
    public R<String> refreshRolePermission(@RequestBody SysRolePermissionReq rolePermissionReq) {
        logic.refreshRolePermission(rolePermissionReq);
        return R.success("更新成功");
    }

    /**
     * 获取指定资源的数据规则
     */
    @GetMapping("/listDataRule")
    @Operation(summary = "获取指定资源的数据规则", description = "获取指定资源的数据规则")
    public List<SysDataRule> listDataRule(@RequestParam String permissionId) {
        return logic.listDataRule(permissionId);
    }

    /**
     * 新增或编辑指定资源的数据规则
     */
    @PostMapping("/addOrUpdateDataRule")
    @Operation(summary = "新增或编辑指定资源的数据规则", description = "新增或编辑指定资源的数据规则")
    public R<String> addOrUpdateDataRule(@RequestBody SysDataRule data) {
        logic.addOrUpdateDataRule(data);
        return R.success("操作成功");
    }

    /**
     * 删除指定数据规则
     */
    @DeleteMapping("/deleteDataRule")
    @Operation(summary = "删除指定数据规则", description = "删除指定数据规则")
    public R<String> deleteDataRule(@RequestParam String id) {
        logic.deleteDataRule(id);
        return R.success("删除成功");
    }

}
