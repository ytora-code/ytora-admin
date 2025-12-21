package xyz.ytora.core.rbac.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.user.model.SysUserMapper;
import xyz.ytora.core.rbac.user.model.entity.SysUser;

import java.time.LocalDate;

/**
 * 用户请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户表请求数据")
public class SysUserReq extends BaseReq<SysUser> {
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userName;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

    /**
     * 身份证
     */
    @Schema(description = "身份证")
    private String idCard;

    @Override
    public SysUser toEntity() {
        SysUserMapper mapper = SysUserMapper.mapper;
        return mapper.toEntity(this);
    }
}
