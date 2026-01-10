package xyz.ytora.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.RespCode;
import xyz.ytora.ytool.str.Strs;

/**
 * 自定义系统异常类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;
    //原始异常类型
    private Throwable throwable;

    public BaseException(String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = RespCode.FAIL.code;
    }

    public BaseException(Throwable throwable, String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = RespCode.FAIL.code;
    }

    public BaseException(RespCode respCode) {
        this.message = respCode.message;
        this.code = respCode.code;
    }

    public BaseException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.message = cause.getMessage();
        this.throwable = cause;
    }
}
