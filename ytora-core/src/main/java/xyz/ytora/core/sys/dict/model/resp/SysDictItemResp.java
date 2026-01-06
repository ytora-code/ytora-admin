package xyz.ytora.core.sys.dict.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.dict.model.entity.SysDict;

/**
 * 系统字典
 */
@Data
public class SysDictItemResp {
    /**
     * 字典项值
     */
    private String dictItemValue;

    /**
     * 字典项值对应的显示文本
     */
    private String dictItemText;

    /**
     * 字典排序
     */
    private Integer index;

}
