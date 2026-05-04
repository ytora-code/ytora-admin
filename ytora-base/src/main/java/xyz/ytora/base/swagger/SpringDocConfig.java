package xyz.ytora.base.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 接口文档配置
 *
 * @author ytora
 * @since 1.0
 */
@Configuration
public class SpringDocConfig {

    private List<String> swaggerPath;

    @Value("${ytora.version}")
    private String version;

    @Value("${ytora.name}")
    private String name;

    @Autowired
    public void autowired(@Value("${server.servlet.context-path}") String contextPath) {
        swaggerPath = List.of(
                contextPath + "/v3/api-docs/**",
                contextPath + "/swagger-ui/**",
                contextPath + "/doc.html",
                contextPath + "/webjars/**",
                contextPath + "/actuator"
        );
    }

    /**
     * 接口文档整体配置
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setName("ytora");
        contact.setEmail("yt220600@gmail.com");
        contact.setUrl("https://www.4399.com");
        return new OpenAPI()
                .info(new Info()
                        .title(name + " 接口文档")
                        .description(name + " 接口文档")
                        .contact(contact)
                        .version(version)
                        .license(new License().name("Apache 2.0").url("https://www.4399.com"))
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("233333")
                                .url("https://www.baidu.com")
                )
                ;
    }

    /**
     * RBAC管理分组
     */
    @Bean
    public GroupedOpenApi rbacGroup() {
        return GroupedOpenApi.builder()
                .group("RBAC")
                .addOpenApiMethodFilter((e) -> e.isAnnotationPresent(Operation.class))
                .pathsToMatch("/rbac/**")
                .addOperationCustomizer(globalHeaderCustomizer())
                .build();
    }

    /**
     * 系统管理分组
     */
    @Bean
    public GroupedOpenApi sysGroup() {
        return GroupedOpenApi.builder()
                .group("系统管理")
                .addOpenApiMethodFilter((e) -> e.isAnnotationPresent(Operation.class))
                .pathsToMatch("/sys/**")
                .addOperationCustomizer(globalHeaderCustomizer())
                .build();
    }

    /**
     * 监控模块分组
     */
    @Bean
    public GroupedOpenApi monitorGroup() {
        return GroupedOpenApi.builder()
                .group("监控模块")
                .addOpenApiMethodFilter((e) -> e.isAnnotationPresent(Operation.class))
                .pathsToMatch("/monitor/**", "/sse/*")
                .addOperationCustomizer(globalHeaderCustomizer())
                .build();
    }

    /**
     * 全局请求头
     */
    @Bean
    public OperationCustomizer globalHeaderCustomizer() {
        return (operation, handlerMethod) -> {
            Parameter header = new Parameter()
                    .in("header")
                    .schema(new StringSchema())
                    .name("Authorization")
                    .description("请求令牌")
                    .required(false);
            operation.addParametersItem(header);
            return operation;
        };
    }

    /**
     * 获取swagger相关路径，用于排除
     *
     * @return 路径集合
     */
    public List<String> swaggerPath() {
        return swaggerPath;
    }

}
