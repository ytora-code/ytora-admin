package xyz.ytora.core.rbac.permission.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * created by YT on 2026/1/14 13:26:51
 * <br/>
 */
@Data
@Schema(description = "资源分类响应")
public class SysPermissionTypeResp {

    @Schema(description = "表格类资源")
    private List<SysPermissionResp> tables;

    @Schema(description = "表单类资源")
    private List<SysPermissionResp> forms;

    @Schema(description = "其他页面元素")
    private List<SysPermissionResp> items;

}
