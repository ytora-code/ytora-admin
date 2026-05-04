package xyz.ytora.core.rbac.role.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 用户-角色关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user_role", idType = IdType.SNOWFLAKE, comment = "用户-角色关系表")
public class SysUserRole extends BaseEntity<SysUserRole> {
    /**
     * 用户ID
     */
    @Column(comment = "用户ID", notNull = true, type = ColumnType.INT8)
    private String userId;

    /**
     * 角色ID
     */
    @Column(comment = "角色ID", notNull = true, type = ColumnType.INT8)
    private String roleId;

    @Override
    public BaseData<SysUserRole> toData() {
        return null;
    }
}
