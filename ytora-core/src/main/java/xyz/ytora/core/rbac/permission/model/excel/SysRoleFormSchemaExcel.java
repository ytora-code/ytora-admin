package xyz.ytora.core.rbac.permission.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.permission.model.SysRoleFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleFormSchema;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("角色-表单项关系列表")
public class SysRoleFormSchemaExcel extends BaseExcel<SysRoleFormSchema> {

    /**
     * 角色ID
     */
    @Excel("角色ID")
    private String roleId;

    /**
     * 表单的资源ID
     */
    @Excel("表单的资源ID")
    private String formId;

    /**
     * 表单项ID
     */
    @Excel("表单项ID")
    private String schemaId;

    @Override
    public SysRoleFormSchema toEntity() {
        SysRoleFormSchemaMapper mapper = SysRoleFormSchemaMapper.mapper;
        return mapper.toEntity(this);
    }
}
