package xyz.ytora.base.mvc;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应数据通用类型
 */
@Data
public abstract class BaseResp<T extends BaseEntity<T>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
}
