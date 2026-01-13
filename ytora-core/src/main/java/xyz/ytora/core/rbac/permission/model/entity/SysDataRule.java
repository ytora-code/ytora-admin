package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.dict.Dict;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.core.rbac.permission.model.resp.SysDataRuleResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.ColumnType;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * created by YT on 2026/1/10 17:14:22
 * <br/>
 * 数据规则
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_data_rule", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "数据规则")
public class SysDataRule extends BaseEntity<SysDataRule> {

    /**
     * 资源ID
     */
    @Index(1)
    @Column(comment = "资源ID", notNull = true, type = ColumnType.INT8)
    private String permissionId;

    /**
     * 规则名称
     */
    @Index(2)
    @Column(comment = "规则名称", notNull = true)
    private String ruleName;

    /**
     * 规则字段
     */
    @Index(3)
    @Column(comment = "规则字段")
    private String ruleField;

    /**
     * 规则类型
     */
    @Index(4)
    @Column(comment = "规则类型", notNull = true)
    private String ruleType;

    /**
     * 规则值
     */
    @Index(5)
    @Column(comment = "规则值")
    private String ruleValue;

    @Override
    public SysDataRuleResp toResp() {
        SysDataRuleResp ruleResp = new SysDataRuleResp();
        ruleResp.setId(id);
        ruleResp.setPermissionId(permissionId);
        ruleResp.setRuleName(ruleName);
        ruleResp.setRuleField(ruleField);
        ruleResp.setRuleType(ruleType);
        ruleResp.setRuleValue(ruleValue);
        return ruleResp;
    }
}
