package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.rbac.permission.model.SysPermissionMapper;
import xyz.ytora.core.rbac.permission.model.data.SysComponentData;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 用户表
 *
 * @author ytora
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_permission", idType = IdType.SNOWFLAKE, comment = "用户表")
public class SysPermission extends BaseEntity<SysPermission> {

    /**
     * 父资源id
     */
    @Column(comment = "父资源id", notNull = true, type = ColumnType.INT8)
    private String pid;

    /**
     * 资源名称
     */
    @Column(comment = "资源名称", notNull = true)
    private String permissionName;

    /**
     * 资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识
     */
    @Column(comment = "资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识", notNull = true)
    private String permissionCode;

    /**
     * 资源类型，1-接口、2-页面、3-页面元素
     */
    @Column(comment = "资源类型，1-接口、2-页面、3-页面元素")
    private Integer permissionType;

    /**
     * 图标
     */
    @Column(comment = "图标")
    private String icon;

    /**
     * 资源的元数据
     */
    @Column(comment = "资源的元数据")
    private SysComponentData meta;

    /**
     * 是否可见
     */
    @Column(comment = "是否可见")
    private Boolean visible;

    /**
     * 排序
     */
    @Column(comment = "排序")
    private Integer index;

    @Override
    public SysPermissionData toData() {
        SysPermissionData permission = SysPermissionMapper.mapper.toData(this);

        if (!permission.getPermissionCode().startsWith("/")) {
            permission.setPermissionCode("/" + permission.getPermissionCode());
        }

        // 如果该资源属于菜单，则需要获取其对应的页面组件路径
        if (permission.getPermissionType() == 2) {
            // 如果是顶级菜单，组件路径应该是固定的一级路由地址
            if ("0".equals(permission.getPid())) {
                permission.setComponent("/components/layouts/index.vue");
            }
            // 如果是非顶级菜单，则使用 permissionCode 作为组件路径
            else {
                permission.setComponent(permission.getPermissionCode());
            }
        }

        if (this.meta != null) {
            permission.setMeta(this.meta);
        }
        return permission;
    }
}
