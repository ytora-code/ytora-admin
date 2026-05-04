package xyz.ytora.core.rbac.datascope.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 角色-数据范围分组关系请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@Schema(description = "角色-数据范围分组关系表请求数据")
public class SysRoleDataScopeGroupParam {

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
     * 数据范围分组ID数组
     */
    @Schema(description = "数据范围分组ID数组")
    private List<String> groupIds;

}
