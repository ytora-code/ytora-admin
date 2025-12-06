package xyz.ytora.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.ytora.base.model.R;

/**
 * created by YT on 2025/12/6 16:40:57
 * <br/>
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public R<?> baseExceptionHandler(BaseException e) {
        log.error(e.getMessage(), e);
        logging(e);
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 记录异常日志
     */
    private void logging(Exception e) {
        //TODO 记录异常日志
    }
}
