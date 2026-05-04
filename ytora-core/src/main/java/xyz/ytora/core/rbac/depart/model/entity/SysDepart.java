package xyz.ytora.core.rbac.depart.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.depart.model.SysDepartMapper;
import xyz.ytora.core.rbac.depart.model.data.SysDepartData;
import xyz.ytora.core.sys.dict.Dict;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 部门表
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_depart", idType = IdType.SNOWFLAKE, comment = "部门表")
public class SysDepart extends BaseEntity<SysDepart> {

    /**
     * 上级部门id
     */
    @Column(comment = "上级部门id", notNull = true, type = ColumnType.INT8)
    private String pid;

    /**
     * 部门名称
     */
    @Column(comment = "部门名称")
    private String departName;

    /**
     * 部门编码
     */
    @Column(comment = "部门编码", notNull = true)
    private String departCode;

    /**
     * 部门类型
     */
    @Column(comment = "部门类型")
    private String type;

    /**
     * 部门联系人ID
     */
    @Column(comment = "部门联系人ID")
    private String contactId;

    @Override
    public SysDepartData toData() {
        SysDepartMapper mapper = SysDepartMapper.mapper;
        return mapper.toData(this);
    }

}
