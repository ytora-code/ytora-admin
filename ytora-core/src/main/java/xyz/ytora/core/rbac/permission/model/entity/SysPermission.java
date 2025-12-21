package xyz.ytora.core.rbac.permission.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.core.rbac.permission.model.SysPermissionMapper;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.ColumnType;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

import java.time.LocalDate;

/**
 * 资源表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_permission", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "资源表")
public class SysPermission extends BaseEntity<SysPermission> {
    /**
     * 父资源id
     */
    @Index(1)
    @Column(comment = "父资源id", notNull = true)
    private String pid;

    /**
     * 资源名称
     */
    @Index(2)
    @Column(comment = "资源名称", notNull = true)
    private String permissionName;

    /**
     * 资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识
     */
    @Index(3)
    @Column(comment = "资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识", notNull = true)
    private String permissionCode;

    /**
     * 资源类型，1-接口、2-页面、3-页面元素
     */
    @Index(4)
    @Column(comment = "资源类型，1-接口、2-页面、3-页面元素")
    private String permissionType;

    /**
     * 手机号码
     */
    @Index(5)
    @Column(comment = "手机号码", type = ColumnType.VARCHAR16)
    private String phone;

    /**
     * 图标
     */
    @Index(6)
    @Column(comment = "图标")
    private String icon;

    /**
     * 是否可见
     */
    @Index(7)
    @Column(comment = "是否可见")
    private Boolean visible;

    /**
     * 排序
     */
    @Index(8)
    @Column(comment = "排序")
    private String index;

    @Override
    public SysPermissionResp toResp() {
        SysPermissionResp permissionResp = SysPermissionMapper.mapper.entityToResp(this);

        // 如果该资源属于菜单，则需要获取其对应的页面组件路径
        if (permissionResp.getPermissionType() == 2) {
            // 如果是顶级菜单，组件路径应该是固定的一级路由地址
            if ("0".equals(permissionResp.getPid())) {
                permissionResp.setComponent("/layouts/index.vue");
            }
            // 如果是非顶级菜单，则使用 permissionCode 作为组件路径
            else {
                permissionResp.setComponent(permissionResp.getPermissionCode());
            }
        }
        return permissionResp;
    }
}
