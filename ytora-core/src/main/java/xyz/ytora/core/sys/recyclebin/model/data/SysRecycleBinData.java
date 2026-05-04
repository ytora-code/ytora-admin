package xyz.ytora.core.sys.recyclebin.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.recyclebin.model.SysRecycleBinMapper;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import xyz.ytora.core.sys.recyclebin.model.excel.SysRecycleBinExcel;
import java.util.Date;

/**
 * 回收站响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "回收站响应数据")
public class SysRecycleBinData extends BaseData<SysRecycleBin> {

    /**
     * 删除人
     */
    @Schema(description = "删除人")
    private String deletedBy;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private Date deletedTime;

    /**
     * 删除原因
     */
    @Schema(description = "删除原因")
    private String deleteReason;

    /**
     * 原始表
     */
    @Schema(description = "原始表")
    private String originalTable;

    /**
     * 原始数据id
     */
    @Schema(description = "原始数据id")
    private String originalId;

    /**
     * 原始数据，JSON
     */
    @Schema(description = "原始数据，JSON")
    private Object originalData;

    /**
     * redo,还原SQL
     */
    @Schema(description = "redo,还原SQL")
    private String restoreSql;

    @Override
    public SysRecycleBinExcel toExcel() {
        SysRecycleBinMapper mapper = SysRecycleBinMapper.mapper;
        return mapper.toExcel(this);
    }
}
