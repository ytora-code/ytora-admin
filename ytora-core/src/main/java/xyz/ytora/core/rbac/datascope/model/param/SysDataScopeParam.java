package xyz.ytora.core.rbac.datascope.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;

/**
 * 数据范围请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "数据范围请求数据")
public class SysDataScopeParam extends BaseParam<SysDataScope> {

    /**
     * 所属分组ID
     */
    @Schema(description = "所属分组ID")
    private String groupId;

    /**
     * 数据范围名称
     */
    @Schema(description = "数据范围名称")
    private String name;

    /**
     * 数据范围匹配的列
     */
    @Schema(description = "数据范围匹配的列")
    private String column;

    /**
     * 数据范围类型
     */
    @Schema(description = "数据范围类型")
    private String type;

    /**
     * 数据范围的规则
     */
    @Schema(description = "数据范围的规则")
    private String value;

    @Override
    public SysDataScope toEntity() {
        SysDataScopeMapper mapper = SysDataScopeMapper.mapper;
        return mapper.toEntity(this);
    }
}
