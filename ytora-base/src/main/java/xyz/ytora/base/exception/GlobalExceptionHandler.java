package xyz.ytora.base.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ytora.base.exception.error.AppErrorBuffer;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.ResultCode;

/**
 * 全局异常处理器
 *
 * @author ytora
 * @since 1.0
 */
@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Resource
    private AppErrorBuffer appErrorBuffer;

    @ExceptionHandler(NullPointerException.class)
    public R<?> nullPointerExceptionHandler(NullPointerException e) {
        log.error("空指针", e);
        logging(e);
        return R.error(ResultCode.NULL_ERROR.code, ResultCode.NULL_ERROR.message);
    }

    @ExceptionHandler(BaseException.class)
    public R<?> baseExceptionHandler(BaseException e) {
        log.error("业务异常", e.getThrowable());
        logging(e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<?> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        logging(e);
        return R.error(ResultCode.FAIL.code, e.getMessage());
    }

    /**
     * 记录异常日志
     */
    private void logging(Exception e) {
        appErrorBuffer.record(e, "APPLICATION");
    }
}
