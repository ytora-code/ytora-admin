package xyz.ytora.core.rbac.permission.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.permission.model.SysRoleTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleTableSchema;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("角色-表字段关系列表")
public class SysRoleTableSchemaExcel extends BaseExcel<SysRoleTableSchema> {

    /**
     * 角色ID
     */
    @Excel("角色ID")
    private String roleId;

    /**
     * 表格的资源ID
     */
    @Excel("表格的资源ID")
    private String tableId;

    /**
     * 表格列ID
     */
    @Excel("表格列ID")
    private String schemaId;

    @Override
    public SysRoleTableSchema toEntity() {
        SysRoleTableSchemaMapper mapper = SysRoleTableSchemaMapper.mapper;
        return mapper.toEntity(this);
    }
}
