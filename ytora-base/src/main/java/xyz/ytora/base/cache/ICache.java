package xyz.ytora.base.cache;

import lombok.NonNull;
import xyz.ytora.base.cache.support.CacheObj;
import xyz.ytora.ytool.date.DateUnit;

import java.util.List;
import java.util.function.Supplier;

/**
 * 缓存规范接口
 */
public interface ICache {
    /**
     * 添加缓存
     */
    <T> void put(@NonNull Object key, T value);

    /**
     * 添加带有过期时间的缓存
     */
    <T> void put(@NonNull Object key, T value, long timeout, DateUnit unit);

    /**
     * 获取缓存
     */
    <T> T get(@NonNull Object key);

    /**
     * 获取指定类型的缓存
     */
    <T> T get(@NonNull Object key, Class<T> type);

    /**
     * 获取指定类型的缓存，如果没有，则按照指定的逻辑放一份带有过期时间的缓存
     */
    <T> T get(@NonNull Object key, Class<T> type, Supplier<T> supplier, long timeout, DateUnit unit);

    /**
     * 延长缓存过期时间
     */
    boolean extendTTL(@NonNull Object key, long timeout, DateUnit unit);

    /**
     * 移除缓存
     */
    void remove(@NonNull Object key);

    /**
     * 判断是否包含指定缓存
     */
    boolean contains(@NonNull Object key);

    /**
     * 分页获取缓存
     */
    List<CacheObj> list(long pageNo, long pageSize);

    /**
     * 清理所有缓存
     */
    void clear();
}
