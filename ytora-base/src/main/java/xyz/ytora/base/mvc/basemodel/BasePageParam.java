package xyz.ytora.base.mvc.basemodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.sqlux.orm.AbsEntity;

/**
 * 分页参数的基类
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePageParam<T extends AbsEntity> extends BaseParam<T> {

    @Schema(description = "页码")
    private Integer pageNo;

    @Schema(description = "页尺寸")
    private Integer pageSize;

}
