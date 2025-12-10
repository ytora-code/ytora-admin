package xyz.ytora.core.rbac.user.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseEntity;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDate;

/**
 * 用户表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "用户表")
public class SysUser extends BaseEntity<SysUser> {
    /**
     * 用户名
     */
    @Index(1)
    @Column(comment = "用户名", notNull = true)
    private String userName;

    /**
     * 真实姓名
     */
    @Index(2)
    @Column(comment = "真实姓名", notNull = true)
    private String realName;

    /**
     * 密码
     */
    @Index(3)
    @Column(comment = "密码", notNull = true)
    private String password;

    /**
     * 头像
     */
    @Index(4)
    @Column(comment = "头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Index(5)
    @Column(comment = "手机号码", columnType = "CHAR(11)")
    private String phone;

    /**
     * 邮箱
     */
    @Index(6)
    @Column(comment = "邮箱")
    private String email;

    /**
     * 生日
     */
    @Index(7)
    @Column(comment = "生日")
    private LocalDate birthday;

    /**
     * 身份证
     */
    @Index(8)
    @Column(comment = "身份证")
    private String idCard;
}
