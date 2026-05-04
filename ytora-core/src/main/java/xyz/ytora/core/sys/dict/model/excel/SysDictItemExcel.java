package xyz.ytora.core.sys.dict.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.dict.model.SysDictItemMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("字典项列表")
public class SysDictItemExcel extends BaseExcel<SysDictItem> {

    /**
     * 字典编码
     */
    @Excel("字典编码")
    private String dictCode;

    /**
     * 字典项值
     */
    @Excel("字典项值")
    private String itemValue;

    /**
     * 字典项文本
     */
    @Excel("字典项文本")
    private String itemText;

    /**
     * 字典项排序
     */
    @Excel("字典项排序")
    private Integer index;

    /**
     * 字典项颜色
     */
    @Excel("字典项颜色")
    private String color;

    @Override
    public SysDictItem toEntity() {
        SysDictItemMapper mapper = SysDictItemMapper.mapper;
        return mapper.toEntity(this);
    }
}
