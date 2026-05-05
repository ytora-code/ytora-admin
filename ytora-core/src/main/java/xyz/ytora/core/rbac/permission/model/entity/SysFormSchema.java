package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.permission.model.SysFormSchemaMapper;
import xyz.ytora.core.rbac.permission.model.data.SysFormSchemaData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 表格列结构
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_form_schema", idType = IdType.SNOWFLAKE, comment = "表格列结构")
public class SysFormSchema extends BaseEntity<SysFormSchema> {

    /**
     * table资源ID
     */
    @Column(comment = "table资源ID", notNull = true)
    private Long permissionId;

    /**
     * 表单项类型
     */
    @Column(comment = "表单项类型")
    private String type;

    /**
     * 表单项标题宽度
     */
    @Column(comment = "表单项标题宽度")
    private String label;

    /**
     * 表单项标题位置
     */
    @Column(comment = "表单项标题位置")
    private String labelPosition;

    /**
     * 表单项标题宽度
     */
    @Column(comment = "表单项标题宽度")
    private Double labelWidth;

    /**
     * 表单项尺寸
     */
    @Column(comment = "表单项尺寸")
    private String size;

    /**
     * 输入内容提示
     */
    @Column(comment = "输入内容提示")
    private String placeholder;

    /**
     * 数据key
     */
    @Column(comment = "数据key")
    private String key;

    /**
     * 字典code
     */
    @Column(comment = "字典code")
    private String dictCode;

    /**
     * 是否隐藏
     */
    @Column(comment = "是否隐藏")
    private Boolean hidden;

    /**
     * 是否禁用
     */
    @Column(comment = "是否禁用")
    private Boolean disabled;

    /**
     * 默认值
     */
    @Column(comment = "默认值")
    private String defaultValue;

    /**
     * attr
     */
    @Column(comment = "attr")
    private Object attr;

    @Override
    public SysFormSchemaData toData() {
        SysFormSchemaMapper mapper = SysFormSchemaMapper.mapper;
        return mapper.toData(this);
    }
}
