package xyz.ytora.core.sys.log;

/**
 * 日志类型
 *
 * @author ytora 
 * @since 1.0
 */
public enum LogType {

    /**
     * 比如在业务中埋点，打的日志
     */
    NORMAL_LOG(1, "普通日志"),

    /**
     * 用户访问接口，记录的日志
     */
    REQUEST_LOG(2, "请求日志"),

    /**
     * 用户登录相关日志，比如登录、登出、输入错误密码
     */
    LOGIN_LOG(3, "登录日志"),

    /**
     * 定时任务自动记录的日志
     */
    SCHEDULE_TASK_LOG(4, "定时任务日志"),

    /**
     * 错误日志
     */
    ERROR_LOG(5, "定时任务日志"),

    /**
     * 其他日志
     */
    OTHER_LOG(6, "其他日志"),
    ;

    private final int type;
    private final String remark;

    LogType(int type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public int type() {
        return type;
    }

    public String remark() {
        return remark;
    }
}
