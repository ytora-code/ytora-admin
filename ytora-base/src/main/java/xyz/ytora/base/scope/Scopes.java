package xyz.ytora.base.scope;

import xyz.ytora.base.exception.BaseException;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * 上下文工具类
 */
public class Scopes {

    public static  <T, R> R run(ScopedValue<T> sv, T value, Supplier<R> supplier) {
        // 记录下异常
        AtomicReference<Throwable> exceptionRef = new AtomicReference<>();
        // 记录下任务执行结果
        AtomicReference<R> resultRef = new AtomicReference<>();
        ScopedValue.where(sv, value).run(() -> {
            try {
                R result = supplier.get();
                resultRef.set(result);
            } catch (Throwable e) {
                exceptionRef.set(e);
            }
        });

        // 抛出异常
        if (exceptionRef.get() != null) {
            throw new BaseException(exceptionRef.get());
        }

        return resultRef.get();
    }

}
