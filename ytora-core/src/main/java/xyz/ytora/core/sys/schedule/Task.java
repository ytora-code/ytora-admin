package xyz.ytora.core.sys.schedule;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 用来标注一个定时任务
 *
 * @author ytora 
 * @since 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Task {

    /**
     * 任务名称
     */
    @AliasFor(annotation = Component.class, attribute = "value")
    String value();

}
