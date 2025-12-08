package xyz.ytora.core.rbac.permission.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseEntity;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDate;

/**
 * 系统用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_permission", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "资源表")
public class SysPermission extends BaseEntity<SysPermission> {
    /**
     * 用户名
     */
    @Index(1)
    @Schema(description = "用户名")
    @Column(comment = "用户名", notNull = true)
    private String userName;

    /**
     * 真实姓名
     */
    @Index(2)
    @Schema(description = "真实姓名")
    @Column(comment = "真实姓名", notNull = true)
    private String realName;

    /**
     * 密码
     */
    @Index(3)
    @Schema(description = "密码")
    @Column(comment = "密码", notNull = true)
    private String password;

    /**
     * 头像
     */
    @Index(4)
    @Schema(description = "头像")
    @Column(comment = "头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Index(5)
    @Schema(description = "手机号码")
    @Column(comment = "手机号码", columnType = "CHAR(11)")
    private String phone;

    /**
     * 邮箱
     */
    @Index(6)
    @Schema(description = "邮箱")
    @Column(comment = "邮箱")
    private String email;

    /**
     * 生日
     */
    @Index(7)
    @Schema(description = "生日")
    @Column(comment = "生日")
    private LocalDate birthday;

    /**
     * 身份证
     */
    @Index(8)
    @Schema(description = "身份证")
    @Column(comment = "身份证")
    private String idCard;
}
