package xyz.ytora.core.rbac.permission.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.permission.model.SysRoleFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleFormSchema;

import java.util.List;

/**
 * 角色-表单项关系请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@Schema(description = "角色-表单项关系表请求数据")
public class SysRoleFormSchemaParam {

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
     * 表单的资源ID
     */
    @Schema(description = "表单的资源ID")
    private String formId;

    /**
     * 最新的ID数组
     */
    @Schema(description = "最新的ID数组")
    private List<String> ids;
}
