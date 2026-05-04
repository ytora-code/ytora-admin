package xyz.ytora.base.auth;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ytora.base.auth.handler.AuthnHandler;
import xyz.ytora.base.auth.handler.AuthzHandler;
import xyz.ytora.base.auth.handler.CorsHandler;
import xyz.ytora.base.auth.handler.PublicResourcesHandler;
import xyz.ytora.base.auth.handler.support.DefaultAuthnHandler;
import xyz.ytora.base.auth.handler.support.DefaultAuthzHandler;
import xyz.ytora.base.auth.handler.support.DefaultCorsHandler;
import xyz.ytora.base.auth.handler.support.DefaultPublicResourcesHandler;
import xyz.ytora.base.swagger.SpringDocConfig;

import java.util.Arrays;

/**
 * auth 模块的配置类
 */
@Slf4j
@Configuration
public class AuthConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Resource
    AuthProperty authProperty;

    @Resource
    private SpringDocConfig springDocConfig;

    /**
     * 设置默认的跨域处理器
     */
    @Bean
    @ConditionalOnMissingBean(CorsHandler.class)
    public CorsHandler corsHandler() {
        DefaultCorsHandler corsHandler = new DefaultCorsHandler();
        corsHandler.setAuthProperty(authProperty);
        return corsHandler;
    }

    /**
     * 设置默认的公共资源处理器
     */
    @Bean
    @ConditionalOnMissingBean(PublicResourcesHandler.class)
    public PublicResourcesHandler publicResourcesHandler() {
        DefaultPublicResourcesHandler publicResourcesHandler = new DefaultPublicResourcesHandler();

        // 设置固定的公共资源
        publicResourcesHandler.put(contextPath);
        publicResourcesHandler.put(contextPath + "/");
        publicResourcesHandler.put(contextPath + "/favicon.ico");

        // 设置 swagger 相关路径为公共资源
        for (String path : springDocConfig.swaggerPath()) {
            publicResourcesHandler.put(path);
        }

        // 设置 PublicResource 中的路径为公共资源
        Arrays.stream(PublicResource.values())
                .map(resource -> resource.resource)
                .forEach(resource -> publicResourcesHandler.put(contextPath + resource));

        // TODO 数据库查询公共资源

        return publicResourcesHandler;
    }

    /**
     * 设置默认的认证处理器
     */
    @Bean
    @ConditionalOnMissingBean(AuthnHandler.class)
    public AuthnHandler authenticateHandler() {
        return new DefaultAuthnHandler();
    }

    /**
     * 设置默认的授权处理器
     */
    @Bean
    @ConditionalOnMissingBean(AuthzHandler.class)
    public AuthzHandler authorizeHandler() {
        return new DefaultAuthzHandler();
    }

    @Bean
    public AuthCoreFilter securityRegisterFilter(CorsHandler corsHandler, PublicResourcesHandler publicResourcesHandler, AuthnHandler authnHandler, AuthzHandler authzHandler) {
        return new AuthCoreFilter()
                .setAuthProperty(authProperty)
                .setCorsHandler(corsHandler)
                .setAuthenticateHandler(authnHandler)
                .setAuthorizeHandler(authzHandler)
                .setPublicResourcesHandler(publicResourcesHandler);
    }

    @Autowired
    public void autowired(AuthProperty authProperty) {
        if (authProperty.getEnable()) {
            log.info("~已开启权限认证模块 ☆✲ﾟ｡(((´♡‿♡`+)))｡ﾟ✲☆");
        }
    }
}