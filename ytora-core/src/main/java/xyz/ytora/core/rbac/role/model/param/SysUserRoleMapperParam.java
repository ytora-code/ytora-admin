package xyz.ytora.core.rbac.role.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色关系的请求参数
 */
@Data
@Schema(description = "获取用户-角色关系的请求参数")
public class SysUserRoleMapperParam {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;

}
