package xyz.ytora.core.rbac.role.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;

/**
 * 角色响应数据
 */
@Data
@Schema(description = "用户-角色响应数据")
public class SysUserRoleResp {

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
