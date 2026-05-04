package xyz.ytora.core.rbac.datascope.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.data.SysRoleDataScopeGroupData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 角色-数据范围分组关系表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_data_scope_group", idType = IdType.SNOWFLAKE, comment = "角色-数据范围分组关系表")
public class SysRoleDataScopeGroup extends BaseEntity<SysRoleDataScopeGroup> {

    /**
     * 角色ID
     */
    @Column(comment = "角色ID", notNull = true)
    private String roleId;

    /**
     * 所属的资源ID
     */
    @Column(comment = "所属的资源ID", notNull = true)
    private String permissionId;

    /**
     * 数据范围分组ID
     */
    @Column(comment = "数据范围分组ID", notNull = true)
    private String groupId;

    @Override
    public SysRoleDataScopeGroupData toData() {
        SysRoleDataScopeGroupMapper mapper = SysRoleDataScopeGroupMapper.mapper;
        return mapper.toData(this);
    }
}
