package xyz.ytora.core.rbac.datascope;

import java.lang.annotation.*;

/**
 * 数据范围注解
 *
 * <p>用来限制一次query能看看到的数据范围</p>
 *
 * @author ytora
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 需要应用的数据范围的code
     */
    String value();

}
