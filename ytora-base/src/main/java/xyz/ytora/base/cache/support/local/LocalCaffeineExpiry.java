package xyz.ytora.base.cache.support.local;

import com.github.benmanes.caffeine.cache.Expiry;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.TimeUnit;

/**
 * 缓存过期策略
 */
@NullMarked
public class LocalCaffeineExpiry implements Expiry<String, CacheItem> {
    /**
     * 创建key后，设置过期时间
     */
    @Override
    public long expireAfterCreate(String key, CacheItem value, long currentTime) {
        if (value.getTtl() < 0) {
            return Long.MAX_VALUE;
        }
        return TimeUnit.MILLISECONDS.toNanos(value.getTtl());
    }

    /**
     * 更新key后，重置过期时间
     */
    @Override
    public long expireAfterUpdate(String key, CacheItem value, long currentTime, long currentDuration) {
        if (value.getTtl() < 0) {
            return Long.MAX_VALUE;
        }
        return TimeUnit.MILLISECONDS.toNanos(value.getTtl());
    }

    /**
     * 读取key后，不会延长key的过期时间
     */
    @Override
    public long expireAfterRead(String key, CacheItem value, long currentTime, long currentDuration) {
        return currentDuration;
    }
}
