package xyz.ytora.core.sys.dict.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.dict.model.resp.SysDictResp;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 系统字典
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_dict", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "系统字典")
public class SysDict extends BaseEntity<SysDict> {

    /**
     * 父字典ID
     */
    @Index(1)
    @Column(comment = "父字典ID", notNull = true)
    private String pid;

    /**
     * 字典名称
     */
    @Index(2)
    @Column(comment = "字典名称")
    private String dictName;

    /**
     * 字典编码
     */
    @Index(3)
    @Column(comment = "字典编码", notNull = true)
    private String dictCode;

    /**
     * 字典项值
     */
    @Index(4)
    @Column(comment = "字典项值")
    private String dictItemValue;

    /**
     * 字典项值对应的显示文本
     */
    @Index(5)
    @Column(comment = "字典项值对应的显示文本")
    private String dictItemText;

    /**
     * 字典排序
     */
    @Index(6)
    @Column(comment = "字典排序")
    private Integer index;

    /**
     * 0-字典/1-字典项
     */
    @Index(7)
    @Column(comment = "类型：0-字典/1-字典项", notNull = true)
    private Integer type;

    @Override
    public SysDictResp toResp() {
        return null;
    }
}
