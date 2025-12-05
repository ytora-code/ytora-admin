//package xyz.ytora.base.cache.support;
//
//import lombok.NonNull;
//import xyz.ytora.ytool.date.DateUnit;
//import xyz.ytora.ytool.json.Jsons;
//
//import java.util.List;
//import java.util.function.Supplier;
//
///**
// * created by yangtong on 2025/4/4 下午9:00
// * <br/>
// * 基于JVM堆内存的缓存组件，存入缓存的数据都会被序列化
// */
//public class StringCacheByLocal extends CacheByLocal {
//
//    public StringCacheByLocal() {
//        super();
//    }
//
//    public StringCacheByLocal(int maxSize) {
//        super(maxSize);
//    }
//
//    @Override
//    public <T> void put(@NonNull Object key, T value) {
//        super.<String>put(key, Jsons.toJsonStr(value));
//    }
//
//    @Override
//    public <T> void put(@NonNull Object key, T value, long timeout, DateUnit unit) {
//        super.<String>put(key, Jsons.toJsonStr(value), timeout, unit);
//    }
//
//    @Override
//    public Object get(@NonNull Object key) {
//        String value = super.get(key);
//        return Jsons.fromJsonStr(value, Object.class);
//    }
//
//    @Override
//    public <T> T get(@NonNull Object key, Class<T> type) {
//        String jsonStr = super.<String>get(key);
//        return Jsons.fromJsonStr(jsonStr, type);
//    }
//
//    @Override
//    public <T> T get(@NonNull Object key, Class<T> type, Supplier<T> supplier, long timeout, DateUnit unit) {
//        String jsonStr = super.get(key, String.class);
//        if (jsonStr == null) {
//            T result = supplier.get();
//            super.put(key, Jsons.toJsonStr(result), timeout, unit);
//            return result;
//        }
//
//        return Jsons.fromJsonStr(jsonStr, type);
//    }
//
//
//    @Override
//    public List<CacheObj> list(long pageNo, long pageSize) {
//        return super.list(pageNo, pageSize);
//    }
//
//}
