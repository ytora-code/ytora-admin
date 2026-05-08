package xyz.ytora.core.online.dynamicapi.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiGroupMapper;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiGroupExcel;
import xyz.ytora.toolkit.tree.ITree;

import java.util.List;

/**
 * 动态API接口分组响应数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "动态API接口分组表响应数据")
public class SysDynamicApiGroupData extends BaseData<SysDynamicApiGroup> implements ITree<SysDynamicApiGroupData> {

    /**
     * 上级分组ID
     */
    @Schema(description = "上级分组ID")
    private String pid;

    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    private String name;

    /**
     * 子分组
     */
    @Schema(description = "子分组")
    private List<SysDynamicApiGroupData> children;

    @Override
    public SysDynamicApiGroupExcel toExcel() {
        SysDynamicApiGroupMapper mapper = SysDynamicApiGroupMapper.mapper;
        return mapper.toExcel(this);
    }
}
