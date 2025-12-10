package xyz.ytora.core.rbac.user.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseExcel;
import xyz.ytora.ytool.document.excel.Excel;

import java.time.LocalDate;

/**
 * EXCEL请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel(fileName = "用户数据")
public class SysUserExcel extends BaseExcel {
    /**
     * 用户名
     */
    @Excel("用户名")
    private String userName;

    /**
     * 真实姓名
     */
    @Excel("真实姓名")
    private String realName;

    /**
     * 密码
     */
    @Excel("密码")
    private String password;

    /**
     * 头像
     */
    @Excel("头像")
    private String avatar;

    /**
     * 手机号码
     */
    @Excel("手机号码")
    private String phone;

    /**
     * 邮箱
     */
    @Excel("邮箱")
    private String email;

    /**
     * 生日
     */
    @Excel("生日")
    private LocalDate birthday;

    /**
     * 身份证
     */
    @Excel("身份证")
    private String idCard;
}
