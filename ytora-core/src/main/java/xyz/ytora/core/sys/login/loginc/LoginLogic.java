package xyz.ytora.core.sys.login.loginc;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.Identity;
import xyz.ytora.core.sys.login.model.req.LoginReq;
import xyz.ytora.core.sys.login.model.resp.SysUserDetailResp;

/**
 * created by YT on 2025/12/13 18:29:26
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class LoginLogic {

    private final Identity identity;

    /**
     * 执行登录
     */
    public SysUserDetailResp doLogin(@Valid LoginReq loginReq) {
        return null;
    }
}
