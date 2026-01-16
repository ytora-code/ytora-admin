package xyz.ytora.base.storage;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.RespCode;
import xyz.ytora.ytool.str.Strs;

/**
 * 文件下载异常，对应某些需要返回文件流的接口，抛出这个异常可以返回json数据（用于业务提示）
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StorageException extends RuntimeException {
    private Integer code;
    private String message;
    //原始异常类型
    private Throwable throwable;


    public StorageException(String message) {
        this.message = message;
    }

    public StorageException(RespCode respCode) {
        this.message = respCode.message;
        this.code = respCode.code;
    }

    public StorageException(RespCode respCode, String message) {
        this.message = respCode.message;
        if (Strs.isNotEmpty(message)) {
            this.message = this.message + ":" + message;
        }
        this.code = respCode.code;
    }

    public StorageException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.message = cause.getMessage();
        this.throwable = cause;
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
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
