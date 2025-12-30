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
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色响应数据")
public class SysRoleResp extends BaseResp<SysRole> {
    /**
     * 角色名称
     */
    @Index(1)
    @Column(comment = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Index(2)
    @Column(comment = "角色编码", notNull = true)
    private String roleCode;
}
