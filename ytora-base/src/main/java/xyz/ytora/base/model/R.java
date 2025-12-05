package xyz.ytora.base.model;

import lombok.Data;
import lombok.experimental.Accessors;
import xyz.ytora.base.enums.RespCode;

/**
 * 统一返回对象，result
 *
 * @param <T> 响应数据类型
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
     * 耗时
     */
    private Long cost;

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
        r.success = RespCode.SUCCESS.status;
        r.code = RespCode.SUCCESS.code;
        r.data = data;
        return r;
    }

    /**
     * 弹出提示，不返回数据
     */
    public static R<String> success(String msg) {
        R<String> r = new R<>();
        r.success = RespCode.SUCCESS.status;
        r.message = msg;
        r.code = RespCode.SUCCESS.code;
        return r;
    }

    /**
     * 返回数据，同时弹出提示
     */
    public static <T> R<T> success(String msg, T data) {
        R<T> r = new R<>();
        r.success = RespCode.SUCCESS.status;
        r.message = msg;
        r.code = RespCode.SUCCESS.code;
        r.data = data;
        return r;
    }

    public static R<String> error() {
        return error("请求失败");
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.success = RespCode.FAIL.status;
        r.code = RespCode.FAIL.code;
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

    public static R<String> error(RespCode respCode) {
        R<String> r = new R<>();
        r.success = respCode.status;
        r.code = respCode.code;
        r.message = respCode.message;
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
        json.append("\"cost\":").append(cost).append(",");
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
