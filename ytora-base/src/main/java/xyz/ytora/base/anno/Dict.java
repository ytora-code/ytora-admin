package xyz.ytora.base.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据字典
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.FIELD})
public @interface Dict {

    /**
     * 字典编码，优先级最高
     */
    String code() default "";

    /**
     * 自定义表名（当不使用系统内置字典时）
     */
    String table() default "";

    /**
     * 表中的文本字段
     */
    String text() default "";

    /**
     * 是否是多值（如以逗号分隔的多个字典code），暂未支持
     */
    boolean multi() default false;
}
