package xyz.ytora.base.auth.handler.support;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import xyz.ytora.base.auth.AuthException;
import xyz.ytora.base.auth.handler.PublicResourcesHandler;

import java.util.HashSet;

/**
 * created by YT on 2025/12/6 18:10:03
 * <br/>
 * 默认的公共资源处理器
 */
public class DefaultPublicResourcesHandler extends HashSet<String> implements PublicResourcesHandler {

    // 可以对路径进行通配符匹配
    AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean isPublic(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        for (String url : this) {
            //如果匹配成功，则返回true
            if (matcher.match(url, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    public DefaultPublicResourcesHandler put(String resource) {
        if (!resource.startsWith("/")) {
            resource = "/" + resource;
        }
        super.add(resource);
        return this;
    }

}
