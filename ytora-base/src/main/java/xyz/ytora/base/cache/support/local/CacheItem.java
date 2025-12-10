package xyz.ytora.base.cache.support.local;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 缓存项
 */
@Data
public class CacheItem implements Serializable {

    /**
     * 缓存键
     */
    public String key;

    /**
     * 缓存值
     */
    public Object value;

    /**
     * 缓存类型
     */
    private Class<?> type;

    /**
     * 缓存放入时间
     */
    public Long cacheAt;

    /**
     * 缓存过期时间
     */
    public volatile Long ttl;

    /**
     * 设置缓存对象，value：缓存值 ttl
     */
    public CacheItem(String key, Object value, long ttl) {
        long now = System.currentTimeMillis();
        this.key = key;
        this.value = value;
        this.type = value.getClass();
        this.cacheAt = now;
        this.ttl = ttl > 0 ? now + ttl : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CacheItem)) {
            return false;
        }
        return ((CacheItem) o).key.equals(this.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
