package xyz.ytora.core.rbac.permission.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.permission.model.SysTableSchemaMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysTableSchema;
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
public class SysTableSchemaExcel extends BaseExcel<SysTableSchema> {

    /**
     * 单元格类型
     */
    @Excel("单元格类型")
    private String type;

    /**
     * 数据key
     */
    @Excel("数据key")
    private String key;

    /**
     * 单元格表头标题
     */
    @Excel("单元格表头标题")
    private String title;

    /**
     * 单元格宽度
     */
    @Excel("单元格宽度")
    private Double width;

    /**
     * 单元格内容的align
     */
    @Excel("单元格内容的align")
    private String align;

    /**
     * 单元格固定位置：左/右
     */
    @Excel("单元格固定位置：左/右")
    private String fixed;

    /**
     * 文字内容溢出时，是否省略
     */
    @Excel("文字内容溢出时，是否省略")
    private String ellipsis;

    /**
     * 格式化文字
     */
    @Excel("格式化文字")
    private String formatter;

    /**
     * 其他属性
     */
    @Excel("其他属性")
    private Object attr;

    @Override
    public SysTableSchema toEntity() {
        SysTableSchemaMapper mapper = SysTableSchemaMapper.mapper;
        return mapper.toEntity(this);
    }
}
