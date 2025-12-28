package xyz.ytora.core.rbac.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.R;
import xyz.ytora.base.querygen.WhereGenerator;
import xyz.ytora.core.rbac.user.logic.SysUserLogic;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.req.SysUserReq;
import xyz.ytora.core.rbac.user.model.resp.SysUserResp;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;

/**
 * 用户 控制器
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/rbac/sysUser")
@RequiredArgsConstructor
public class SysUserApi extends BaseApi<SysUser, SysUserLogic, SysUserRepo> {

    /**
     * 分页查询用户
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户", description = "分页查询用户")
    public Page<SysUserResp> page(@ParameterObject SysUserReq userdata,
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        ConditionExpressionBuilder where = WhereGenerator.where();
        Page<SysUser> page = repository.page(pageNo, pageSize, where);
        return Pages.transPage(page, SysUser::toResp);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysUserResp queryById(@RequestParam String id) {
        SysUser entity = repository.one(w -> w.eq(SysUserReq::getId, id));
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
    public R<String> insertOrUpdate(@RequestBody SysUserReq sysUserReq) {
        if (sysUserReq.getId() == null) {
            repository.insert(sysUserReq.toEntity());
            return R.success("新增成功");
        } else {
            repository.update(sysUserReq.toEntity(), w -> w.eq(SysUserReq::getId, sysUserReq.getId()));
            return R.success("编辑成功");
        }
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> delete(String id) {
        ConditionExpressionBuilder where = WhereGenerator.where();
        repository.delete(where);
        return R.success("删除成功");
    }

}
