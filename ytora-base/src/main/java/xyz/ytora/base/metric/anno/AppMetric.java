package xyz.ytora.base.metric.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务埋点注解。
 * <p>
 * 推荐用于核心业务方法：
 * <br/>
 * 1. 自动统计调用次数；
 * <br/>
 * 2. 自动统计成功/失败次数；
 * <br/>
 * 3. 自动统计调用耗时。
 * <p>
 * 示例：
 * <pre>{@code
 * @AppMetric(value = "user.login", description = "用户登录")
 * public LoginUserDetail doLogin(LoginParam param) {
 *     ...
 * }
 * }</pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AppMetric {

    /**
     * 指标名称，推荐使用点分格式，便于前端或后续监控系统分组。
     * 例如：user.login、order.submit、report.export。
     */
    String value();

    /**
     * 指标说明。
     */
    String description() default "";
}
