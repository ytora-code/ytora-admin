package xyz.ytora.core.rbac.permission.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.ytool.tree.ITree;

import java.util.List;

@Data
@Schema(description = "角色-资源关系")
public class SysRolePermissionTreeResp implements ITree<SysRolePermissionTreeResp> {

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
    private List<SysRolePermissionTreeResp> children;

    /**
     * 是否有子节点
     */
    private Boolean hasChildren = false;

    @Override
    public String getKey() {
        return this.permissionName;
    }

    @Override
    public void hasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}
