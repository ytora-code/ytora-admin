package xyz.ytora.core.rbac.user.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.user.model.SysUserMapper;
import xyz.ytora.core.rbac.user.model.data.SysUserData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

import java.time.LocalDate;

/**
 * 用户表
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user", idType = IdType.SNOWFLAKE, comment = "用户表")
public class SysUser extends BaseEntity<SysUser> {

    /**
     * 用户名
     */
    @Column(comment = "用户名", notNull = true)
    private String userName;

    /**
     * 真实姓名
     */
    @Column(comment = "真实姓名", notNull = true)
    private String realName;

    /**
     * 密码
     */
    @Column(comment = "密码", notNull = true)
    private String password;

    /**
     * 头像
     */
    @Column(comment = "头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Column(comment = "手机号码", type = ColumnType.VARCHAR16)
    private String phone;

    /**
     * 邮箱
     */
    @Column(comment = "邮箱")
    private String email;

    /**
     * 生日
     */
    @Column(comment = "生日")
    private LocalDate birthday;

    /**
     * 身份证
     */
    @Column(comment = "身份证")
    private String idCard;

    /**
     * 状态
     */
    @Column(comment = "状态,1-正常/2-冻结")
    private Integer status;

    @Override
    public SysUserData toData() {
        SysUserMapper mapper = SysUserMapper.mapper;
        return mapper.toData(this);
    }
}
