package xyz.ytora.base.mvc.result.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 被该注解修饰的控制器方法，将不会应用任何内置增强
 *
 * <p>如果想让一个控制器方法忽略字段、返回值、excel等增强功能额，而是使用SpringMvc默认的逻辑，就标注该注解</p>
 *
 * @author ytora 
 * @since 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoWrap {
}
