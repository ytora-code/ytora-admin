package xyz.ytora.core.rbac.permission.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.permission.model.SysPermissionMapper;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.excel.SysPermissionExcel;
import xyz.ytora.toolkit.tree.ITree;

import java.util.List;

/**
 * 用户响应数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户表响应数据")
public class SysPermissionData extends BaseData<SysPermission> implements ITree<SysPermissionData> {

    /**
     * 父资源id
     */
    @Schema(description = "父资源id")
    private String pid;

    /**
     * 父资源名称
     */
    @Schema(description = "父资源名称")
    private String pName;

    /**
     * 层级
     */
    @Schema(description = "层级")
    private Integer level;

    /**
     * 层级Key
     */
    @Schema(description = "层级Key")
    private String levelKey;

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
     * 元数据
     */
    @Schema(description = "元数据")
    private SysComponentData meta;

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
    private List<SysPermissionData> children;


    @Override
    public SysPermissionExcel toExcel() {
        SysPermissionMapper mapper = SysPermissionMapper.mapper;
        return mapper.toExcel(this);
    }
}
