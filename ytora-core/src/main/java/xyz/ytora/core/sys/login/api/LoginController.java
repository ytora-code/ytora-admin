package xyz.ytora.core.sys.login.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.sys.login.loginc.LoginLogic;
import xyz.ytora.core.sys.login.model.req.LoginReq;
import xyz.ytora.core.sys.login.model.resp.SysUserDetailResp;

@RestController
@RequestMapping("/sys")
@RequiredArgsConstructor
@Tag(name = "登录")
public class LoginController {

    private final LoginLogic loginService;

    /**
     * 执行登录
     */
    @PostMapping("/doLogin")
    @Operation(summary = "执行登录", description = "执行登录")
    public R<SysUserDetailResp> doLogin(@Valid @RequestBody LoginReq loginReq) {
        return R.success("登陆成功", loginService.doLogin(loginReq));
    }

}
