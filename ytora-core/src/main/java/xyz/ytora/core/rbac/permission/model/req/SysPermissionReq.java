package xyz.ytora.core.rbac.permission.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.permission.model.SysPermissionMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;

/**
 * created by System on 2025年5月22日 20:45:43
 * <br/>
 * SysPermissionReq表实体类
 * <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "资源表")
public class SysPermissionReq extends BaseReq<SysPermission> {

    private String id;

    /**
     * 删除标记，0正常，1删除
     */
    @Schema(description = "删除标记，0正常，1删除")
    private Boolean delFlag;

    /**
     * 父资源id
     */
    @Schema(description = "父资源id")
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
     * 地址（页面路由地址或者接口地址）
     */
    @Schema(description = "地址（页面路由地址或者接口地址）")
    private String path;

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

    @Override
    public SysPermission toEntity() {
        SysPermissionMapper mapper = SysPermissionMapper.mapper;
        return mapper.reqToEntity(this);
    }
}