package xyz.ytora.core.online.dynamicapi.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiGroupMapper;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;

/**
 * 动态API接口分组请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "动态API接口分组表请求数据")
public class SysDynamicApiGroupParam extends BaseParam<SysDynamicApiGroup> {

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

    @Override
    public SysDynamicApiGroup toEntity() {
        SysDynamicApiGroupMapper mapper = SysDynamicApiGroupMapper.mapper;
        return mapper.toEntity(this);
    }
}
