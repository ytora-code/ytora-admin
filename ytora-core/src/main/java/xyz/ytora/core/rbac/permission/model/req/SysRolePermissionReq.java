package xyz.ytora.core.rbac.permission.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * created by YT on 2026/1/4 23:15:15
 * <br/>
 */
@Data
@Schema(description = "角色-资源请求数据")
public class SysRolePermissionReq {
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 原始集合数组
     */
    @Schema(description = "原始集合数组")
    private List<String> originPermissionIds;

    /**
     * 最新的集合数组
     */
    @Schema(description = "最新的集合数组")
    private List<String> currentPermissionIds;
}
