package xyz.ytora.core.rbac.role.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.role.logic.SysRoleLogic;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.req.SysRoleReq;
import xyz.ytora.core.rbac.role.model.resp.SysRoleResp;
import xyz.ytora.core.rbac.role.repo.SysRoleRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.SelectBuilder;

/**
 * 角色 控制器
 */
@Tag(name = "角色")
@RestController
@RequestMapping("/rbac/role")
@RequiredArgsConstructor
public class SysRoleApi extends BaseApi<SysRole, SysRoleLogic, SysRoleRepo> {

    /**
     * 分页查询用户
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户", description = "分页查询用户")
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

}
