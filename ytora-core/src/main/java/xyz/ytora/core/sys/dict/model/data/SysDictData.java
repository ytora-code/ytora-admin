package xyz.ytora.core.sys.dict.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.dict.model.SysDictMapper;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.model.excel.SysDictExcel;

/**
 * 字典响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典表响应数据")
public class SysDictData extends BaseData<SysDict> {

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    private String dictCode;

    /**
     * 字典项排序
     */
    @Schema(description = "字典项排序")
    private Integer index;

    @Override
    public SysDictExcel toExcel() {
        SysDictMapper mapper = SysDictMapper.mapper;
        return mapper.toExcel(this);
    }
}
