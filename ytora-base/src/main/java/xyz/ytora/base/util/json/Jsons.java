package xyz.ytora.base.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.ytora.base.exception.BaseException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述
 *
 * <p>说明</p>
 *
 * @author ytora 
 * @since 1.0
 */
public class Jsons {

    /**
     * 全局唯一的 JsonMapper
     */
    private static volatile ObjectMapper objectMapper;

    // 预置的常用 TypeRef
    public static final TypeRef<Map<String, String>> MAP_OF_STRING_STRING = new TypeRef<>() {
    };
    public static final TypeRef<Map<String, Object>> MAP_OF_STRING_OBJECT = new TypeRef<>() {
    };

    private Jsons() {
    }

    /**
     * 注册自定义 JsonMapper（例如：注册全局 TypeAdapter、命名策略等）
     */
    public static void register(ObjectMapper objectMapper) {
        Jsons.objectMapper = objectMapper;
    }

    // ----------------------- 序列化 -----------------------

    public static <T> String toJsonStr(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new BaseException(e, "json序列化异常：{}", e.getMessage());
        }

    }

    // ----------------------- 反序列化 -----------------------

    public static <T> T fromJsonStr(String jsonStr, Class<T> type) {
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (JsonProcessingException e) {
            throw new BaseException(e, "json反序列化异常：{}", e.getMessage());
        }
    }

    public static <T> T fromJsonStr(String jsonStr, TypeRef<T> typeRef) {
        JavaType javaType = objectMapper.getTypeFactory().constructType(typeRef.type());
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            throw new BaseException(e, "json反序列化异常：{}", e.getMessage());
        }
    }

    // ----------------------- Map 互转 -----------------------

    /**
     * 对象 → Map<String,Object>
     */
    public static Map<String, Object> toMap(Object obj) {
        if (obj == null) {
            return Collections.emptyMap();
        }

        if (obj instanceof Map<?, ?> m) {
            // 已经是 Map，做一次类型安全转换
            Map<String, Object> result = new LinkedHashMap<>();
            for (Map.Entry<?, ?> e : m.entrySet()) {
                result.put(String.valueOf(e.getKey()), e.getValue());
            }
            return result;
        }

        try {
            return objectMapper.convertValue(obj, new TypeReference<>() {
            });
        } catch (IllegalArgumentException e) {
            throw new BaseException(e, "对象转换为Map失败：{}", e.getMessage());
        }
    }

    /**
     * Map<String,Object> → 对象
     */
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }

        // 如果本来就是目标类型，直接返回（避免不必要转换）
        if (clazz.isInstance(map)) {
            return clazz.cast(map);
        }

        try {
            return objectMapper.convertValue(map, clazz);
        } catch (IllegalArgumentException e) {
            throw new BaseException("Map 转换为对象失败: target=" + clazz.getName() + ", data=" + map, e);
        }
    }

    /**
     * Map<String,Object> → 对象（泛型版本）
     */
    public static <T> T fromMap(Map<String, Object> map, TypeRef<T> typeRef) {
        if (map == null) {
            return null;
        }

        try {
            JavaType javaType = objectMapper.getTypeFactory().constructType(typeRef.type());
            return objectMapper.convertValue(map, javaType);
        } catch (IllegalArgumentException e) {
            throw new BaseException("Map 转换为对象失败: target=" + typeRef.type().getTypeName() + ", data=" + map, e);
        }
    }

}
