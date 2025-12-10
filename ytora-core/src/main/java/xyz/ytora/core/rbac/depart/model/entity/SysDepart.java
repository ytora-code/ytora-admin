package xyz.ytora.core.rbac.depart.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseEntity;
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
}
