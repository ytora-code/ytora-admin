package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.permission.model.SysRoleTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.data.SysRoleTableSchemaData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 角色-表字段关系表
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_table_schema", idType = IdType.SNOWFLAKE, comment = "角色-表字段关系表")
public class SysRoleTableSchema extends BaseEntity<SysRoleTableSchema> {

    /**
     * 角色ID
     */
    @Column(comment = "角色ID", notNull = true)
    private String roleId;

    /**
     * 表格的资源ID
     */
    @Column(comment = "表格的资源ID")
    private String tableId;

    /**
     * 表格列ID
     */
    @Column(comment = "表格列ID")
    private String schemaId;

    @Override
    public SysRoleTableSchemaData toData() {
        SysRoleTableSchemaMapper mapper = SysRoleTableSchemaMapper.mapper;
        return mapper.toData(this);
    }
}
