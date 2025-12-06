package xyz.ytora.base.sql4J;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.ytora.base.scope.ScopedValueItem;

import java.util.concurrent.atomic.AtomicReference;

/**
 * created by YT on 2025/12/6 16:29:52
 * <br/>
 * 多数据源情况下，切换数据源的切面
 */
@Aspect
@Component
public class DsAspect {
    @Around(value = "@annotation(ds)", argNames = "jpt,ds")
    public Object around(ProceedingJoinPoint jpt, Ds ds) throws Throwable {
        AtomicReference<Object> resultRef = new AtomicReference<>();
        AtomicReference<Throwable> exceptionRef = new AtomicReference<>();

        ScopedValue.where(ScopedValueItem.DS_CONTEXT, ds.value())
                .run(() -> {
                    try {
                        resultRef.set(jpt.proceed());
                    } catch (Throwable e) {
                        exceptionRef.set(e);
                    }
                });

        if (exceptionRef.get() != null) {
            throw exceptionRef.get();
        }
        return resultRef.get();

    }
}
