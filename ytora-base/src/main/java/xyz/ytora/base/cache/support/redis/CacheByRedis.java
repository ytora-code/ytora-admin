package xyz.ytora.base.cache.support.redis;

import xyz.ytora.base.cache.ICache;

import java.util.List;
import java.util.function.Supplier;

/**
 * 基本redis的缓存
 *
 * @author ytora
 * @since 1.0
 */
public class CacheByRedis implements ICache {
    @Override
    public <T> void put(Object key, T value) {

    }

    @Override
    public <T> void put(Object key, T value, long ttl) {

    }

    @Override
    public <T> T get(Object key) {
        return null;
    }

    @Override
    public <T> T get(Object key, Supplier<T> supplier, long ttl) {
        return null;
    }

    @Override
    public List<String> getAll() {
        return List.of();
    }

    @Override
    public boolean extendTTL(Object key, long ttl) {
        return false;
    }

    @Override
    public <T> T remove(Object key) {
        return null;
    }
}
