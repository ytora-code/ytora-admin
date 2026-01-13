package xyz.ytora.core.rbac.permission.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleDataRule;

import java.util.List;

/**
 * 角色-数据规则关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDataRuleResp extends BaseResp<SysRoleDataRule> {

    /**
     * 数据规则名称
     */
    @Schema(description = "数据规则名称")
    private String ruleName;
    /**
     * 数据规则
     */
    @Schema(description = "数据规则")
    List<SysDataRuleResp> dataRules;

    /**
     * 数据规则ID
     */
    @Schema(description = "数据规则ID")
    List<String> ruleIds;

    @Override
    public BaseExcel<SysRoleDataRule> toExcel() {
        return null;
    }

}
