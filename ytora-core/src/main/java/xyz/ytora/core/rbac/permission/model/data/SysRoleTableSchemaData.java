package xyz.ytora.core.rbac.permission.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.permission.model.SysRoleTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleTableSchema;
import xyz.ytora.core.rbac.permission.model.excel.SysRoleTableSchemaExcel;

/**
 * 角色-表字段关系响应数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色-表字段关系表响应数据")
public class SysRoleTableSchemaData extends BaseData<SysRoleTableSchema> {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 表格的资源ID
     */
    @Schema(description = "表格的资源ID")
    private String tableId;

    /**
     * 表格列ID
     */
    @Schema(description = "表格列ID")
    private String schemaId;

    @Override
    public SysRoleTableSchemaExcel toExcel() {
        SysRoleTableSchemaMapper mapper = SysRoleTableSchemaMapper.mapper;
        return mapper.toExcel(this);
    }
}
