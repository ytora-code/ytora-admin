package xyz.ytora.base.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ytora.base.RespUtil;
import xyz.ytora.base.mvc.RespCode;
import xyz.ytora.base.mvc.R;
import xyz.ytora.base.storage.StorageException;
import xyz.ytora.sql4j.Sql4JException;
import xyz.ytora.ytool.document.DocException;

/**
 * 全局异常处理器
 */
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    public R<?> nullPointerExceptionHandler(NullPointerException e) {
        log.error(e.getMessage(), e);
        logging(e);
        return R.error(RespCode.NULL_ERROR.code, RespCode.NULL_ERROR.message);
    }

    @ExceptionHandler(BaseException.class)
    public R<?> baseExceptionHandler(BaseException e) {
        log.error(e.getMessage(), e.getCause());
        logging(e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DownloadException.class)
    public R<?> downloadExceptionHandler(DownloadException e) {
        HttpServletResponse resp = RespUtil.getResp();
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        log.error(e.getMessage(), e);
        logging(e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Sql4JException.class)
    public R<?> sql4JExceptionHandler(Sql4JException e) {
        log.error(e.getMessage(), e.getCause());
        logging(e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DocException.class)
    public R<?> docExceptionHandler(DocException e) {
        HttpServletResponse resp = RespUtil.getResp();
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        log.error(e.getMessage(), e.getCause());
        logging(e);
        return R.error(RespCode.FILE_DOWNLOAD_FAIL.code, e.getMessage());
    }

    @ExceptionHandler(StorageException.class)
    public R<?> storageExceptionHandler(StorageException e) {
        log.error(e.getMessage(), e);
        logging(e);
        return R.error(RespCode.FAIL.code, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<?> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        logging(e);
        return R.error(RespCode.FAIL.code, e.getMessage());
    }

    /**
     * 记录异常日志
     */
    private void logging(Exception e) {
        //TODO 记录异常日志
    }
}
