package xyz.ytora.base.cache;

import lombok.Getter;
import xyz.ytora.base.cache.support.local.CacheByLocal;

/**
 * 缓存组件类型
 */
@Getter
public enum CacheType {
    /**
     * 基于本地JVM的缓存
     */
    LOCAL(1, CacheByLocal.class),
    /**
     * 基于Redis组件的缓存
     */
    REDIS(2, null),
    /**
     * 基于数据库的缓存
     */
    DB(3, null);

    private final int order;

    private final Class<? extends ICache> cache;

    CacheType(int order, Class<? extends ICache> cache) {
        this.order = order;
        this.cache = cache;
    }

}
