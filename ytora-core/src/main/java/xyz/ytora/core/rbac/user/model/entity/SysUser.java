package xyz.ytora.core.rbac.user.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import xyz.ytora.base.dict.Dict;
import xyz.ytora.base.dict.IDictParser;
import xyz.ytora.base.model.BaseEntity;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "用户表")
@Component
public class SysUser extends BaseEntity<SysUser> {
    /**
     * 用户名
     */
    @Index(1)
    @Schema(description = "用户名")
    @Column(comment = "用户名", notNull = true)
    @Dict(table = "sys_user", code = "user_name", text = "real_name")
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

    @Resource
    private IDictParser dictParser;
    @PostConstruct
    public void init() {
        SysUser sysUser1 = new SysUser();
        sysUser1.setUserName("admin");
        SysUser sysUser2 = new SysUser();
        sysUser2.setUserName("test_123123");
        Map<String, Object> translate = dictParser.translate(sysUser1);
        System.out.println(translate);
    }
}
