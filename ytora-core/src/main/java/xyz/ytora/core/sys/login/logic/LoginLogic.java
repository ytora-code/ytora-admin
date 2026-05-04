package xyz.ytora.core.sys.login.logic;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.Identity;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.enums.Const;
import xyz.ytora.base.mvc.result.ResultCode;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.core.rbac.permission.logic.SysPermissionLogic;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionType;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.core.sys.log.LogType;
import xyz.ytora.core.sys.log.logic.SysLogLogic;
import xyz.ytora.core.sys.log.model.entity.SysLog;
import xyz.ytora.core.sys.login.model.data.LoginUserDetail;
import xyz.ytora.core.sys.login.model.param.LoginParam;
import xyz.ytora.core.sys.login.model.param.RefreshPasswordParam;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.sqlux.orm.type.Text;
import xyz.ytora.toolkit.bean.Beans;
import xyz.ytora.toolkit.id.Ids;
import xyz.ytora.toolkit.text.Strs;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * 登录模块业务逻辑层
 *
 * @author ytora 
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class LoginLogic {

    private final SysUserRepo userRepo;
    private final SysPermissionLogic permissionLogic;
    private final Identity identity;
    private final SysLogLogic logLogic;
    private final Caches caches;

    @Value("${ytora.auth.token-invalid-time}")
    private Long tokenInvalidTime;

    /**
     * 执行登录
     */
    public LoginUserDetail doLogin(@Valid LoginParam param) {
        long now = System.currentTimeMillis();

        String username = param.getUsername();
        String password = param.getPassword();

        Optional<SysUser> userOp = SQL.select().from(SysUser.class)
                .where(w -> w.eq(SysUser::getUserName, username))
                .submit(SysUser.class, 0);
        // 根据用户名未查到数据，说明用户不存在
        if (userOp.isEmpty()) {
            throw new BaseException(ResultCode.UNKNOWN_USER_PASSWORD);
        }
        SysUser user = userOp.get();

        // 该用户被封禁，静止登录
        if (!Objects.equals(user.getStatus(), 1)) {
            throw new BaseException(ResultCode.BANNED_USER);
        }

        // 校验密码是否正确
        if (!identity.match(password, user.getPassword())) {
            throw new BaseException(ResultCode.UNKNOWN_USER_PASSWORD);
        }

        // 走到这，用户校验通过，登录成功
        LoginUserDetail userDetail = queryUserDetail(user.getId());

        // 产生token并将token作为cookie发送给前端
        UUID uuid = UUID.randomUUID();
        String randomStr = Strs.randomNumber(8);
        String token = uuid + randomStr;
        HttpServletResponse resp = ScopedValueContext.RESPONSE.get();
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
        loginUser.setIp(HttpUtil.getIp());
        caches.put(Const.LOGIN_TOKEN_PREFIXX.value() + token, loginUser, tokenInvalidTime * 1000);

        SysLog log = new SysLog();
        log.setType(LogType.LOGIN_LOG);
        log.setCreateBy(userDetail.getUserName());
        log.setContent(Text.of("登录系统"));
        logLogic.doLog(log);

        return userDetail;
    }

    /**
     * 执行登出
     */
    public void doLogout(String token) {
        HttpServletResponse resp = ScopedValueContext.RESPONSE.get();

        String userName = null;
        if (Strs.isNotEmpty(token)) {
            //移除缓存
            LoginUser user = caches.remove(buildTokenCacheKey(token));
            userName = user.getUserName();

            //移除前端cookie
            ResponseCookie cookie = ResponseCookie.from("Authorization", token)
                    .path("/")
                    .httpOnly(false)
                    .maxAge(0)
                    .build();
            resp.addHeader("Set-Cookie", cookie.toString());
        }

        SysLog log = new SysLog();
        log.setType(LogType.LOGIN_LOG);
        log.setCreateBy(userName);
        log.setContent(Text.of("退出登录"));
        logLogic.doLog(log);
    }

    /**
     * 根据token查询登录用户
     *
     * @param token 原始token
     * @return 登录用户
     */
    public LoginUser queryLoginUserByToken(String token) {
        if (Strs.isEmpty(token)) {
            return null;
        }
        return caches.get(buildTokenCacheKey(token));
    }

    /**
     * 根据用户id查询用户详情
     * @param userid 用户id
     * @return 用户详情
     */
    public LoginUserDetail queryUserDetail(String userid) {
        // step1.根据id查询用户信息
        Optional<SysUser> userOp = SQL.select().from(SysUser.class)
                .where(w -> w.eq(SysUser::getId, userid))
                .submit(SysUser.class, 0);
        if (userOp.isEmpty()) {
            throw new BaseException(ResultCode.UNKNOWN_USER);
        }
        SysUser user = userOp.get();
        if (!Objects.equals(user.getStatus(), 1)) {
            throw new BaseException(ResultCode.BANNED_USER);
        }
        LoginUserDetail userDetail = Beans.copyTo(user, LoginUserDetail.class);

        // step2.查询该用户拥有的角色
        List<SysRole> roles = userRepo.listAllRoleByUserId(user.getId());
        userDetail.setRoles(roles);

        // step3.查询该用户拥有的菜单
        List<SysPermissionData> menus = permissionLogic.listAllMenu(roles.stream().map(SysRole::getId).toList());
        userDetail.setMenus(menus);

        // step4.查询该用户拥有的组件
        SysPermissionType permissionType = permissionLogic.listAllComponent(roles.stream().map(SysRole::getId).toList());
        userDetail.setTables(permissionType.getTables());
        userDetail.setForms(permissionType.getForms());
        userDetail.setItems(permissionType.getItems());

        return userDetail;
    }

    /**
     * 更新密码
     */
    public void refreshPassword(RefreshPasswordParam param) {
    }

    private String buildTokenCacheKey(String token) {
        return Const.LOGIN_TOKEN_PREFIXX.value() + token;
    }
}
