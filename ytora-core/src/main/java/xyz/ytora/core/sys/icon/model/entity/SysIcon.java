package xyz.ytora.core.sys.icon.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.icon.model.SysIconMapper;
import xyz.ytora.core.sys.icon.model.data.SysIconData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 系统图标库
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_icon", idType = IdType.SNOWFLAKE, comment = "系统图标库")
public class SysIcon extends BaseEntity<SysIcon> {

    /**
     * 图标code
     */
    @Column(comment = "图标code")
    private String code;

    /**
     * 图标名称
     */
    @Column(comment = "图标名称")
    private String name;

    /**
     * 图标库类型
     */
    @Column(comment = "图标库类型")
    private String type;

    /**
     * 宽度
     */
    @Column(comment = "宽度")
    private Integer width;

    /**
     * 高度
     */
    @Column(comment = "高度")
    private Integer height;

    /**
     * 图标SVG主体数据
     */
    @Column(comment = "图标SVG主体数据")
    private String body;

    @Override
    public SysIconData toData() {
        SysIconMapper mapper = SysIconMapper.mapper;
        return mapper.toData(this);
    }
}
