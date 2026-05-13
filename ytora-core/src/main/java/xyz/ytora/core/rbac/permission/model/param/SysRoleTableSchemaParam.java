package xyz.ytora.core.rbac.permission.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.permission.model.SysRoleTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleTableSchema;

import java.util.List;

/**
 * 角色-表字段关系请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@Schema(description = "角色-表字段关系表请求数据")
public class SysRoleTableSchemaParam {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 所属的资源ID
     */
    @Schema(description = "所属的资源ID")
    private String permissionId;

    /**
     * 表格的资源ID
     */
    @Schema(description = "表格的资源ID")
    private String tableId;

    /**
     * 最新的ID数组
     */
    @Schema(description = "最新的ID数组")
    private List<String> ids;

}
