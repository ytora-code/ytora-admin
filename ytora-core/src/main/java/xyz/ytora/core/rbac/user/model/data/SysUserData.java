package xyz.ytora.core.rbac.user.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.user.model.SysUserMapper;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.core.sys.dict.Dict;

import java.time.LocalDate;

/**
 * 用户响应数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户表响应数据")
public class SysUserData extends BaseData<SysUser> {
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

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 数据创建人所属部门
     */
    @Schema(description = "数据创建人所属部门")
    @Dict(table = "sys_depart", code = "depart_code", text = "depart_name")
    private String departCode;

    @Override
    public SysUserExcel toExcel() {
        SysUserMapper mapper = SysUserMapper.mapper;
        return mapper.toExcel(this);
    }
}
