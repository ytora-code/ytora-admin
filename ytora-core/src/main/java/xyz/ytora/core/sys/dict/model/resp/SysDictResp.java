package xyz.ytora.core.sys.dict.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.dict.model.entity.SysDict;

/**
 * 系统字典
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictResp extends BaseResp<SysDict> {
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码，唯一
     */
    private String dictCode;

    /**
     * 字典排序
     */
    private Integer index;

    @Override
    public BaseExcel<SysDict> toExcel() {
        return null;
    }
}
