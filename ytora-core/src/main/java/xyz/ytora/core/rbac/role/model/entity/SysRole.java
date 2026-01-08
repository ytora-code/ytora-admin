package xyz.ytora.core.rbac.role.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.resp.SysRoleResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 角色表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role", idType = IdType.SNOWFLAKE, backupOnDelete = true, createIfNotExist = true, comment = "角色表")
public class SysRole extends BaseEntity<SysRole> {

    /**
     * 角色名称
     */
    @Index(1)
    @Column(comment = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Index(2)
    @Column(comment = "角色编码", notNull = true)
    private String roleCode;

    @Override
    public SysRoleResp toResp() {
        SysRoleMapper mapper = SysRoleMapper.mapper;
        return mapper.toResp(this);
    }
}
