package xyz.ytora.core.rbac.datascope.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.data.SysRoleDataScopeData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 角色-数据范围关系表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_data_scope", idType = IdType.SNOWFLAKE, comment = "角色-数据范围关系表")
public class SysRoleDataScope extends BaseEntity<SysRoleDataScope> {

    /**
     * 角色ID
     */
    @Column(comment = "角色ID", notNull = true)
    private String roleId;

    /**
     * 数据分组ID
     */
    @Column(comment = "数据分组ID", notNull = true)
    private String groupId;

    /**
     * 数据范围ID
     */
    @Column(comment = "数据范围ID", notNull = true)
    private String scopeId;

    @Override
    public SysRoleDataScopeData toData() {
        SysRoleDataScopeMapper mapper = SysRoleDataScopeMapper.mapper;
        return mapper.toData(this);
    }
}
