package xyz.ytora.core.sys.dict.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.dict.model.SysDictMapper;
import xyz.ytora.core.sys.dict.model.data.SysDictData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 字典表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_dict", idType = IdType.SNOWFLAKE, comment = "字典表")
public class SysDict extends BaseEntity<SysDict> {

    /**
     * 字典名称
     */
    @Column(comment = "字典名称")
    private String dictName;

    /**
     * 字典编码
     */
    @Column(comment = "字典编码", notNull = true)
    private String dictCode;

    /**
     * 字典项排序
     */
    @Column(comment = "字典项排序")
    private Integer index;

    @Override
    public SysDictData toData() {
        SysDictMapper mapper = SysDictMapper.mapper;
        return mapper.toData(this);
    }
}
