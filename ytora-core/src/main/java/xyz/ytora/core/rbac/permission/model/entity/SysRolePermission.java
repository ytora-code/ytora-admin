package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 角色-资源关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_permission", idType = IdType.SNOWFLAKE, comment = "角色-资源关系表")
public class SysRolePermission extends BaseEntity<SysRolePermission> {
    /**
     * 角色ID
     */
    @Column(comment = "角色ID", notNull = true, type = ColumnType.INT8)
    private String roleId;

    /**
     * 资源ID
     */
    @Column(comment = "资源ID", notNull = true, type = ColumnType.INT8)
    private String permissionId;

    @Override
    public BaseData<SysRolePermission> toData() {
        return null;
    }
}
