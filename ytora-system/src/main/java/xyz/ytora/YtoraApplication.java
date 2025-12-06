package xyz.ytora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主启动类
 */
@EnableAsync    //开启异步任务
@EnableScheduling   //开启定时任务
@EnableCaching  //开启缓存功能
@EnableAspectJAutoProxy //开启AOP代理
@EnableTransactionManagement //开启事务
@SpringBootApplication
public class YtoraApplication {
    void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(YtoraApplication.class, args);
        Environment env = context.getEnvironment();
        String value = env.getProperty("ytora.version");
        System.out.println("项目版本：" + value);
    }
}