package xyz.ytora.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.orm.AbsEntity;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDateTime;

/**
 * 实体类通用类型
 * @param <T> 实体类型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<T> extends AbsEntity<T> {

    /**
     * 创建时间
     * JsonFormat:ObjectMapper在序列化（Java→JSON）和反序列化（JSON→Java）时生效
     * DateTimeFormat:Spring MVC使用的注解，用于告诉Spring MVC的ConversionService如何进行类型绑定，通常是将前端字符串日期参数转为后端的日期对象
     */
    /**
     * 创建人
     */
    @Index(1)
    @Column(comment = "创建人", columnType = "VARCHAR(16)")
    private String createBy;

    /**
     * 创建时间
     */
    @Index(2)
    @Column(comment = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Index(3)
    @Column(comment = "更新人", columnType = "VARCHAR(16)")
    private String updateBy;

    /**
     * 更新时间
     */
    @Index(4)
    @Column(comment = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 创建者所属部门
     */
    @Index(5)
    @Column(comment = "创建者所属部门")
    private String departCode;

    /**
     * 数据备注
     */
    @Index(6)
    @Column(comment = "数据备注")
    private String remark;

    /**
     * 数据状态
     */
    @Index(7)
    @Column(comment = "数据状态")
    private String status;

    /**
     * 实体类转为RESP类
     */
//    public abstract BaseResp<T> toResp();
}
