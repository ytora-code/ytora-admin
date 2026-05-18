package xyz.ytora.core.sys.coderule.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.coderule.model.SysCodeRuleMapper;
import xyz.ytora.core.sys.coderule.model.data.SysCodeRuleData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 系统编码规则表
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_code_rule", idType = IdType.SNOWFLAKE, comment = "系统编码规则表")
public class SysCodeRule extends BaseEntity<SysCodeRule> {

    /**
     * 规则编码，如 ORDER_NO、DDBM
     */
    @Column(comment = "规则编码，如 ORDER_NO、DDBM", notNull = true)
    private String ruleCode;

    /**
     * 规则名称
     */
    @Column(comment = "规则名称", notNull = true)
    private String ruleName;

    /**
     * 编码模板，如 DDBM-${date:yyyy-MM-dd}-${seq:4}、DDBM-${date:yyyy}-${seq:4}、DDBM-${week}-${seq:4}、DDBM-${date:yyyy-MM-dd}-${uuid:32}、DDBM-${date:yyyy-MM-dd HH:mm}-${snow:20}
     */
    @Column(comment = "编码模板，如 DDBM-${date:yyyy-MM-dd}-${seq:4}、DDBM-${date:yyyy}-${seq:4}、DDBM-${week}-${seq:4}、DDBM-${date:yyyy-MM-dd}-${uuid:32}、DDBM-${date:yyyy-MM-dd HH:mm}-${snow:20}", notNull = true)
    private String ruleTemplate;

    /**
     * seq流水起始值，默认0，表示第一次生成0000
     */
    @Column(comment = "seq流水起始值，默认0，表示第一次生成0000", notNull = true)
    private Long seqStart;

    /**
     * seq流水步长，默认1
     */
    @Column(comment = "seq流水步长，默认1", notNull = true)
    private Integer seqStep;

    /**
     * 日期时区
     */
    @Column(comment = "日期时区", notNull = true)
    private String timezone;

    @Override
    public SysCodeRuleData toData() {
        SysCodeRuleMapper mapper = SysCodeRuleMapper.mapper;
        return mapper.toData(this);
    }
}
