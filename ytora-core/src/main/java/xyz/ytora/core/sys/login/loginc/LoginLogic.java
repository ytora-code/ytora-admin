package xyz.ytora.core.sys.login.loginc;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.Identity;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.enums.RespCode;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.role.logic.SysRoleLogic;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.core.sys.login.model.req.LoginReq;
import xyz.ytora.core.sys.login.model.resp.LoginUserDetailResp;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.ytool.bean.Beans;
import xyz.ytora.ytool.str.Strs;
import xyz.ytora.ytool.tree.Trees;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * created by YT on 2025/12/13 18:29:26
 * <br/>
 * 登录相关逻辑
 */
@Service
@RequiredArgsConstructor
public class LoginLogic {

    private final SQLHelper sqlHelper;
    private final SysUserRepo sysUserRepo;
    private final SysRoleLogic roleLogic;
    private final Identity identity;
    private final Caches caches;

    @Value("${ytora.auth.token-invalid-time}")
    private Long tokenInvalidTime;

    /**
     * 执行登录
     */
    public LoginUserDetailResp doLogin(@Valid LoginReq loginReq) {
        long now = System.currentTimeMillis();

        String username = loginReq.getUsername();
        String password = loginReq.getPassword();
        SysUser user = sysUserRepo.one(w -> w.eq(SysUser::getUserName, username));
        if (user == null) {
            throw new BaseException(RespCode.UNKNOWN_USER_PASSWORD);
        }
        if (!Objects.equals(user.getStatus(), "1")) {
            throw new BaseException(RespCode.BANNED_USER);
        }
        // 校验密码是否正确
        if (!identity.match(password, user.getPassword())) {
            throw new BaseException(RespCode.UNKNOWN_USER_PASSWORD);
        }
        // 登录校验通过，获取用户详细信息
        LoginUserDetailResp userDetail = queryUserDetail(user.getId());

        // 产生token并将token作为cookie发送给前端
        UUID uuid = UUID.randomUUID();
        String randomStr = Strs.randomNumber(8);
        String token = "TKN-" + uuid + randomStr;
        HttpServletResponse resp = ScopedValueItem.RESPONSE.get();
        ResponseCookie cookie = ResponseCookie.from("Authorization", token)
                .httpOnly(false) //防止XSS窃取（前端JS无法访问）
                .secure(false)  //是否仅HTTPS
                .path("/")      //cookie生效路径
                .maxAge(60 * 60 * 24 * 7)  //有效时间7天
                .sameSite("Lax") //防止CSRF，推荐Lax或Strict
                .build();
        resp.addHeader("Set-Cookie", cookie.toString());

        // 存入缓存
        LoginUser loginUser = new LoginUser();
        Beans.copyProperties(userDetail, loginUser);
        loginUser.setLoginTime(now);
        loginUser.setLastRequestTime(now);
        loginUser.setRequestCount(1L);
        caches.put(token, loginUser, tokenInvalidTime * 1000);

        return userDetail;
    }

    public LoginUserDetailResp queryUserDetail(String userid) {
        LoginUserDetailResp userDetail = new LoginUserDetailResp();
        // step1.根据id查询用户信息
        SysUser user = sysUserRepo.one(w -> w.eq(SysUser::getId, userid));
        if (user == null) {
            throw new BaseException("用户不存在");
        }
        if (!Objects.equals(user.getStatus(), "1")) {
            throw new BaseException(RespCode.BANNED_USER);
        }
        Beans.copyProperties(user, userDetail);

        // step2.查询该用户拥有的角色
        List<SysRole> roles = sysUserRepo.listAllRoleByUserId(user.getId());
        userDetail.setRoles(roles);

        // step3.查询该用户拥有的资源
        List<SysPermission> permissions = roles.stream().flatMap(role -> roleLogic.listAllPermission(role.getId()).stream()).toList();
        userDetail.setPermissions(Trees.toTree(permissions.stream().map(SysPermission::toResp).toList()));

        return userDetail;
    }
}
