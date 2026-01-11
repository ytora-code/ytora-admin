package xyz.ytora.core.rbac.datarule.model.entity;

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
 * 角色-数据规则关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_data_rule", idType = IdType.SNOWFLAKE, backupOnDelete = true, createIfNotExist = true, comment = "角色-数据规则关系表")
public class SysRoleDataRule extends BaseEntity<SysRoleDataRule> {

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

    /**
     * 数据规则ID
     */
    @Index(3)
    @Column(comment = "数据规则ID", notNull = true, type = ColumnType.INT8)
    private String ruleId;

    @Override
    public BaseResp<SysRoleDataRule> toResp() {
        return null;
    }
}
