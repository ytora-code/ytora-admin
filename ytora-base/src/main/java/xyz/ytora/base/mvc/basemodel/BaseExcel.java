package xyz.ytora.base.mvc.basemodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sqlux.orm.AbsEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * EXCEL模型数据的基类
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Schema(description = "EXCEL模型")
public abstract class BaseExcel<T extends AbsEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * EXCEL类转为实体类
     */
    public abstract T toEntity();

}
