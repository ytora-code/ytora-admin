package xyz.ytora.core.rbac.permission.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.permission.model.SysRoleFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleFormSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysRoleFormSchemaExcel;

/**
 * 角色-表单项关系响应数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色-表单项关系表响应数据")
public class SysRoleFormSchemaData extends BaseData<SysRoleFormSchema> {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 表单的资源ID
     */
    @Schema(description = "表单的资源ID")
    private String formId;

    /**
     * 表单项ID
     */
    @Schema(description = "表单项ID")
    private String schemaId;

    @Override
    public SysRoleFormSchemaExcel toExcel() {
        SysRoleFormSchemaMapper mapper = SysRoleFormSchemaMapper.mapper;
        return mapper.toExcel(this);
    }
}
