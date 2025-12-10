package xyz.ytora.base.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 缓存工具类
 */
@Slf4j
@Component
public class Caches {

    private CacheType defaultCache;
    private final Map<CacheType, ICache> caches = new LinkedHashMap<>();

    @Autowired
    public void autowired(CacheProperty property) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.defaultCache = property.getDefaultCache();
        if (defaultCache == null) {
            throw new IllegalArgumentException("必须指定 ytora.cache.default-cache");
        }
        log.info("使用{}作为默认缓存实现", defaultCache.name());

        Map<CacheType, Class<? extends ICache>> itemMap = property.getImpl();
        List<CacheType> orderCacheTypes = itemMap.keySet().stream().sorted(Enum::compareTo).toList();
        for (CacheType cacheType : orderCacheTypes) {
            Class<? extends ICache> clazz = itemMap.get(cacheType);
            caches.put(cacheType, clazz.getDeclaredConstructor().newInstance());
        }
    }

    public <T> void put(String key, T value) {
        caches.get(defaultCache).put(key, value);
    }

    public <T> void put(CacheType cacheType, String key, T value) {
        caches.get(cacheType).put(key, value);
    }

    public <T> void put(String key, T value, long ttl) {
        caches.get(defaultCache).put(key, value, ttl);
    }

    public <T> void put(CacheType cacheType, String key, T value, long ttl) {
        caches.get(cacheType).put(key, value, ttl);
    }

    public <T> T get(String key) {
        return caches.get(defaultCache).get(key);
    }

    public <T> T get(CacheType cacheType, String key) {
        return caches.get(cacheType).get(key);
    }

    public <T> T get(String key, Supplier<T> supplier, long ttl) {
        return caches.get(defaultCache).get(key, supplier, ttl);
    }

    public <T> T get(CacheType cacheType, String key, Supplier<T> supplier, long ttl) {
        return caches.get(cacheType).get(key, supplier, ttl);
    }

    public List<String> getAll() {
        return caches.get(defaultCache).getAll();
    }

    public List<String> getAll(CacheType cacheType) {
        return caches.get(cacheType).getAll();
    }

    public boolean extendTTL(String key, long ttl) {
        return caches.get(defaultCache).extendTTL(key, ttl);
    }

    public boolean extendTTL(CacheType cacheType, String key, long ttl) {
        return caches.get(cacheType).extendTTL(key, ttl);
    }

    public void remove(String key) {
        caches.get(defaultCache).remove(key);
    }

    public void remove(CacheType cacheType, String key) {
        caches.get(cacheType).remove(key);
    }
}
