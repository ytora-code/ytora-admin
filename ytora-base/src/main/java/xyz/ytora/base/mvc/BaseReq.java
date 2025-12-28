package xyz.ytora.base.mvc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;

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
     * 数据备注
     */
    @Schema(description = "数据备注")
    private String remark;

    /**
     * 数据状态
     */
    @Schema(description = "数据状态")
    private String status;

    /**
     * REQ类转为实体类
     */
    public abstract T toEntity();
}
