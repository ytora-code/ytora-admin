package xyz.ytora.core.sys.login.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.enums.RespCode;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.sys.login.loginc.LoginLogic;
import xyz.ytora.core.sys.login.model.req.LoginReq;
import xyz.ytora.core.sys.login.model.resp.LoginUserDetailResp;
import xyz.ytora.ytool.str.Strs;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sys")
@RequiredArgsConstructor
@Tag(name = "登录")
public class LoginController {

    private final LoginLogic loginService;
    private final Caches caches;

    /**
     * 执行登录
     */
    @PostMapping("/doLogin")
    @Operation(summary = "执行登录", description = "执行登录")
    public R<LoginUserDetailResp> doLogin(@Valid @RequestBody LoginReq loginReq) throws InterruptedException {
        return R.success("登陆成功", loginService.doLogin(loginReq));
    }

    /**
     * 执行登出
     */
    @GetMapping("/logout")
    @Operation(summary = "执行登出", description = "根据token执行登出")
    public R<?> logout(@CookieValue(value = "Authorization", required = false) String token, HttpServletResponse resp) {
        if (Strs.isNotEmpty(token)) {
            //移除缓存
            caches.remove(token);

            //移除前端cookie
            ResponseCookie cookie = ResponseCookie.from("Authorization", token)
                    .path("/")
                    .httpOnly(false)
                    .maxAge(0)
                    .build();
            resp.addHeader("Set-Cookie", cookie.toString());
        }

        return R.success("退出成功");
    }

    /**
     * 根据token获取用户详情数据
     */
    @GetMapping("/queryDetailByToken")
    @Operation(summary = "获取用户详情数据", description = "根据token获取用户详情数据")
    public R<LoginUserDetailResp> queryDetailByToken(@CookieValue(value = "Authorization", required = false) String token) {
        if (token == null) {
            throw new BaseException(RespCode.NOT_LOGIN);
        }
        LoginUser loginUser = caches.get(token);
        if (loginUser == null) {
            //虽然根据token没有查到数据，但带有token，还是认为用户之前登录过，只是过期了
            throw new BaseException(RespCode.LOGIN_TIMEOUT);
        }

        return R.success(loginService.queryUserDetail(loginUser.getId()));
    }

}
