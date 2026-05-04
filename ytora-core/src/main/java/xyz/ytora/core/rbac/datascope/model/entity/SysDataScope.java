package xyz.ytora.core.rbac.datascope.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 数据范围
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_data_scope", idType = IdType.SNOWFLAKE, comment = "数据范围")
public class SysDataScope extends BaseEntity<SysDataScope> {

    /**
     * 所属分组ID
     */
    @Column(comment = "所属分组ID", notNull = true)
    private String groupId;

    /**
     * 数据范围名称
     */
    @Column(comment = "数据范围名称", notNull = true)
    private String name;

    /**
     * 数据范围匹配的列
     */
    @Column(comment = "数据范围匹配的列")
    private String column;

    /**
     * 数据范围类型
     */
    @Column(comment = "数据范围类型", notNull = true)
    private String type;

    /**
     * 数据范围的规则
     */
    @Column(comment = "数据范围的规则")
    private String value;

    @Override
    public SysDataScopeData toData() {
        SysDataScopeMapper mapper = SysDataScopeMapper.mapper;
        return mapper.toData(this);
    }
}
