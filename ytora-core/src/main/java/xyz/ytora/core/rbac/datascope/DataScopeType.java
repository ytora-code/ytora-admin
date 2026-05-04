package xyz.ytora.core.rbac.datascope;

import java.util.Arrays;

/**
 * 几种内置的数据范围类型
 *
 * @author ytora
 * @since 1.0
 */
public enum DataScopeType {

    ALL("ALL", "查看全部数据"),

    ROOT_DEPART("ROOT_DEPART", "查看当前主体（组织根）数据"),

    CRUEENT_DEPART_AND_CHILD("CRUEENT_DEPART_AND_CHILD", "查看当前部门及子部门数据"),

    SELF_CREATED("SELF_CREATED", "查看本人创建的数据"),

    ASSIGNED_DEPART("ASSIGNED_DEPART", "查看指定部门的数据"),

    ASSIGNED_USER("ASSIGNED_USER", "查看指定人员创建的数据"),

    CUSTOM("CUSTOM", "自定义")
    ;


    private final String code;
    private final String desc;

    DataScopeType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DataScopeType fromCode(String code) {
        return Arrays.stream(values())
                .filter(item -> item.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown DataScopeType code: " + code));
    }

    /**
     * 是否需要用户指定
     */
    public boolean isCustomAssignment() {
        return this == ASSIGNED_DEPART || this == ASSIGNED_USER;
    }

}
