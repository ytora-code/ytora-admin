package xyz.ytora.core.rbac.role.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.ytool.document.excel.Excel;

import java.time.LocalDate;

/**
 * EXCEL请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel(fileName = "用户数据")
public class SysRoleExcel extends BaseExcel<SysRole> {
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

    @Override
    public SysRole toEntity() {
        return SysRoleMapper.mapper.toEntity(this);
    }
}
