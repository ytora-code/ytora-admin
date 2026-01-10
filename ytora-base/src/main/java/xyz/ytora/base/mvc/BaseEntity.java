package xyz.ytora.base.mvc;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.sql4J.autofill.*;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.enums.ColumnType;
import xyz.ytora.sql4j.orm.Entity;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDateTime;

/**
 * 实体类通用类型
 * @param <T> 实体类型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<T extends BaseEntity<T>> extends Entity<T> {

    /**
     * 创建人
     */
    @Index(1)
    @Column(comment = "创建人", type = ColumnType.VARCHAR16, fill = CreateUserFiller.class)
    protected String createBy;

    /**
     * 创建时间
     */
    @Index(2)
    @Column(comment = "创建时间", fill = CreateTimeFiller.class)
    protected LocalDateTime createTime;

    /**
     * 更新人
     */
    @Index(3)
    @Column(comment = "更新人", type = ColumnType.VARCHAR16, fill = UpdateUserFiller.class)
    protected String updateBy;

    /**
     * 更新时间
     */
    @Index(4)
    @Column(comment = "更新时间", fill = UpdateTimeFiller.class)
    protected LocalDateTime updateTime;

    /**
     * 创建者所属部门
     */
    @Index(5)
    @Column(comment = "创建者所属部门", fill = DepartCodeFiller.class)
    protected String departCode;

    /**
     * 数据备注
     */
    @Index(6)
    @Column(comment = "数据备注")
    protected String remark;

    /**
     * 数据状态
     */
    @Index(7)
    @Column(comment = "数据状态")
    protected String status;

    /**
     * 实体类转为RESP类
     */
    public abstract BaseResp<T> toResp();
}
