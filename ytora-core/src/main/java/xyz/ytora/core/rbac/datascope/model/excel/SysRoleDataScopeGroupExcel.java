package xyz.ytora.core.rbac.datascope.model.excel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("角色-数据范围分组关系列表")
public class SysRoleDataScopeGroupExcel extends BaseExcel<SysRoleDataScopeGroup> {

    /**
     * 角色ID
     */
    @Excel("角色ID")
    private String roleId;

    /**
     * 所属的资源ID
     */
    @Schema(description = "所属的资源ID")
    private String permissionId;

    /**
     * 数据范围分组ID
     */
    @Excel("数据范围分组ID")
    private String groupId;

    @Override
    public SysRoleDataScopeGroup toEntity() {
        SysRoleDataScopeGroupMapper mapper = SysRoleDataScopeGroupMapper.mapper;
        return mapper.toEntity(this);
    }
}
