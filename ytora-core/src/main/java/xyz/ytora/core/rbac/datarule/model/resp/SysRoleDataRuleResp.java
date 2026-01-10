package xyz.ytora.core.rbac.datarule.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.datarule.model.entity.SysRoleDataRule;

/**
 * 角色-数据规则关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDataRuleResp extends BaseResp<SysRoleDataRule> {

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
    public BaseExcel<SysRoleDataRule> toExcel() {
        return null;
    }

}
