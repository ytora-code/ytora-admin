package xyz.ytora.core.rbac.datarule.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.datarule.model.entity.SysRoleDataRule;

/**
 * 角色-数据规则关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDataRuleReq extends BaseReq<SysRoleDataRule> {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 数据规则ID
     */
    @Schema(description = "数据规则ID")
    private String ruleId;

    @Override
    public SysRoleDataRule toEntity() {
        SysRoleDataRule mapper = new SysRoleDataRule();
        mapper.setRoleId(roleId);
        mapper.setRuleId(ruleId);
        return mapper;
    }
}
