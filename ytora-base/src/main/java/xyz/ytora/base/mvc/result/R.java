package xyz.ytora.base.mvc.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 接口统一返回数据结构
 *
 * <p>对于普通接口，返回的数据应该使用该类包装<p/>
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class R<T> {
    /**
     * 请求是否成功
     */
    private Boolean success;
    /**
     * 请求状态码
     */
    private Integer code;
    /**
     * 请求消息
     */
    private String message;

    /**
     * 请求数据
     */
    private T data;

    /**
     * 既不返回数据，也不弹出提示
     */
    public static R<String> success() {
        return success(null);
    }

    /**
     * 只返回数据，不弹出提示
     */
    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.success = ResultCode.SUCCESS.status;
        r.code = ResultCode.SUCCESS.code;
        r.data = data;
        return r;
    }

    /**
     * 弹出提示，不返回数据
     */
    public static R<String> success(String msg) {
        R<String> r = new R<>();
        r.success = ResultCode.SUCCESS.status;
        r.message = msg;
        r.code = ResultCode.SUCCESS.code;
        return r;
    }

    /**
     * 返回数据，同时弹出提示
     */
    public static <T> R<T> success(String msg, T data) {
        R<T> r = new R<>();
        r.success = ResultCode.SUCCESS.status;
        r.message = msg;
        r.code = ResultCode.SUCCESS.code;
        r.data = data;
        return r;
    }

    public static R<String> error() {
        return error("请求失败");
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.success = ResultCode.FAIL.status;
        r.code = ResultCode.FAIL.code;
        r.message = msg;
        return r;
    }

    public static R<String> error(Integer errCode, String msg) {
        R<String> r = new R<>();
        r.success = false;
        r.code = errCode;
        r.message = msg;
        return r;
    }

    public static R<String> error(ResultCode ResultCode) {
        R<String> r = new R<>();
        r.success = ResultCode.status;
        r.code = ResultCode.code;
        r.message = ResultCode.message;
        return r;
    }

    @Override
    public String toString() {
        StringBuilder json = new StringBuilder("{");
        json.append("\"success\":").append(success).append(",");
        json.append("\"code\":").append(code).append(",");
        if (message != null) {
            json.append("\"message\":\"").append(message).append("\",");
        } else {
            json.append("\"message\":null,");
        }
        if (data != null) {
            json.append("\"data\":\"").append(data).append("\"");
        } else {
            json.append("\"data\":null");
        }
        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");

        return json.toString();
    }

}
