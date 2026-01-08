package xyz.ytora.base.mvc;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * created by YT on 2026/1/8 20:39:40
 * <br/>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer, EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    /**
     * 重定向
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/doc.html");
    }

    /**
     * 参数解析器
     * 可以添加自定义的参数解析器
     */
    @Bean
    public WebMvcRegistrations webMvcRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
                return new RequestMappingHandlerAdapter() {
                    @Override
                    public void afterPropertiesSet() {
                        super.afterPropertiesSet();
                        List<HandlerMethodArgumentResolver> argumentResolvers = getArgumentResolvers();
                        if (argumentResolvers == null) {
                            argumentResolvers = new ArrayList<>();
                        }
                        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>(argumentResolvers);
                        resolvers.addFirst(new ExcelBodyArgumentResolver());
                        setArgumentResolvers(resolvers);
                    }
                };
            }
        };
    }
}
