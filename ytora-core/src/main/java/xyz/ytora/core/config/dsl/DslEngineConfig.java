package xyz.ytora.core.config.dsl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ytora.toolkit.text.dsl.YtoraDslEngine;
import xyz.ytora.toolkit.text.dsl.function.DslFunction;

import java.util.List;

/**
 * dsl模板解析器
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Configuration
public class DslEngineConfig {

    @Bean
    public YtoraDslEngine dslEngine(List<DslFunction> functions) {
        YtoraDslEngine.Builder builder = YtoraDslEngine.builder();
        for (DslFunction function : functions) {
            builder.registerFunction(function);
        }
        return builder.build();
    }

}
