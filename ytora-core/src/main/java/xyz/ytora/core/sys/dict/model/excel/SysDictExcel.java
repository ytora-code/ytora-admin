package xyz.ytora.core.sys.dict.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.core.sys.dict.model.SysDictMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.ytool.anno.Index;
import xyz.ytora.ytool.document.excel.Excel;

/**
 * created by YT on 2026/1/12 18:19:59
 * <br/>
 * 系统字典
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel(fileName = "系统字典")
public class SysDictExcel extends BaseExcel<SysDict> {

    /**
     * 字典名称
     */
    @Index(1)
    @Excel("字典名称")
    private String dictName;

    /**
     * 字典编码
     */
    @Index(2)
    @Excel("字典编码")
    private String dictCode;

    @Override
    public SysDict toEntity() {
        return SysDictMapper.mapper.toEntity(this);
    }
}
