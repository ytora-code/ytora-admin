package xyz.ytora.core.rbac.permission.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.permission.model.entity.SysDataRule;

/**
 * created by YT on 2026/1/10 17:14:22
 * <br/>
 * 数据规则
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDataRuleReq extends BaseReq<SysDataRule> {

    /**
     * 资源ID
     */
    @Schema(description = "规则字段")
    private String permissionId;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String ruleName;

    /**
     * 规则字段
     */
    @Schema(description = "规则字段")
    private String ruleField;

    /**
     * 规则类型
     */
    @Schema(description = "规则类型")
    private String ruleType;

    /**
     * 规则值
     */
    @Schema(description = "规则值")
    private String ruleValue;

    @Override
    public SysDataRule toEntity() {
        SysDataRule rule = new SysDataRule();
        rule.setId(id);
        rule.setPermissionId(permissionId);
        rule.setRuleName(ruleName);
        rule.setRuleField(ruleField);
        rule.setRuleType(ruleType);
        rule.setRuleValue(ruleValue);
        return rule;
    }
}
