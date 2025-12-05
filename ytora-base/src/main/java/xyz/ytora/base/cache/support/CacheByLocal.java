//package xyz.ytora.base.cache.support;
//
//import lombok.NonNull;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import xyz.ytora.base.cache.ICache;
//import xyz.ytora.base.exception.BaseException;
//import xyz.ytora.ytool.date.DateUnit;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ScheduledFuture;
//import java.util.function.Supplier;
//
///**
// * created by yangtong on 2025/4/4 下午9:00
// * <br/>
// * 基于JVM堆内存的缓存组件
// */
//public class CacheByLocal implements ICache {
//
//    private final int maxSize;
//    private volatile boolean isStop = false;
//    private final Map<String, CacheObj> cache = new ConcurrentHashMap<>();
//    private final Logger log = LoggerFactory.getLogger(CacheByLocal.class);
//
//    public CacheByLocal() {
//        this(1024);
//    }
//
//    public CacheByLocal(int maxSize) {
//        this.maxSize = maxSize;
//        log.info("正在使用基于JVM堆内存的缓存组件(*´▽｀)ノノ");
//
//        ScheduledFuture<?>[] futureRef = new ScheduledFuture[1];
//        futureRef[0] = T.execScheduled(() -> {
//            if (isStop) {
//                cache.clear();
//                futureRef[0].cancel(false); // 取消任务
//                return;
//            }
//            if (log.isDebugEnabled()) {
//                log.debug("清理过期缓存...");
//            }
//            cleanExpired();
//        }, 10L, DateUnit.SECOND);
//    }
//
//    @Override
//    public <T> void put(@NonNull Object key, T value) {
//        check();
//        put(key, value, -1, DateUnit.MS);
//    }
//
//    @Override
//    public <T> void put(@NonNull Object key, T value, long timeout, DateUnit unit) {
//        check();
//        if (unit.equals(DateUnit.MONTH) || unit.equals(DateUnit.YEAR)) {
//            throw new BaseException("月份或者年份的具体时间长度不确定，无法转换成毫秒数");
//        }
//
//        if (cache.size() >= maxSize) {
//            cleanExpired();
//            if (cache.size() >= maxSize) {
//                throw new BaseException("缓存容量已达上限：" + maxSize);
//            }
//        }
//
//        //将超时时间转换成毫秒数
//        long ms = unit.getMillis() * timeout;
//
//        String keyStr = key.toString();
//        CacheObj cacheObj = new CacheObj(keyStr, value, ms);
//        cache.put(keyStr, cacheObj);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T> T get(@NonNull Object key) {
//        check();
//        return (T) get(key, Object.class);
//    }
//
//    @Override
//    public <T> T get(@NonNull Object key, Class<T> type) {
//        check();
//        CacheObj value = cache.get(key.toString());
//        if (value == null || value.isExpired()) {
//            // 清理过期数据
//            cache.remove(key.toString());
//            return null;
//        }
//
//        return type.cast(value.value);
//    }
//
//    @Override
//    public <T> T get(@NonNull Object key, Class<T> type, Supplier<T> supplier, long timeout, DateUnit unit) {
//        check();
//        T value = get(key, type);
//        if (value == null) {
//            value = supplier.get();
//            put(key, value, timeout, unit);
//        }
//        return value;
//    }
//
//    public boolean extendTTL(@NonNull Object key, long timeout, DateUnit unit) {
//        check();
//        CacheObj value = cache.get(key.toString());
//        if (value == null || value.isExpired()) {
//            cache.remove(key.toString());
//            return false;
//        }
//
//        //延长时间
//        long now = System.currentTimeMillis();
//        value.expireAt = unit.getMillis() * timeout + now;
//        return true;
//    }
//
//    @Override
//    public void remove(@NonNull Object key) {
//        check();
//        cache.remove(key.toString());
//    }
//
//    @Override
//    public boolean contains(@NonNull Object key) {
//        check();
//        return cache.containsKey(key.toString());
//    }
//
//    @Override
//    public List<CacheObj> list(long pageNo, long pageSize) {
//        check();
//        //long offset = (pageNo - 1) * pageSize;
//
//        return cache.values().stream().map(val -> {
//            CacheObj cacheObj = new CacheObj();
//
//            cacheObj.setKey(val.getKey());
//            cacheObj.setCacheAt(val.getCacheAt());
//            cacheObj.setExpireAt(val.getExpireAt());
//
//            return cacheObj;
//        }).sorted((e1, e2) -> e2.cacheAt.compareTo(e1.cacheAt)).toList();
////        List<CacheObj> result = new ArrayList<>();
////
////        for (int i = 0; i < pageSize; i++) {
////            long current = offset + i;
////            if (current < cacheObjList.size()) {
////                CacheObj cacheObj = cacheObjList.get((int) current);
////                result.add(cacheObj);
////            } else {
////                break;
////            }
////        }
////        return cacheObjList;
//    }
//
//    @Override
//    public void clear() {
//        check();
//        cache.clear();
//    }
//
//    public void shutdown() {
//        isStop = true;
//    }
//
//    private void check() {
//        if (isStop) {
//            throw new BaseException("本地缓存已关闭");
//        }
//    }
//
//    private void cleanExpired() {
//        //删除数据
//        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
//    }
//
//    public static ICache getCache() {
//        return localCache;
//    }
//
//}
