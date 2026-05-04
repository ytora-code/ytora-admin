package xyz.ytora.core.rbac.datascope.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeGroupData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 数据范围组
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_data_scope_group", idType = IdType.SNOWFLAKE, comment = "数据范围组")
public class SysDataScopeGroup extends BaseEntity<SysDataScopeGroup> {

    /**
     * 所属的资源ID
     */
    @Column(comment = "所属的资源ID", notNull = true)
    private String permissionId;

    /**
     * 分组名称
     */
    @Column(comment = "分组名称", notNull = true)
    private String name;

    /**
     * 分组编码
     */
    @Column(comment = "分组编码", notNull = true)
    private String code;

    @Override
    public SysDataScopeGroupData toData() {
        SysDataScopeGroupMapper mapper = SysDataScopeGroupMapper.mapper;
        return mapper.toData(this);
    }
}
