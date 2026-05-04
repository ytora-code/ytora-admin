package xyz.ytora.core.rbac.user.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.user.model.SysUserMapper;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.toolkit.document.excel.Excel;

import java.time.LocalDate;

/**
 * EXCEL请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("用户列表")
public class SysUserExcel extends BaseExcel<SysUser> {
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

    /**
     * 备注
     */
    @Excel("备注")
    private String remark;

    /**
     * 状态
     */
    @Excel("状态")
    private Integer status;

    @Override
    public SysUser toEntity() {
        SysUserMapper mapper = SysUserMapper.mapper;
        return mapper.toEntity(this);
    }
}
