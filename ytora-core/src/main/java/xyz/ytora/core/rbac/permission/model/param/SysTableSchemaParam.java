package xyz.ytora.core.rbac.permission.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.permission.model.SysTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysTableSchema;
import xyz.ytora.sqlux.core.anno.Column;

/**
 * 表格列结构请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "表格列结构请求数据")
public class SysTableSchemaParam extends BaseParam<SysTableSchema> {

    /**
     * table资源ID
     */
    @Schema(description = "table资源ID")
    private String permissionId;

    /**
     * 表格code
     */
    @Schema(description = "表格code")
    private String tableCode;

    /**
     * 单元格类型
     */
    @Schema(description = "单元格类型")
    private String type;

    /**
     * 数据key
     */
    @Schema(description = "数据key")
    private String key;

    /**
     * 单元格表头标题
     */
    @Schema(description = "单元格表头标题")
    private String title;

    /**
     * 单元格宽度
     */
    @Schema(description = "单元格宽度")
    private Integer width;

    /**
     * 单元格内容的align
     */
    @Schema(description = "单元格内容的align")
    private String align;

    /**
     * 单元格固定位置：左/右
     */
    @Schema(description = "单元格固定位置：左/右")
    private String fixed;

    /**
     * 文字内容溢出时，是否省略
     */
    @Schema(description = "文字内容溢出时，是否省略")
    private String ellipsis;

    /**
     * 格式化文字
     */
    @Schema(description = "格式化文字")
    private String formatter;

    /**
     * 其他属性
     */
    @Schema(description = "其他属性")
    private Object attr;

    @Override
    public SysTableSchema toEntity() {
        SysTableSchemaMapper mapper = SysTableSchemaMapper.mapper;
        return mapper.toEntity(this);
    }
}
