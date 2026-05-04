package xyz.ytora.core.rbac.datascope.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.excel.SysDataScopeGroupExcel;

/**
 * 数据范围组响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "数据范围组响应数据")
public class SysDataScopeGroupData extends BaseData<SysDataScopeGroup> {

    /**
     * 所属的资源ID
     */
    @Schema(description = "所属的资源ID")
    private String permissionId;

    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    private String name;

    /**
     * 分组编码
     */
    @Schema(description = "分组编码")
    private String code;

    @Override
    public SysDataScopeGroupExcel toExcel() {
        SysDataScopeGroupMapper mapper = SysDataScopeGroupMapper.mapper;
        return mapper.toExcel(this);
    }
}
