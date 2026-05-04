package xyz.ytora.base.auth;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class AuthException extends RuntimeException {
    /**
     * 异常状态码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String message;
    /**
     * 原始异常类型
     */
    private final Throwable throwable;

    public AuthException(String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = ResultCode.FAIL.code;
        this.throwable = this;
    }

    public AuthException(Throwable throwable, String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = ResultCode.FAIL.code;
        this.throwable = throwable;
    }

    public AuthException(ResultCode ResultCode) {
        this.message = ResultCode.message;
        this.code = ResultCode.code;
        this.throwable = this;
    }

    public AuthException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.message = cause.getMessage();
        this.throwable = cause;
    }
}
