package xyz.ytora.base.exception;

import lombok.Getter;
import xyz.ytora.base.mvc.result.ResultCode;
import xyz.ytora.toolkit.text.Strs;

/**
 * 自定义业务异常
 *
 * @author ytora 
 * @since 1.0
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 异常状态码
     */
    private final Integer code;
    /**
     * 异常消息
     */
    private final String message;
    /**
     * 原始异常类型
     */
    private final Throwable throwable;

    public BaseException(String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = ResultCode.FAIL.code;
        this.throwable = this;
    }

    public BaseException(String message, Throwable throwable) {
        this.message = message;
        this.code = ResultCode.FAIL.code;
        this.throwable = throwable;
    }

    public BaseException(Throwable throwable, String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = ResultCode.FAIL.code;
        this.throwable = throwable;
    }

    public BaseException(ResultCode ResultCode) {
        this.message = ResultCode.message;
        this.code = ResultCode.code;
        this.throwable = this;
    }

    public BaseException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.message = cause.getMessage();
        this.code = 500;
        this.throwable = cause;
    }

}
