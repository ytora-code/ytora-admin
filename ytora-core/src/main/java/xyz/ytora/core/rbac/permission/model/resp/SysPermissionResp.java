package xyz.ytora.core.rbac.permission.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.dict.Dict;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.ytool.tree.ITree;

import java.util.List;

/**
 * created by YT on 2025/12/13 18:35:55
 * <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "资源表")
public class SysPermissionResp extends BaseResp<SysPermission> implements ITree<SysPermissionResp> {

    /**
     * 父资源id
     */
    @Schema(description = "父资源id")
    @Dict(table = "sys_permission", code = "id", text = "permission_name")
    private String pid;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称")
    private String permissionName;

    /**
     * 资源唯一编码
     */
    @Schema(description = "资源唯一编码")
    private String permissionCode;

    /**
     * 资源类型，1-接口、2-页面、3-页面元素
     */
    @Schema(description = "资源类型，1-接口、2-页面、3-页面元素")
    private Integer permissionType;

    /**
     * 前端组件地址（type为页面时生效）
     */
    @Schema(description = "前端组件地址（type为页面时生效）")
    private String component;

    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 排序
     */
    private Integer index;

    /**
     * 子元数集合
     */
    private List<SysPermissionResp> children;

    /**
     * 是否有子节点
     */
    private Boolean hasChildren = false;

    @Override
    public String getKey() {
        return this.permissionName;
    }

    @Override
    public void hasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

}
