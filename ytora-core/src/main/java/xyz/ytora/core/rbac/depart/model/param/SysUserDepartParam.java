package xyz.ytora.core.rbac.depart.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户-部门请求参数
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Schema(description = "用户-部门请求参数")
public class SysUserDepartParam {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private List<String> userIds;

    /**
     * 部门ID数组
     */
    @Schema(description = "部门ID数组")
    private String departId;

    /**
     * 操作类型
     */
    @Schema(description = "操作类型，add是绑定用户-部门关系，delete是解绑用户-部门关系")
    private String type;

}
