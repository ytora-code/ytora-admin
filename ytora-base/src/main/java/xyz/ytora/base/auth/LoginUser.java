package xyz.ytora.base.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 认证通过后的登录用户
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Accessors(chain=true)
public class LoginUser {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户username
     */
    private String userName;

    /**
     * 用户真实名称
     */
    private String realName;

    /**
     * 用户部门编码
     */
    private String departCode;

    /**
     * 用户登录时的时间戳
     */
    private Long loginTime;

    /**
     * 用户上一次请求的时间戳
     */
    private Long lastRequestTime;

    /**
     * 请求次数
     */
    private Long requestCount;

    /**
     * 用户登录IP
     */
    private String ip;

}
