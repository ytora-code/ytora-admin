package xyz.ytora.core.sys.dict.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.dict.model.SysDictItemMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;

/**
 * 字典项请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典项表请求数据")
public class SysDictItemParam extends BaseParam<SysDictItem> {

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
    public SysDictItem toEntity() {
        SysDictItemMapper mapper = SysDictItemMapper.mapper;
        return mapper.toEntity(this);
    }
}
