package xyz.ytora.base.sql4J;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by YT on 2025/12/6 16:28:57
 * <br/>
 * 多数据源注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface Ds {

    /**
     * 数据源名称
     */
    String value();

}
