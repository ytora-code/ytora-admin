package xyz.ytora.base.dict.support;

import lombok.Data;
import xyz.ytora.base.model.BaseEntity;

/**
 * created by YT on 2025/12/10 17:45:48
 * <br/>
 */
@Data
public class SysDict extends BaseEntity<SysDict> {
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码，唯一
     */
    private String dictCode;

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
    private Integer sortOrder;

    /**
     * 0-字典/1-字典项
     */
    private Integer type;
}
