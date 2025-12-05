package xyz.ytora.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.enums.RespCode;

/**
 * 文件下载异常，对应某些需要返回文件流的接口，抛出这个异常可以返回json数据（用于业务提示）
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DownloadException extends RuntimeException {
    private Integer code;
    private String message;
    //原始异常类型
    private Throwable throwable;


    public DownloadException(String message) {
        this.message = message;
        this.code = RespCode.FAIL.code;
    }

    public DownloadException(RespCode respCode) {
        this.message = respCode.message;
        this.code = respCode.code;
    }

    public DownloadException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.message = cause.getMessage();
        this.throwable = cause;
    }


    @Override
    public String toString() {
        return "DownloadException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
