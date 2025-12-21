package xyz.ytora.core.rbac.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.sql4j.core.SQLHelper;

/**
 * 用户 控制器
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/rbac/sysUser")
@RequiredArgsConstructor
public class SysUserApi {
    private final SQLHelper sqlHelper;
    private final SysUserRepo sysUserRepo;

    /**
     * 分页查询字典
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询字典", description = "分页查询字典")
    public R<?> page() {
        Integer submit = sqlHelper.delete().from(SysUser.class).where(w -> w.ne(SysUser::getId, 111)).submit();
        return R.success(submit);
    }

    @GetMapping("/test")
    @Operation(summary = "测试", description = "测试")
    public R<?> test() {
        SysUser sysUser = sysUserRepo.one(w -> w.eq(SysUser::getId, 111));

        return R.success(sysUser);
    }

}
