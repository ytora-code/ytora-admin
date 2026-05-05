package xyz.ytora.core.sys.icon.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.icon.model.SysIconMapper;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;
import xyz.ytora.core.sys.icon.model.excel.SysIconExcel;
import xyz.ytora.sqlux.core.anno.Column;

/**
 * 系统图标库响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统图标库响应数据")
public class SysIconData extends BaseData<SysIcon> {

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

    /**
     * 宽度
     */
    @Schema(description = "宽度")
    private Integer width;

    /**
     * 高度
     */
    @Schema(description = "高度")
    private Integer height;

    /**
     * 图标SVG主体数据
     */
    @Schema(description = "图标SVG主体数据")
    private String body;

    @Override
    public SysIconExcel toExcel() {
        SysIconMapper mapper = SysIconMapper.mapper;
        return mapper.toExcel(this);
    }
}
