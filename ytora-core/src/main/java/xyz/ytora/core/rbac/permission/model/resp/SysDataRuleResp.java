package xyz.ytora.core.rbac.permission.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.dict.Dict;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.permission.model.entity.SysDataRule;

/**
 * created by YT on 2026/1/10 17:14:22
 * <br/>
 * 数据规则
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDataRuleResp extends BaseResp<SysDataRule> {

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
    @Dict(code = "rule_type")
    private String ruleType;

    /**
     * 规则值
     */
    @Schema(description = "规则值")
    private String ruleValue;

    @Override
    public BaseExcel<SysDataRule> toExcel() {
        return null;
    }
}
