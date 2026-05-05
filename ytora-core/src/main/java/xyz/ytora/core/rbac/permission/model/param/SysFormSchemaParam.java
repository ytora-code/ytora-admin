package xyz.ytora.core.rbac.permission.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.permission.model.SysFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysFormSchema;

/**
 * 表格列结构请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "表格列结构请求数据")
public class SysFormSchemaParam extends BaseParam<SysFormSchema> {

    /**
     * table资源ID
     */
    @Schema(description = "table资源ID")
    private Long permissionId;

    /**
     * 表单项类型
     */
    @Schema(description = "表单项类型")
    private String type;

    /**
     * 表单项标题宽度
     */
    @Schema(description = "表单项标题宽度")
    private String label;

    /**
     * 表单项标题位置
     */
    @Schema(description = "表单项标题位置")
    private String labelPosition;

    /**
     * 表单项标题宽度
     */
    @Schema(description = "表单项标题宽度")
    private Double labelWidth;

    /**
     * 表单项尺寸
     */
    @Schema(description = "表单项尺寸")
    private String size;

    /**
     * 输入内容提示
     */
    @Schema(description = "输入内容提示")
    private String placeholder;

    /**
     * 数据key
     */
    @Schema(description = "数据key")
    private String key;

    /**
     * 字典code
     */
    @Schema(description = "字典code")
    private String dictCode;

    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏")
    private Boolean hidden;

    /**
     * 是否禁用
     */
    @Schema(description = "是否禁用")
    private Boolean disabled;

    /**
     * 默认值
     */
    @Schema(description = "默认值")
    private String defaultValue;

    /**
     * attr
     */
    @Schema(description = "attr")
    private Object attr;

    @Override
    public SysFormSchema toEntity() {
        SysFormSchemaMapper mapper = SysFormSchemaMapper.mapper;
        return mapper.toEntity(this);
    }
}
