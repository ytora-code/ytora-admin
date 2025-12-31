package xyz.ytora.core.sys.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.user.logic.SysUserLogic;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.req.SysUserReq;
import xyz.ytora.core.rbac.user.model.resp.SysUserResp;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.SqlInfo;
import xyz.ytora.sql4j.sql.select.SelectBuilder;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

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
