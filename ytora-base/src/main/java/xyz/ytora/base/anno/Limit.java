package xyz.ytora.base.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 幂等性限制
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Limit {

    String value() default "";

    //最小间隔时间，默认 1 分钟
    long minIntervalMillis() default 1000;

    //窗口期
    long timeWindowMillis() default 5 * 60 * 1000;

    //窗口期内最多次数
    int maxCalls() default 10;
}
