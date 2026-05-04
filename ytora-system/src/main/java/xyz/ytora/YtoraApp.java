package xyz.ytora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主启动类
 *
 * @author ytora
 * @since 1.0
 */
@EnableAsync(proxyTargetClass = true)    //开启异步任务
@EnableScheduling   //开启定时任务
@EnableCaching(proxyTargetClass = true)  //开启缓存功能
@EnableAspectJAutoProxy //开启AOP代理
@SpringBootApplication
public class YtoraApp {
    static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(YtoraApp.class, args);
        Environment env = context.getEnvironment();
        String value = env.getProperty("ytora.version");
        System.out.println("项目版本：" + value);
    }
}
