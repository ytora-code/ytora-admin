package xyz.ytora.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.sql4j.orm.AbsEntity;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 实体类通用类型
 * @param <T> 实体类型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<T> extends AbsEntity<T> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 创建时间
     * JsonFormat:ObjectMapper在序列化（Java→JSON）和反序列化（JSON→Java）时生效
     * DateTimeFormat:Spring MVC使用的注解，用于告诉Spring MVC的ConversionService如何进行类型绑定，通常是将前端字符串日期参数转为后端的日期对象
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建者所属部门
     */
    private String departCode;

    //按需添加
//    /** 乐观锁控制 */
//    private Integer version;

    /**
     * 数据备注
     */
    private String remark;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 实体类转为RESP类
     */
//    public abstract BaseResp<T> toResp();
}
