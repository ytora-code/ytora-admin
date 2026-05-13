package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.permission.model.SysRoleFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.data.SysRoleFormSchemaData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 角色-表单项关系表
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_role_form_schema", idType = IdType.SNOWFLAKE, comment = "角色-表单项关系表")
public class SysRoleFormSchema extends BaseEntity<SysRoleFormSchema> {

    /**
     * 角色ID
     */
    @Column(comment = "角色ID", notNull = true)
    private String roleId;

    /**
     * 表单的资源ID
     */
    @Column(comment = "表单的资源ID")
    private String formId;

    /**
     * 表单项ID
     */
    @Column(comment = "表单项ID")
    private String schemaId;

    @Override
    public SysRoleFormSchemaData toData() {
        SysRoleFormSchemaMapper mapper = SysRoleFormSchemaMapper.mapper;
        return mapper.toData(this);
    }
}
