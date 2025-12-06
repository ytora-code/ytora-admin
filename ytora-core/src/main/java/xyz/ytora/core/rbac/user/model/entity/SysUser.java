package xyz.ytora.core.rbac.user.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseEntity;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;

import java.time.LocalDate;

/**
 * 系统用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user_123", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "用户表")
public class SysUser extends BaseEntity<SysUser> {
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Column(comment = "用户名")
    private String userName;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    @Column(comment = "真实姓名")
    private String realName;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @Column(comment = "密码")
    private String password;

    /**
     * 头像
     */
    @Schema(description = "头像")
    @Column(comment = "头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    @Column(comment = "手机号码", columnType = "CHAR(11)")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Column(comment = "邮箱")
    private String email;

    /**
     * 生日
     */
    @Schema(description = "生日")
    @Column(comment = "生日")
    private LocalDate birthday;

    /**
     * 身份证
     */
    @Schema(description = "身份证")
    @Column(comment = "身份证")
    private String idCard;
}
