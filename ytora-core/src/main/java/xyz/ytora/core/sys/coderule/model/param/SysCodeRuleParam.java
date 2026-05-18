package xyz.ytora.core.sys.coderule.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.coderule.model.SysCodeRuleMapper;
import xyz.ytora.core.sys.coderule.model.entity.SysCodeRule;

/**
 * 系统编码规则请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统编码规则表请求数据")
public class SysCodeRuleParam extends BaseParam<SysCodeRule> {

    /**
     * 规则编码，如 ORDER_NO、DDBM
     */
    @Schema(description = "规则编码，如 ORDER_NO、DDBM")
    private String ruleCode;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String ruleName;

    /**
     * 编码模板，如 DDBM-${date:yyyy-MM-dd}-${seq:4}、DDBM-${date:yyyy}-${seq:4}、DDBM-${week}-${seq:4}、DDBM-${date:yyyy-MM-dd}-${uuid:32}、DDBM-${date:yyyy-MM-dd HH:mm}-${snow:20}
     */
    @Schema(description = "编码模板，如 DDBM-${date:yyyy-MM-dd}-${seq:4}、DDBM-${date:yyyy}-${seq:4}、DDBM-${week}-${seq:4}、DDBM-${date:yyyy-MM-dd}-${uuid:32}、DDBM-${date:yyyy-MM-dd HH:mm}-${snow:20}")
    private String ruleTemplate;

    /**
     * seq流水起始值，默认0，表示第一次生成0000
     */
    @Schema(description = "seq流水起始值，默认0，表示第一次生成0000")
    private Long seqStart;

    /**
     * seq流水步长，默认1
     */
    @Schema(description = "seq流水步长，默认1")
    private Integer seqStep;

    /**
     * 日期时区
     */
    @Schema(description = "日期时区")
    private String timezone;

    @Override
    public SysCodeRule toEntity() {
        SysCodeRuleMapper mapper = SysCodeRuleMapper.mapper;
        return mapper.toEntity(this);
    }
}
