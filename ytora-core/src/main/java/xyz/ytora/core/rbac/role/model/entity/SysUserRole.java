package xyz.ytora.core.rbac.role.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseEntity;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 用户-角色关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user_role", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "用户-角色关系表")
public class SysUserRole extends BaseEntity<SysUserRole> {
    /**
     * 用户ID
     */
    @Index(1)
    @Column(comment = "用户ID", notNull = true)
    private String userId;

    /**
     * 角色ID
     */
    @Index(2)
    @Column(comment = "角色ID", notNull = true)
    private String roleId;
}
