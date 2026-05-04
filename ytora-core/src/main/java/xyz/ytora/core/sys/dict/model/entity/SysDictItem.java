package xyz.ytora.core.sys.dict.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.dict.model.SysDictItemMapper;
import xyz.ytora.core.sys.dict.model.data.SysDictItemData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 字典项表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_dict_item", idType = IdType.SNOWFLAKE, comment = "字典项表")
public class SysDictItem extends BaseEntity<SysDictItem> {

    /**
     * 字典编码
     */
    @Column(comment = "字典编码", notNull = true)
    private String dictCode;

    /**
     * 字典项值
     */
    @Column(comment = "字典项值")
    private String itemValue;

    /**
     * 字典项文本
     */
    @Column(comment = "字典项文本")
    private String itemText;

    /**
     * 字典项排序
     */
    @Column(comment = "字典项排序")
    private Integer index;

    /**
     * 字典项颜色
     */
    @Column(comment = "字典项颜色")
    private String color;

    @Override
    public SysDictItemData toData() {
        SysDictItemMapper mapper = SysDictItemMapper.mapper;
        return mapper.toData(this);
    }
}
