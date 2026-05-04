package xyz.ytora.core.sys.recyclebin.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.recyclebin.model.SysRecycleBinMapper;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import xyz.ytora.toolkit.document.excel.Excel;
import java.time.LocalDateTime;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("回收站列表")
public class SysRecycleBinExcel extends BaseExcel<SysRecycleBin> {

    /**
     * 删除人
     */
    @Excel("删除人")
    private String deletedBy;

    /**
     * 删除时间
     */
    @Excel("删除时间")
    private LocalDateTime deletedTime;

    /**
     * 删除原因
     */
    @Excel("删除原因")
    private String deleteReason;

    /**
     * 原始表
     */
    @Excel("原始表")
    private String originalTable;

    /**
     * 原始数据id
     */
    @Excel("原始数据id")
    private String originalId;

    /**
     * 原始数据，JSON
     */
    @Excel("原始数据，JSON")
    private Object originalData;

    /**
     * redo,还原SQL
     */
    @Excel("redo,还原SQL")
    private String restoreSql;

    @Override
    public SysRecycleBin toEntity() {
        SysRecycleBinMapper mapper = SysRecycleBinMapper.mapper;
        return mapper.toEntity(this);
    }
}
