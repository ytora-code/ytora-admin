package xyz.ytora.core.sys.dict.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.file.model.entity.SysFile;

/**
 * 系统字典
 */
@Data
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

}
