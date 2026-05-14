package xyz.ytora.core.rbac.permission;

/**
 * 菜单类型
 *
 * @author ytora
 * @since 1.0
 */
public enum PermissionType {
    /**
     * API接口
     */
    API(1),
    /**
     * 菜单
     */
    MENU(2),
    /**
     * 表格
     */
    TABLE(3),
    /**
     * 表单
     */
    FORM(4),
    /**
     * 其他
     */
    OTHER(5);

    int code;

    PermissionType(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
