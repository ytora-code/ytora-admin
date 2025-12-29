package xyz.ytora.base.sql4J;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import xyz.ytora.sql4j.orm.scanner.RepoScanner;

import java.util.List;

/**
 * 负责在 Spring 启动初期注册所有 Repo 的 Bean 定义
 */
public class Sql4JRepoRegistrar implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) {
        // 1. 提前绑定配置属性（因为此时 Sql4JProperty Bean 还没创建）
        Sql4JProperty properties = Binder.get(environment)
                .bind("ytora.sql4j", Sql4JProperty.class)
                .orElse(new Sql4JProperty());

        String repoPath = properties.getRepoPath();
        if (repoPath == null || repoPath.isEmpty()) {
            return;
        }

        // 2. 扫描IRepo接口
        RepoScanner scanner = new RepoScanner(repoPath);
        List<Class<?>> interfaces = scanner.scanRepoInterfaces();

        // 3. 循环注册为 FactoryBean
        for (Class<?> clazz : interfaces) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Sql4JRepoFactoryBean.class);
            // !关键：使用构造函数注入，确保类型元数据第一时间被注入，如果使用属性依赖注入，可能会报错
            builder.addConstructorArgValue(clazz);
            builder.addConstructorArgValue(repoPath);
//            builder.addPropertyValue("repoInterface", clazz);
//            builder.addPropertyValue("pkgToScan", repoPath);
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            registry.registerBeanDefinition(clazz.getName(), builder.getBeanDefinition());
        }
    }
}
