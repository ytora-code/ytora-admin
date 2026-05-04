package xyz.ytora.base.mvc.basemodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.orm.AbsEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 前端参数模型的基类
 *
 * @author ytora 
 * @since 1.0
 */
@Data
public abstract class BaseParam<T extends AbsEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "数据主键")
    protected String id;

    /**
     * 数据备注
     */
    @Column(value = "remark", comment = "数据备注")
    private String remark;

    /**
     * PARAM类转为实体类
     */
    public abstract T toEntity();

}
