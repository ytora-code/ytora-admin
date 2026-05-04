package xyz.ytora.core.rbac.role.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.data.SysRoleData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 角色表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role", idType = IdType.SNOWFLAKE, comment = "角色表")
public class SysRole extends BaseEntity<SysRole> {

    /**
     * 角色名称
     */
    @Column(comment = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(comment = "角色编码", notNull = true)
    private String roleCode;

    @Override
    public SysRoleData toData() {
        SysRoleMapper mapper = SysRoleMapper.mapper;
        return mapper.toData(this);
    }
}
