package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.ColumnType;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 角色-资源关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_permission", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "角色-资源关系表")
public class SysRolePermission extends BaseEntity<SysRolePermission> {
    /**
     * 角色ID
     */
    @Index(1)
    @Column(comment = "角色ID", notNull = true, type = ColumnType.INT8)
    private String roleId;

    /**
     * 资源ID
     */
    @Index(2)
    @Column(comment = "资源ID", notNull = true, type = ColumnType.INT8)
    private String permissionId;

    @Override
    public BaseResp<SysRolePermission> toResp() {
        return null;
    }
}
