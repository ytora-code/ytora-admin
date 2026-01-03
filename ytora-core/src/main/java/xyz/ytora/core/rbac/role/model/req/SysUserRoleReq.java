package xyz.ytora.core.rbac.role.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;

/**
 * 角色表请求数据
 */
@Data
@Schema(description = "角色表请求数据")
public class SysUserRoleReq {
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
     * 用户ID
     */
    @Schema(description = "是否新增，true是新增，false是移除")
    private Boolean add;
}
