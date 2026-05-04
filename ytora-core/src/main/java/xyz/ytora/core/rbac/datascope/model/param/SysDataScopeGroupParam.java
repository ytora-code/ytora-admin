package xyz.ytora.core.rbac.datascope.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;

/**
 * 数据范围组请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "数据范围组请求数据")
public class SysDataScopeGroupParam extends BaseParam<SysDataScopeGroup> {

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
    public SysDataScopeGroup toEntity() {
        SysDataScopeGroupMapper mapper = SysDataScopeGroupMapper.mapper;
        return mapper.toEntity(this);
    }
}
