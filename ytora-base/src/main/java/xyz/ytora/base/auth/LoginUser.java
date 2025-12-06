package xyz.ytora.base.auth;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登录用户
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

}
