package xyz.ytora.core.rbac.permission.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.toolkit.tree.ITree;

import java.util.List;

@Data
@Schema(description = "角色-资源关系")
public class SysRolePermissionTree implements ITree<SysRolePermissionTree> {

    @Schema(description = "id")
    private String id;

    /**
     * 父资源id
     */
    @Schema(description = "父资源id")
    private String pid;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称")
    private String permissionName;

    /**
     * 资源类型
     */
    @Schema(description = "资源类型")
    private Integer permissionType;

    /**
     * 子元数集合
     */
    private List<SysRolePermissionTree> children;

    /**
     * 是否有子节点
     */
    private Boolean hasChildren = false;
}
