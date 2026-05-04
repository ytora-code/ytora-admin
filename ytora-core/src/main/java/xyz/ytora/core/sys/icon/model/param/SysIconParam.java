package xyz.ytora.core.sys.icon.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.sys.icon.model.SysIconMapper;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;

/**
 * 系统图标库请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统图标库请求数据")
public class SysIconParam extends BaseParam<SysIcon> {

    /**
     * 图标code
     */
    @Schema(description = "图标code")
    private String code;

    /**
     * 图标名称
     */
    @Schema(description = "图标名称")
    private String name;

    /**
     * 图标库类型
     */
    @Schema(description = "图标库类型")
    private String type;

    @Override
    public SysIcon toEntity() {
        SysIconMapper mapper = SysIconMapper.mapper;
        return mapper.toEntity(this);
    }
}
