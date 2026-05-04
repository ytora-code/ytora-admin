package xyz.ytora.core.sys.dict.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.dict.model.SysDictItemMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;
import xyz.ytora.core.sys.dict.model.excel.SysDictItemExcel;

/**
 * 字典项响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典项表响应数据")
public class SysDictItemData extends BaseData<SysDictItem> {

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    private String itemValue;

    /**
     * 字典项文本
     */
    @Schema(description = "字典项文本")
    private String itemText;

    /**
     * 字典项排序
     */
    @Schema(description = "字典项排序")
    private Integer index;

    /**
     * 字典项颜色
     */
    @Schema(description = "字典项颜色")
    private String color;

    @Override
    public SysDictItemExcel toExcel() {
        SysDictItemMapper mapper = SysDictItemMapper.mapper;
        return mapper.toExcel(this);
    }
}
