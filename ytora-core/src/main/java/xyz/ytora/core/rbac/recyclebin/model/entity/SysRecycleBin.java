package xyz.ytora.core.rbac.recyclebin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.model.BaseEntity;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDateTime;


/**
 * 回收站
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_recycle_bin", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "回收站")
public class SysRecycleBin extends BaseEntity<SysRecycleBin> {
    /**
     * 删除人
     */
    @Index(1)
    @Column(comment = "删除人")
    private String deletedBy;

    /**
     * 删除时间
     */
    @Index(2)
    @Column(comment = "删除时间")
    private LocalDateTime deletedTime;

    /**
     * 删除原因
     */
    @Index(3)
    @Column(comment = "删除原因")
    private String deleteReason;

    /**
     * 原始表
     */
    @Index(4)
    @Column(comment = "原始表", notNull = true)
    private String originalTable;

    /**
     * 原始数据id
     */
    @Index(5)
    @Column(comment = "原始数据id", notNull = true)
    private String originalId;

    /**
     * 原始数据，JSON
     */
    @Index(6)
    @Column(comment = "原始数据，JSON", notNull = true)
    private String originalData;
}