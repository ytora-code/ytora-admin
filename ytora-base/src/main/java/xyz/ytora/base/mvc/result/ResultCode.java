package xyz.ytora.base.mvc.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的响应状态码
 *
 * @author ytora
 * @since 1.0
 */
public enum ResultCode {

    // ==================== 0-9 请求成功 ====================
    SUCCESS(0, true, "请求成功")

    // ==================== 10-99 业务错误 ====================
    , NOT_LOGIN(10, false, "未登录，请先登录")
    , LOGIN_TIMEOUT(11, false, "登录过期，请重新登录")
    , UNKNOWN_USER(12, false, "未知的用户")
    , UNKNOWN_USER_PASSWORD(13, false, "账号或密码错误")
    , CAPTCHA_ERROR(14, false, "验证码错误")
    , BANNED_USER(15, false, "被禁止的用户")
    , LOGIN_FREQUENTLY(16, false, "登录太频繁")
    , NO_PERMISSION(17, false, "没有访问权限")
    , QUEST_FREQUENTLY(18, false, "请求太频繁")
    , PARAM_ERROR(19, false, "参数错误")
    , MISSING_PARAM(20, false, "缺少必要参数")
    , FORMAT_ERROR(21, false, "数据格式不正确")
    , VALIDATION_FAILED(22, false, "数据校验失败")
    , DUPLICATE_SUBMIT(23, false, "请勿重复提交")
    , FILE_UPLOAD_FAIL(24, false, "文件上传失败")
    , FILE_NOT_FOUND(25, false, "文件未找到")
    , FILE_DOWNLOAD_FAIL(26, false, "文件下载失败")
    , FILE_DELETE_FAIL(27, false, "文件删除失败")
    , DETECT_FILE_TYPE_FAIL(28, false, "文件类型检测失败")
    , ERROR_FILE_ID(29, false, "不允许的文件id")
    , ERROR_FILE_METADATA(30, false, "文件元数据读取异常")

    // ==================== 100-200 程序错误 ====================
    , NULL_ERROR(101, false, "空指针异常"), SQL_ERROR(11, false, "数据库执行异常")

    , FAIL(500, false, "其他错误")

    ;

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
    private static final Map<Integer, ResultCode> CODE_MAP = new HashMap<>();

    static {
        for (ResultCode respCode : ResultCode.values()) {
            CODE_MAP.put(respCode.code, respCode);
        }
    }

    ResultCode(Integer code, Boolean status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    /**
     * 根据状态码获取枚举实例
     * @param code 状态码
     * @return 对应的枚举实例，如果不存在则返回null
     */
    public static ResultCode getByCode(Integer code) {
        return CODE_MAP.get(code);
    }

    /**
     * 根据状态码获取枚举实例，如果不存在则返回默认值
     * @param code 状态码
     * @param defaultValue 默认值
     * @return 对应的枚举实例或默认值
     */
    public static ResultCode getByCode(Integer code, ResultCode defaultValue) {
        return CODE_MAP.getOrDefault(code, defaultValue);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
