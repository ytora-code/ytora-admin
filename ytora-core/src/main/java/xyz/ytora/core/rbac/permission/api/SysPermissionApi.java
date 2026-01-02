package xyz.ytora.core.rbac.permission.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.permission.logic.SysPermissionLogic;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.req.SysPermissionReq;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.core.rbac.permission.repo.SysPermissionRepo;
import xyz.ytora.sql4j.sql.select.SelectBuilder;

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
    @GetMapping("/list")
    @Operation(summary = "查询资源", description = "查询资源")
    public List<SysPermissionResp> list(String permissionCode) {
        return logic.list(permissionCode);
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
    public R<String> insertOrUpdate(@RequestBody SysPermissionReq SysPermissionReq) {
        if (SysPermissionReq.getId() == null) {
            repository.insert(SysPermissionReq.toEntity());
            return R.success("新增成功");
        } else {
            repository.update(SysPermissionReq.toEntity(), w -> w.eq(SysPermission::getId, SysPermissionReq.getId()));
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


}
