package xyz.ytora.core.ai.logic.aiimpl.support.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI API通用配置
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "ytora.ai.api")
public class AiApiProperty {

    /**
     * 默认供应商
     */
    private String defaultProvider;

    /**
     * API基础地址
     */
    private String baseUri;

    /**
     * API密钥
     */
    private String apiKey;

    /**
     * 模型名称
     */
    private String model;

    /**
     * 请求超时时间，单位秒
     */
    private Integer timeoutSeconds = 120;

}
