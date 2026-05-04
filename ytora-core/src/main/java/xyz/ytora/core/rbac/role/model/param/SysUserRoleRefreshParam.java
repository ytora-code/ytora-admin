package xyz.ytora.core.rbac.role.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色表请求数据
 */
@Data
@Schema(description = "角色表请求数据")
public class SysUserRoleRefreshParam {
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 是否新增，true是新增，false是移除
     */
    @Schema(description = "是否新增，true是新增，false是移除")
    private Boolean add;
}
