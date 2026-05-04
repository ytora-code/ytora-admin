package xyz.ytora.base.mvc.basemodel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.sqlux.filler.*;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.enums.FillType;
import xyz.ytora.sqlux.orm.AbsEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类的基类
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<T extends AbsEntity> extends AbsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 数据创建人
     */
    @Column(value = "create_by", comment = "数据创建人", fillOn = FillType.INSERT, filler = CreateByFiller.class)
    private String createBy;

    /**
     * 数据创建日期
     */
    @Column(value = "create_time", comment = "数据创建日期", fillOn = FillType.INSERT, filler = CreateTimeFiller.class)
    private LocalDateTime createTime;

    /**
     * 数据修改人
     */
    @Column(value = "update_by", comment = "数据修改人", fillOn = FillType.INSERT_UPDATE, filler = UpdateByFiller.class)
    private String updateBy;

    /**
     * 数据修改日期
     */
    @Column(value = "update_time", comment = "数据修改日期", fillOn = FillType.INSERT_UPDATE, filler = UpdateTimeFiller.class)
    private LocalDateTime updateTime;

    /**
     * 数据创建人所属部门
     */
    @Column(value = "depart_code", comment = "数据创建人所属部门", fillOn = FillType.INSERT, filler = DepartCodeFiller.class)
    private String departCode;

    /**
     * 数据备注
     */
    @Column(value = "remark", comment = "数据备注")
    private String remark;

    /**
     * 实体类转为RESP类
     */
    public abstract BaseData<T> toData();

}
