package xyz.ytora.base.datarule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by YT on 2026/1/10 18:14:19
 * <br/>
 * 数据规则注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {

    /**
     * 需要应用的数据规则所在的资源CODE
     */
    String value();

}
