package xyz.ytora.core.rbac.datascope.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.excel.SysRoleDataScopeGroupExcel;
import xyz.ytora.sqlux.core.anno.Column;

/**
 * 角色-数据范围分组关系响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色-数据范围分组关系表响应数据")
public class SysRoleDataScopeGroupData extends BaseData<SysRoleDataScopeGroup> {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 所属的资源ID
     */
    @Schema(description = "所属的资源ID")
    private Long permissionId;

    /**
     * 数据范围分组ID
     */
    @Schema(description = "数据范围分组ID")
    private String groupId;

    @Override
    public SysRoleDataScopeGroupExcel toExcel() {
        SysRoleDataScopeGroupMapper mapper = SysRoleDataScopeGroupMapper.mapper;
        return mapper.toExcel(this);
    }
}
