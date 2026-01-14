package xyz.ytora.core.sys.login.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.core.rbac.role.model.entity.SysRole;

import java.util.List;

/**
 * 登录用户的详情
 */
@Data
@Schema(name = "用户详情")
public class LoginUserDetailResp {
    /**
     * 主键id
     */
    @Schema(name = "用户id")
    private String id;
    /**
     * 登录使用账号，唯一
     */
    @Schema(name = "用户名")
    private String userName;
    /**
     * 选填
     */
    @Schema(name = "用户真实姓名")
    private String realName;
    /**
     * 图片base64编码，选填
     */
    @Schema(name = "用户头像")
    private String avatar;
    /**
     * 选填
     */
    @Schema(name = "手机号")
    private String phone;

    /**
     * 用户当前部门
     */
    @Schema(name = "用户当前部门")
    private String departCode;

    /**
     * 用户当前部门
     */
    @Schema(name = "用户当前部门")
    private String departName;

    /**
     * 同上
     */
    @Schema(name = "邮箱")
    private String email;

    /**
     * 这个看着填
     */
    @Schema(name = "备注")
    private String remark;
    /**
     * 一个用户对应多个角色
     */
    @Schema(name = "该用户角色")
    private List<SysRole> roles;

    /**
     * 该用户拥有菜单
     */
    @Schema(name = "该用户拥有菜单")
    private List<SysPermissionResp> menus;

    /**
     * 该用户拥有 TABLE 组件
     */
    @Schema(name = "该用户拥有 TABLE 组件")
    private List<SysPermissionResp> tables;

    /**
     * 该用户拥有 FORM 组件
     */
    @Schema(name = "该用户拥有 FORM 组件")
    private List<SysPermissionResp> forms;

    /**
     * 该用户拥有其他组件
     */
    @Schema(name = "该用户拥有其他组件")
    private List<SysPermissionResp> items;

}
