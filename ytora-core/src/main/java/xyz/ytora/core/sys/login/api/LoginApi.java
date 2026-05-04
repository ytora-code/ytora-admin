package xyz.ytora.core.sys.login.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.ResultCode;
import xyz.ytora.core.sys.login.logic.LoginLogic;
import xyz.ytora.core.sys.login.model.data.LoginUserDetail;
import xyz.ytora.core.sys.login.model.param.CheckPasswordParam;
import xyz.ytora.core.sys.login.model.param.LoginParam;
import xyz.ytora.core.sys.login.model.param.RefreshPasswordParam;

/**
 * 登录模块的API层
 *
 * @author ytora 
 * @since 1.0
 */
@RestController
@RequestMapping("/sys")
@RequiredArgsConstructor
@Tag(name = "登录")
public class LoginApi {

    private final LoginLogic loginService;

    /**
     * 获取登录验证码
     */
    @GetMapping("/getCode")
    @Operation(summary = "获取登录Code", description = "code可能是图形码，可能是短信，可能是邮寄，视业务而定")
    public R<?> getCaptcha() {
        return R.success();
    }

    /**
     * 执行登录
     */
    @PostMapping("/doLogin")
    @Operation(summary = "执行登录", description = "执行登录")
    public R<LoginUserDetail> doLogin(@Valid @RequestBody LoginParam param) {
        return R.success("登陆成功", loginService.doLogin(param));
    }

    /**
     * 执行登出
     */
    @GetMapping("/doLogout")
    @Operation(summary = "执行登出", description = "根据token执行登出")
    public R<?> doLogout(@CookieValue(value = "Authorization", required = false) String token) {
        loginService.doLogout(token);
        return R.success("退出成功");
    }

    /**
     * 根据token获取用户详情数据
     */
    @GetMapping("/queryDetailByToken")
    @Operation(summary = "获取用户详情数据", description = "根据token获取用户详情数据")
    public R<LoginUserDetail> queryDetailByToken(@CookieValue(value = "Authorization", required = false) String token) {
        if (token == null) {
            throw new BaseException(ResultCode.NOT_LOGIN);
        }
        LoginUser loginUser = loginService.queryLoginUserByToken(token);
        if (loginUser == null) {
            //虽然根据token没有查到数据，但带有token，还是认为用户之前登录过，只是过期了
            throw new BaseException(ResultCode.LOGIN_TIMEOUT);
        }

        return R.success(loginService.queryUserDetail(loginUser.getId()));
    }

    /**
     * 校验密码
     */
    @PostMapping("/checkPassword")
    @Operation(summary = "校验密码", description = "校验密码")
    public R<String> checkPassword(@RequestBody @Valid CheckPasswordParam param) {
        return null;
    }

    /**
     * 更新密码
     */
    @PostMapping("/refreshPassword")
    @Operation(summary = "更新密码", description = "更新密码")
    public String refreshPassword(@RequestBody @Valid RefreshPasswordParam param) {
        loginService.refreshPassword(param);
        return null;
    }

}
