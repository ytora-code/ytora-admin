package xyz.ytora.core.rbac.permission.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.permission.model.SysFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysFormSchema;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("表格列结构列表")
public class SysFormSchemaExcel extends BaseExcel<SysFormSchema> {

    /**
     * table资源ID
     */
    @Excel("table资源ID")
    private Long permissionId;

    /**
     * 表单项类型
     */
    @Excel("表单项类型")
    private String type;

    /**
     * 表单项标题宽度
     */
    @Excel("表单项标题宽度")
    private String label;

    /**
     * 表单项标题位置
     */
    @Excel("表单项标题位置")
    private String labelPosition;

    /**
     * 表单项标题宽度
     */
    @Excel("表单项标题宽度")
    private Double labelWidth;

    /**
     * 表单项尺寸
     */
    @Excel("表单项尺寸")
    private String size;

    /**
     * 输入内容提示
     */
    @Excel("输入内容提示")
    private String placeholder;

    /**
     * 数据key
     */
    @Excel("数据key")
    private String key;

    /**
     * 字典code
     */
    @Excel("字典code")
    private String dictCode;

    /**
     * 是否隐藏
     */
    @Excel("是否隐藏")
    private Boolean hidden;

    /**
     * 是否禁用
     */
    @Excel("是否禁用")
    private Boolean disabled;

    /**
     * 默认值
     */
    @Excel("默认值")
    private String defaultValue;

    /**
     * attr
     */
    @Excel("attr")
    private Object attr;

    @Override
    public SysFormSchema toEntity() {
        SysFormSchemaMapper mapper = SysFormSchemaMapper.mapper;
        return mapper.toEntity(this);
    }
}
