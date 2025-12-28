package xyz.ytora.base.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * auth 属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "ytora.auth")
public class AuthProperty {

    /**
     * 是否启动权限认证模块
     */
    private Boolean enable;

    /**
     * 用户多久不操作登录会失效，单位s，默认600s: 10分钟
     */
    private Long tokenInvalidTime;

    /**
     * 是否允许跨域请求携带凭据（如 cookies、HTTP 身份验证信息或客户端证书）进行跨域请求
     */
    private Boolean allowCredentials = false;

    /**
     * 允许跨域访问的域名，如果 allowCredentials 为 ture ，那么 origins 不能设为 *
     */
    private String origins = "*";
}
