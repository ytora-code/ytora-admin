package xyz.ytora.base.cache.support.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ytora.base.cache.ICache;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * 基本本地内存的缓存
 */
public class CacheByLocal implements ICache {

    private static final Logger log = LoggerFactory.getLogger(CacheByLocal.class);

    private final Cache<String, CacheItem> cache;

    public CacheByLocal() {
        cache = Caffeine.newBuilder()
                .expireAfter(new LocalCaffeineExpiry())
                .initialCapacity(100)
                .maximumSize(1024)
                .removalListener((String key, CacheItem value, RemovalCause cause) -> {
                    String reasonCn = switch (cause) {
                        case EXPIRED -> "到期了启动移除";
                        case SIZE -> "容量满了被淘汰策略移除";
                        case EXPLICIT -> "用户手动remove";
                        case REPLACED -> "用户更新被替换 (put覆盖)";
                        case COLLECTED -> "垃圾回收 (GC) 移除";
                    };

                    // 打印日志
                    log.info("Key [{}] 被移除, 原因是: {}", key, reasonCn);
                })
                .build();
    }

    @Override
    public <T> void put(Object key, T value) {
        put(key, value, -1);
    }

    @Override
    public <T> void put(Object key, T value, long ttl) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为空");
        }
        String keyStr = key.toString();
        CacheItem item = new CacheItem(keyStr, value, ttl);
        cache.put(keyStr, item);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为空");
        }
        CacheItem item = cache.getIfPresent(key.toString());
        if (item == null) {
            return null;
        }
        return (T) item.getType().cast(item.getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Supplier<T> supplier, long ttl) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为空");
        }
        String keyStr = key.toString();
        CacheItem item = cache.get(keyStr, k -> new CacheItem(k, supplier.get(), ttl));
        if (item == null) {
            return null;
        }
        return (T) item.getType().cast(item.getValue());
    }

    @Override
    public List<String> getAll() {
        ConcurrentMap<String, @NonNull CacheItem> sourceMap = cache.asMap();
        return sourceMap.keySet().stream()
                .map(sourceMap::get)
                .filter(Objects::nonNull)
                .filter(item -> item.getTtl() < 0 || item.getCacheAt() + item.getTtl() > System.currentTimeMillis())
                .map(CacheItem::getKey)
                .toList();
    }

    @Override
    public boolean extendTTL(Object key, long ttl) {
        Object value = get(key);
        if (value == null) {
            log.error("[{}] 不存在，延长TTL失败", key.toString());
            return false;
        }
        put(key, value, ttl);
        return true;
    }

    @Override
    public void remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为空");
        }
        cache.invalidate(key.toString());
    }

}
