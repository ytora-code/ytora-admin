package xyz.ytora.core.rbac.datascope.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;
import xyz.ytora.core.rbac.datascope.model.excel.SysRoleDataScopeExcel;

/**
 * 角色-数据范围关系响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色-数据范围关系表响应数据")
public class SysRoleDataScopeData extends BaseData<SysRoleDataScope> {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 数据分组ID
     */
    @Schema(description = "数据分组ID")
    private String groupId;

    /**
     * 数据范围ID
     */
    @Schema(description = "数据范围ID")
    private String scopeId;

    @Override
    public SysRoleDataScopeExcel toExcel() {
        SysRoleDataScopeMapper mapper = SysRoleDataScopeMapper.mapper;
        return mapper.toExcel(this);
    }
}
