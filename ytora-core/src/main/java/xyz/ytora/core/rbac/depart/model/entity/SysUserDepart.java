package xyz.ytora.core.rbac.depart.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 用户-部门关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user_depart", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "用户-部门关系表")
public class SysUserDepart extends BaseEntity<SysUserDepart> {
    /**
     * 用户ID
     */
    @Index(1)
    @Column(comment = "用户ID", notNull = true)
    private String userId;

    /**
     * 部门ID
     */
    @Index(2)
    @Column(comment = "部门ID", notNull = true)
    private String departId;

    @Override
    public BaseResp<SysUserDepart> toResp() {
        return null;
    }
}
