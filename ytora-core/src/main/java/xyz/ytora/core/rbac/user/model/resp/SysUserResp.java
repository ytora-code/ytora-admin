package xyz.ytora.core.rbac.user.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseResp;
import xyz.ytora.ytool.document.excel.Excel;

import java.time.LocalDate;

/**
 * 用户响应数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户表响应数据")
public class SysUserResp extends BaseResp {
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
}
