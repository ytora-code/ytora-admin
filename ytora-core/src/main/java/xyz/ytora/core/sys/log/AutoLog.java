package xyz.ytora.core.sys.log;

import java.lang.annotation.*;

import xyz.ytora.core.sys.schedule.ParameterTask;

/**
 * 日志
 *
 * <p>该系统所有日志扩展的基础注解</p>
 * <P>该注解只能标注在控制器方法和普通方法，决不能使用在{@link ParameterTask}，会和ParameterTask内部的日志API相互影响</P>
 *
 * @author ytora 
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

    String value() default "自动记录日志";
}
