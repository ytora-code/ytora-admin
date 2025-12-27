package xyz.ytora.base.mvc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 请求接受类通用类型
 */
@Data
public abstract class BaseReq<T extends BaseEntity<T>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private String id;

    /**
     * REQ类转为实体类
     */
    public abstract T toEntity();
}
