package xyz.ytora.base.cache;

/**
 * 缓存组件类型
 */
public enum CacheType {
    /**
     * 基于本地JVM的缓存
     */
    LOCAL(1),
    /**
     * 基于Redis组件的缓存
     */
    REDIS(2),
    /**
     * 基于数据库的缓存
     */
    DB(3);

    final int order;

    CacheType(int order) {
        this.order = order;
    }
}
