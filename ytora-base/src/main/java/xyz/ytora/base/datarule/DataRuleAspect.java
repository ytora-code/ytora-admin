package xyz.ytora.base.datarule;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.ytora.base.scope.ScopedValueItem;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 对 RULE 进行增强
 */
@Aspect
@Component
public class DataRuleAspect {

    @Around("@annotation(rule)")
    public Object handleDownload(ProceedingJoinPoint joinPoint, Rule rule) throws Throwable {
        AtomicReference<Object> resultRef = new AtomicReference<>();
        AtomicReference<Throwable> exceptionRef = new AtomicReference<>();

        ScopedValue.where(ScopedValueItem.DATA_RULE, rule.value())
                .run(() -> {
                    try {
                        resultRef.set(joinPoint.proceed());
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
