package xyz.ytora.base.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * created by yangtong on 2025/4/4 下午7:11
 * <br/>
 * 自定义的响应状态码
 */
public enum RespCode {

    // ==================== 0 请求成功 ====================
    SUCCESS(0, true, "请求成功"),

    // ==================== -1 请求失败，通用错误码 ====================
    FAIL(-1, false, "请求错误"),

    // ==================== 1-99 认证授权相关 ====================
    NOT_LOGIN(1, false, "未登录，请先登录"),
    LOGIN_TIMEOUT(2, false, "登录过期，请重新登录"),
    UNKNOWN_USER(3, false, "用户不存在"),
    PASSWORD_ERROR(4, false, "密码错误"),
    UNKNOWN_USER_PASSWORD(5, false, "账号或密码错误"),
    BANNED_USER(6, false, "被禁止的用户"),
    CAPTCHA_ERROR(7, false, "验证码错误"),
    THIRD_PARTY_LOGIN_ERROR(8, false, "第三方登录失败"),
    LOGIN_FREQUENTLY(9, false, "登录太频繁"),
    NO_PERMISSION(11, false, "没有接口权限"),
    ACCESS_DENIED(12, false, "访问被拒绝"),

    // ==================== 101-199 请求相关 ====================
    PARAM_ERROR(101, false, "参数错误"),
    MISSING_PARAM(102, false, "缺少必要参数"),
    FORMAT_ERROR(103, false, "数据格式不正确"),
    VALIDATION_FAILED(104, false, "数据校验失败"),
    DUPLICATE_SUBMIT(105, false, "请勿重复提交"),
    IDEMPOTENT_KEY_MISSING(106, false, "幂等标识缺失"),

    // ==================== 200-299 业务相关 ====================
    BUSINESS_ERROR(200, false, "业务处理失败"),
    DATA_NOT_FOUND(201, false, "数据不存在"),
    DATA_DUPLICATE(202, false, "数据重复"),
    DATA_CONFLICT(203, false, "数据冲突"),
    OPERATION_NOT_ALLOWED(204, false, "操作不被允许"),

    // ==================== 300-399 系统错误 ====================
    SERVER_ERROR(300, false, "服务器内部错误"),
    SERVICE_UNAVAILABLE(301, false, "服务不可用"),
    TIMEOUT(302, false, "请求超时"),
    DATABASE_ERROR(303, false, "数据库连接异常"),
    CACHE_ERROR(304, false, "缓存服务异常"),

    // ==================== 400-499 文件相关 ====================
    FILE_ERROR(400, false, "文件异常"),
    DUPLICATE_RESOURCE(401, false, "文件已存在"),
    RESOURCE_LOCKED(402, false, "文件被锁定"),
    FILE_NOT_FOUND(403, false, "文件未找到"),
    FILE_UPLOAD_FAIL(404, false, "文件上传失败"),
    FILE_DOWNLOAD_FAIL(405, false, "文件下载失败"),
    FILE_DELETE_FAIL(406, false, "文件删除失败"),
    DETECT_FILE_TYPE_FAIL(407, false, "文件类型检测失败"),
    ERROR_FILE_ID(408, false, "不允许的文件id"),
    ERROR_FILE_METADATA(409, false, "文件元数据读取异常"),
    FILE_SIZE_EXCEEDED(410, false, "文件大小超出限制"),
    UNSUPPORTED_FILE_FORMAT(411, false, "不支持的文件格式"),

    // ==================== 500-599 外部服务相关 ====================
    EXTERNAL_SERVICE_ERROR(500, false, "外部服务异常"),
    THIRD_PARTY_API_ERROR(501, false, "第三方API调用失败"),
    NETWORK_ERROR(502, false, "网络连接异常");

    /**
     * 请求结果状态码
     */
    public final Integer code;

    /**
     * 请求是否成功
     */
    public final Boolean status;

    /**
     * 请求结果状态说明
     */
    public final String message;

    /**
     * 静态映射，用于快速查找
     */
    private static final Map<Integer, RespCode> CODE_MAP = new HashMap<>();

    static {
        for (RespCode respCode : RespCode.values()) {
            CODE_MAP.put(respCode.code, respCode);
        }
    }

    RespCode(Integer code, Boolean status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    /**
     * 根据状态码获取枚举实例
     * @param code 状态码
     * @return 对应的枚举实例，如果不存在则返回null
     */
    public static RespCode getByCode(Integer code) {
        return CODE_MAP.get(code);
    }

    /**
     * 根据状态码获取枚举实例，如果不存在则返回默认值
     * @param code 状态码
     * @param defaultValue 默认值
     * @return 对应的枚举实例或默认值
     */
    public static RespCode getByCode(Integer code, RespCode defaultValue) {
        return CODE_MAP.getOrDefault(code, defaultValue);
    }

    /**
     * 判断是否为认证授权相关错误
     * @return true if it's an authentication/authorization error
     */
    public boolean isAuthError() {
        return code >= 1 && code <= 99;
    }

    /**
     * 判断是否为参数相关错误
     * @return true if it's a parameter error
     */
    public boolean isParamError() {
        return code >= 101 && code <= 199;
    }

    /**
     * 判断是否为业务相关错误
     * @return true if it's a business error
     */
    public boolean isBusinessError() {
        return code >= 200 && code <= 299;
    }

    /**
     * 判断是否为系统错误
     * @return true if it's a system error
     */
    public boolean isSystemError() {
        return code >= 300 && code <= 399;
    }

    /**
     * 判断是否为文件相关错误
     * @return true if it's a file error
     */
    public boolean isFileError() {
        return code >= 400 && code <= 499;
    }

    /**
     * 判断是否为外部服务相关错误
     * @return true if it's an external service error
     */
    public boolean isExternalServiceError() {
        return code >= 500 && code <= 599;
    }

    /**
     * 格式化消息
     * @param args 参数
     * @return 格式化后的消息
     */
    public String formatMessage(Object... args) {
        return String.format(this.message, args);
    }

    /**
     * 获取HTTP状态码映射
     * @return 对应的HTTP状态码
     */
    public int getHttpStatus() {
        if (isAuthError()) {
            // 认证授权类错误
            if (code == 1 || code == 2) {
                return 401; // Unauthorized
            } else {
                return 403; // Forbidden
            }
        } else if (isParamError()) {
            // 参数错误
            return 400; // Bad Request
        } else if (isSystemError()) {
            // 系统错误
            return 500; // Internal Server Error
        } else if (code == 0) {
            // 成功
            return 200; // OK
        } else {
            // 其他业务错误
            return 400; // Bad Request
        }
    }

    @Override
    public String toString() {
        return String.format("RespCode{code=%d, success=%s, message='%s'}",
                code, status, message);
    }
}