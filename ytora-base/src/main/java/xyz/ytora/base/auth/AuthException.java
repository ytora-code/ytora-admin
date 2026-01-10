package xyz.ytora.base.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.RespCode;
import xyz.ytora.ytool.str.Strs;

/**
 * 权限异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthException extends RuntimeException {
    private Integer code;
    private String message;
    //原始异常类型
    private Throwable throwable;

    public AuthException(String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = RespCode.FAIL.code;
    }

    public AuthException(Throwable throwable, String message, Object... params) {
        this.message = Strs.format(message, params);
        this.code = RespCode.FAIL.code;
    }

    public AuthException(RespCode respCode) {
        this.message = respCode.message;
        this.code = respCode.code;
    }

    public AuthException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.message = cause.getMessage();
        this.throwable = cause;
    }
}
