package xyz.ytora.core.rbac.permission.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色-资源关系")
public class SysRolePermissionDetail extends BaseData<SysPermission> {

    /**
     * 资源树
     */
    @Schema(description = "资源树")
    private List<SysRolePermissionTree> tree;

    /**
     * 角色拥有的所有资源ID
     */
    @Schema(description = "角色拥有的所有资源ID")
    private List<String> permissionIds;

    @Override
    public BaseExcel<SysPermission> toExcel() {
        return null;
    }
}
