package xyz.ytora.base.mvc.enums;

import xyz.ytora.base.exception.BaseException;

/**
 * 存在映射关系数据的操作类型：绑定或解绑
 */
public enum MapperType {

    /**
     * 绑定
     */
    BIND("bind"),
    /**
     * 解绑
     */
    UN_BIND("unBind");

    private final String type;

    MapperType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static MapperType get(String typeName) {
        if ("bind".equalsIgnoreCase(typeName)
                || "add".equalsIgnoreCase(typeName)
                || "insert".equalsIgnoreCase(typeName)) {
            return BIND;
        } else if ("unBind".equalsIgnoreCase(typeName)
                || "un-bind".equalsIgnoreCase(typeName)
                || "remove".equalsIgnoreCase(typeName)
                || "delete".equalsIgnoreCase(typeName)) {
            return UN_BIND;
        }
        throw new BaseException("未知的操作类型：" + typeName);
    }
}
