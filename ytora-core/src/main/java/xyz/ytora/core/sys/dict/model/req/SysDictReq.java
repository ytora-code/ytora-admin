package xyz.ytora.core.sys.dict.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.sys.dict.model.SysDictMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDict;

/**
 * created by YT on 2026/1/6 13:07:14
 * <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典请求数据")
public class SysDictReq extends BaseReq<SysDict> {

    /**
     * 父字典ID
     */
    private String pid;

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
    private Integer index;

    /**
     * 1-字典/2-字典项
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    @Override
    public SysDict toEntity() {
        return SysDictMapper.mapper.toEntity(this);
    }
}
