package xyz.ytora.base.mvc;

import lombok.Data;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.enums.ColumnType;
import xyz.ytora.ytool.anno.Index;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 响应数据通用类型
 */
@Data
public abstract class BaseResp<T extends BaseEntity<T>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Index(0)
    private String id;

    /**
     * 创建人
     */
    @Index(1)
    private String createBy;

    /**
     * 创建时间
     */
    @Index(2)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Index(3)
    private String updateBy;

    /**
     * 更新时间
     */
    @Index(4)
    private LocalDateTime updateTime;

    /**
     * 创建者所属部门
     */
    @Index(5)
    private String departCode;

    /**
     * 数据备注
     */
    @Index(6)
    private String remark;

    /**
     * 数据状态
     */
    @Index(7)
    private String status;
}
