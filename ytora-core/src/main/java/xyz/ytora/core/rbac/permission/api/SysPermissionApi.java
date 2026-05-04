package xyz.ytora.core.rbac.permission.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.core.rbac.permission.logic.SysPermissionLogic;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.data.SysRolePermissionDetail;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.param.SysPermissionParam;
import xyz.ytora.core.rbac.permission.model.param.SysRolePermissionParam;

import java.util.List;

/**
 * 资源模块的API层
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "资源")
@RestController
@RequestMapping("/rbac/permission")
@RequiredArgsConstructor
public class SysPermissionApi extends BaseApi<SysPermissionLogic> {

    /**
     * 获取整颗资源树
     */
    @GetMapping("/tree")
    @Operation(summary = "获取整颗资源树", description = "获取整颗资源树")
    public List<SysPermissionData> tree(String permissionName) {
        return logic.tree(permissionName);
    }

    /**
     * 根据PID查询资源
     */
    @GetMapping("/listByPid")
    @Operation(summary = "根据PID查询资源", description = "根据PID查询资源，顶级资源PID为0")
    public List<SysPermissionData> listByPid(@RequestParam String pid) {
        return logic.listByPid(pid);
    }

    /**
     * 根据ID查询资源
     */
    @Operation(summary = "根据ID查询资源", description = "根据ID查询资源")
    @GetMapping("/queryById")
    public R<SysPermissionData> queryById(@RequestParam String id) {
        SysPermission permission = logic.queryById(id);
        if (permission == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(permission.toData());
    }

    /**
     * 新增或编辑
     */
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysPermissionParam param) {
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

    // ============================== 其他 =================================>

    /**
     * 获取指定角色的资源树
     */
    @GetMapping("/treePermissionByRoleId")
    @Operation(summary = "获取指定角色的资源树", description = "获取指定角色的资源树")
    public SysRolePermissionDetail treePermissionByRoleId(@RequestParam String roleId) {
        return logic.treePermissionByRoleId(roleId);
    }

    /**
     * 更新角色-资源映射
     */
    @PostMapping("/refreshRolePermission")
    @Operation(summary = "更新角色-资源映射", description = "更新角色-资源映射")
    public R<String> refreshRolePermission(@RequestBody SysRolePermissionParam rolePermissionReq) {
        logic.refreshRolePermission(rolePermissionReq);
        return R.success("更新成功");
    }

}
