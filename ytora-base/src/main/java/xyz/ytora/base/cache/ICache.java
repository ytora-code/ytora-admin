package xyz.ytora.base.cache;

import java.util.List;
import java.util.function.Supplier;

/**
 * 缓存规范接口
 */
public interface ICache {
    /**
     * 添加缓存
     */
    <T> void put(Object key, T value);

    /**
     * 添加带有过期时间的缓存
     */
    <T> void put(Object key, T value, long ttl);

    /**
     * 获取缓存
     */
    <T> T get(Object key);

    /**
     * 获取指定缓存，如果没有，则按照指定的逻辑放一份带有过期时间的缓存
     */
    <T> T get(Object key, Supplier<T> supplier, long ttl);

    /**
     * 获取所有缓存的key
     */
    List<String> getAll();

    /**
     * 延长缓存过期时间
     */
    boolean extendTTL(Object key, long ttl);

    /**
     * 移除缓存
     */
    void remove(Object key);
}
