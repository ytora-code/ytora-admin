package xyz.ytora.core.sys.dict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典
 *
 * <p>该系统所有字典扩展的基础注解</p>
 *
 * @author ytora 
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.FIELD})
public @interface Dict {

    /**
     * <p>code单独出现时，作为系统字典code</p>
     * <p>当和table、text一起出现时，作为表的列字段</p>
     */
    String code() default "";

    /**
     * 自定义表名（当不使用系统内置字典时）
     */
    String table() default "";

    /**
     * 需要被翻译的列字段名称
     */
    String text() default "";

    /**
     * 数据源
     */
    String ds() default "";

    /**
     * 是否是多值（以逗号分隔的多个字典code），暂未支持
     */
    boolean multi() default false;

}
