package xyz.ytora.core.sys.dict.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.dict.model.SysDictMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("字典列表")
public class SysDictExcel extends BaseExcel<SysDict> {

    /**
     * 字典名称
     */
    @Excel("字典名称")
    private String dictName;

    /**
     * 字典编码
     */
    @Excel("字典编码")
    private String dictCode;

    /**
     * 字典项排序
     */
    @Excel("字典项排序")
    private Integer index;

    @Override
    public SysDict toEntity() {
        SysDictMapper mapper = SysDictMapper.mapper;
        return mapper.toEntity(this);
    }
}
