package xyz.ytora.base.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 缓存配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "ytora.cache")
public class CacheProperty {

    /**
     * 是否默认缓存
     */
    private CacheType defaultCache;

    /**
     * 缓存路径配置
     */
    private Map<CacheType, Class<? extends ICache>> impl;
}
