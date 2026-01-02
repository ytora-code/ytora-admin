package xyz.ytora.core.rbac.depart.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.depart.model.SysDepartMapper;
import xyz.ytora.core.rbac.depart.model.resp.SysDepartResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 部门表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_depart", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "部门表")
public class SysDepart extends BaseEntity<SysDepart> {
    /**
     * 上级部门id
     */
    @Index(1)
    @Column(comment = "上级部门id", notNull = true)
    private String pid;

    /**
     * 部门名称
     */
    @Index(2)
    @Column(comment = "部门名称")
    private String departName;

    /**
     * 部门编码
     */
    @Index(3)
    @Column(comment = "部门编码", notNull = true)
    private String departCode;

    /**
     * 部门类型
     */
    @Index(3)
    @Column(comment = "部门类型")
    private String type;

    /**
     * 部门联系人username
     */
    @Index(4)
    @Column(comment = "部门联系人username")
    private String contactUserName;

    @Override
    public SysDepartResp toResp() {
        SysDepartMapper mapper = SysDepartMapper.mapper;
        return mapper.entityToResp(this);
    }
}
