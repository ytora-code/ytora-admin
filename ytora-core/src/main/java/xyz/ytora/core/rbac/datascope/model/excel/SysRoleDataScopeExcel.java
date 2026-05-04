package xyz.ytora.core.rbac.datascope.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("角色-数据范围关系列表")
public class SysRoleDataScopeExcel extends BaseExcel<SysRoleDataScope> {

    /**
     * 角色ID
     */
    @Excel("角色ID")
    private String roleId;

    /**
     * 数据分组ID
     */
    @Excel("数据分组ID")
    private String groupId;

    /**
     * 数据范围ID
     */
    @Excel("数据范围ID")
    private String scopeId;

    @Override
    public SysRoleDataScope toEntity() {
        SysRoleDataScopeMapper mapper = SysRoleDataScopeMapper.mapper;
        return mapper.toEntity(this);
    }
}
