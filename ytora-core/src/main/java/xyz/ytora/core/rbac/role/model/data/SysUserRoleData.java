package xyz.ytora.core.rbac.role.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色响应数据
 */
@Data
@Schema(description = "用户-角色响应数据")
public class SysUserRoleData {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色备注
     */
    private String roleRemark;

    /**
     * 是否拥有
     */
    private Boolean owner;
}
