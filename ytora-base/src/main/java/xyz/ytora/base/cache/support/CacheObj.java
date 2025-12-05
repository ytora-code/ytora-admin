package xyz.ytora.base.cache.support;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * created by yangtong on 2025/4/4 下午9:34
 * <br/>
 * 缓存对象
 */
@Data
public class CacheObj implements Serializable {

    /**
     * 缓存键
     */
    public String key;
    /**
     * 缓存值
     */
    public Object value;
    /**
     * 缓存放入时间
     */
    public Long cacheAt;
    /**
     * 缓存过期时间
     */
    public volatile Long expireAt;

    public CacheObj() {

    }

    /**
     * 设置缓存对象，value：缓存值 timeout：过期时间(ms)
     */
    public CacheObj(String key, Object value, long timeout) {
        long now = System.currentTimeMillis();
        this.key = key;
        this.value = value;
        this.cacheAt = now;
        this.expireAt = timeout > 0 ? now + timeout : -1;
    }

    //判断是否过期
    public boolean isExpired() {
        return expireAt > 0 && System.currentTimeMillis() > expireAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CacheObj)) {
            return false;
        }
        return ((CacheObj) o).key.equals(this.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
