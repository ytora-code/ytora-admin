package xyz.ytora.core.sys.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.core.rbac.user.logic.SysUserLogic;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;

@Tag(name = "测试")
@RestController
@RequestMapping("/sys/test")
@RequiredArgsConstructor
public class TestApi extends BaseApi<SysUser, SysUserLogic, SysUserRepo> {

    @GetMapping("/testSlowSql")
    @Operation(summary = "慢查询测试", description = "慢查询测试")
    public Object page() {
        SQLHelper sqlHelper = SQLHelper.getInstance();
        sqlHelper.select(Raw.of("pg_sleep(1)")).from(SysUser.class).where(w -> w.ne(SysUser::getId, 123)).submit();
        sqlHelper.select(Raw.of("pg_sleep(3)")).from(SysUser.class).where(w -> w.ne(SysUser::getId, 123)).submit();
        sqlHelper.select(Raw.of("pg_sleep(5)")).from(SysUser.class).where(w -> w.ne(SysUser::getId, 123)).submit();
        System.out.println(sqlHelper.getSlowSqlQueue());
        return "成功";
    }

}
