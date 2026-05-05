package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.permission.model.SysTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.data.SysTableSchemaData;
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
@Table(value = "sys_table_schema", idType = IdType.SNOWFLAKE, comment = "表格列结构")
public class SysTableSchema extends BaseEntity<SysTableSchema> {

    /**
     * table资源ID
     */
    @Column(comment = "table资源ID")
    private String permissionId;

    /**
     * 单元格类型
     */
    @Column(comment = "单元格类型")
    private String type;

    /**
     * 数据key
     */
    @Column(comment = "数据key")
    private String key;

    /**
     * 单元格表头标题
     */
    @Column(comment = "单元格表头标题")
    private String title;

    /**
     * 单元格宽度
     */
    @Column(comment = "单元格宽度")
    private Integer width;

    /**
     * 单元格内容的align
     */
    @Column(comment = "单元格内容的align")
    private String align;

    /**
     * 单元格固定位置：左/右
     */
    @Column(comment = "单元格固定位置：左/右")
    private String fixed;

    /**
     * 文字内容溢出时，是否省略
     */
    @Column(comment = "文字内容溢出时，是否省略")
    private String ellipsis;

    /**
     * 格式化文字
     */
    @Column(comment = "格式化文字")
    private String formatter;

    /**
     * 其他属性
     */
    @Column(comment = "其他属性")
    private Object attr;

    @Override
    public SysTableSchemaData toData() {
        SysTableSchemaMapper mapper = SysTableSchemaMapper.mapper;
        return mapper.toData(this);
    }
}
