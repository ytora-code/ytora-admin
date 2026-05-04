package xyz.ytora.core.rbac.depart.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 用户-部门关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user_depart", idType = IdType.SNOWFLAKE, comment = "用户-部门关系表")
public class SysUserDepart extends BaseEntity<SysUserDepart> {
    /**
     * 用户ID
     */
    @Column(comment = "用户ID", notNull = true, type = ColumnType.INT8)
    private String userId;

    /**
     * 部门ID
     */
    @Column(comment = "部门ID", notNull = true, type = ColumnType.INT8)
    private String departId;

    @Override
    public BaseData<SysUserDepart> toData() {
        return null;
    }
}
