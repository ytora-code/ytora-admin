package xyz.ytora.core.rbac.datascope.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.core.rbac.datascope.model.excel.SysDataScopeExcel;

/**
 * 数据范围响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "数据范围响应数据")
public class SysDataScopeData extends BaseData<SysDataScope> {

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
    public SysDataScopeExcel toExcel() {
        SysDataScopeMapper mapper = SysDataScopeMapper.mapper;
        return mapper.toExcel(this);
    }
}
