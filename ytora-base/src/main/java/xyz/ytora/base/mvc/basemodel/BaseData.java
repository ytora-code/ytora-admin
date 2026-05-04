package xyz.ytora.base.mvc.basemodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.ytora.sqlux.orm.AbsEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 响应前端数据的基类
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Schema(description = "基础响应")
public abstract class BaseData<T extends AbsEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 数据创建人
     */
    @Schema(description = "数据创建人")
    private String createBy;

    /**
     * 数据创建日期
     */
    @Schema(description = "数据创建日期")
    private LocalDateTime createTime;

    /**
     * 数据修改人
     */
    @Schema(description = "数据修改人")
    private String updateBy;

    /**
     * 数据修改日期
     */
    @Schema(description = "数据修改日期")
    private LocalDateTime updateTime;

    /**
     * 数据创建人所属部门
     */
    @Schema(description = "数据创建人所属部门")
    private String departCode;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 响应数据转为 EXCEL
     */
    public abstract BaseExcel<T> toExcel();

}
