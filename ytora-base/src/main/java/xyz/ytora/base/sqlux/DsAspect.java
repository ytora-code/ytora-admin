package xyz.ytora.base.sqlux;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.scope.Scopes;
import xyz.ytora.sqlux.core.anno.Ds;

/**
 * 多数据源情况下，切换数据源的切面
 *
 * @author ytora
 * @since 1.0
 */
@Aspect
@Component
public class DsAspect {

    @Around(value = "@annotation(ds)", argNames = "jpt,ds")
    public Object around(ProceedingJoinPoint jpt, Ds ds) throws Throwable {
        return Scopes
                .start()
                .where(ScopedValueContext.DS_CONTEXT, ds.value())
                .run(() -> {
                    try {
                        return jpt.proceed();
                    } catch (Throwable e) {
                        throw new BaseException(e);
                    }
                });
    }

}
