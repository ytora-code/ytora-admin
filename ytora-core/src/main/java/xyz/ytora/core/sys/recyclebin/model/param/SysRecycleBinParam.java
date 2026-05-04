package xyz.ytora.core.sys.recyclebin.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.recyclebin.model.SysRecycleBinMapper;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import java.util.Date;

/**
 * 回收站请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "回收站请求数据")
public class SysRecycleBinParam extends BaseParam<SysRecycleBin> {

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
    public SysRecycleBin toEntity() {
        SysRecycleBinMapper mapper = SysRecycleBinMapper.mapper;
        return mapper.toEntity(this);
    }
}
