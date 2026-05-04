package xyz.ytora.core.sys.recyclebin.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.recyclebin.model.SysRecycleBinMapper;
import xyz.ytora.core.sys.recyclebin.model.data.SysRecycleBinData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;
import xyz.ytora.sqlux.orm.type.Json;

import java.time.LocalDateTime;

/**
 * 回收站
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_recycle_bin", idType = IdType.SNOWFLAKE, comment = "回收站")
public class SysRecycleBin extends BaseEntity<SysRecycleBin> {

    /**
     * 删除人
     */
    @Column(comment = "删除人")
    private String deletedBy;

    /**
     * 删除时间
     */
    @Column(comment = "删除时间")
    private LocalDateTime deletedTime;

    /**
     * 删除原因
     */
    @Column(comment = "删除原因")
    private String deleteReason;

    /**
     * 原始表
     */
    @Column(comment = "原始表", notNull = true)
    private String originalTable;

    /**
     * 原始数据id
     */
    @Column(comment = "原始数据id", notNull = true)
    private String originalId;

    /**
     * 原始数据，JSON
     */
    @Column(comment = "原始数据，JSON", notNull = true)
    private Json originalData;

    /**
     * redo,还原SQL
     */
    @Column(comment = "redo,还原SQL")
    private String restoreSql;

    @Override
    public SysRecycleBinData toData() {
        SysRecycleBinMapper mapper = SysRecycleBinMapper.mapper;
        return mapper.toData(this);
    }
}
