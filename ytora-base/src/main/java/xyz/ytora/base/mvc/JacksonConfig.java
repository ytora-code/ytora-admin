package xyz.ytora.base.mvc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ytora.base.util.json.Jsons;
import xyz.ytora.sqlux.core.json.SqluxJson;
import xyz.ytora.sqlux.orm.type.Json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson相关配置
 *
 * <p>说明</p>
 *
 * @author ytora 
 * @since 1.0
 */
@Configuration
public class JacksonConfig {

    /**
     * 自定义 Jackson 处理 json 时的行为
     */
    @Bean
    public SqluxJson customerJackson(ObjectMapper objectMapper) {
        // 先给本系统的json工具类注册一份ObjectMapper
        Jsons.register(objectMapper);

        // 然后基于ObjectMapper产生SqluxJson实现类，给Sqlux内部使用
        return new SqluxJson() {

            @Override
            public Object parse(String json) {
                if (json == null || json.trim().isEmpty()) {
                    return null;
                }
                try {
                    return objectMapper.readValue(json, Object.class);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Sqlux JSON解析失败: " + json, e);
                }
            }

            @Override
            public Object parse(String json, Type type) {
                if (json == null || json.trim().isEmpty()) {
                    return null;
                }
                if (type == null) {
                    return parse(json);
                }
                try {
                    JavaType javaType = objectMapper.getTypeFactory().constructType(type);
                    return objectMapper.readValue(json, javaType);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Sqlux JSON解析失败: " + json, e);
                }
            }

            @Override
            public String stringify(Object value) {
                if (value == null) {
                    return null;
                }
                try {
                    return objectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Sqlux JSON序列化失败: " + value, e);
                }
            }
        };
    }

    /**
     * 指定Jackson在处理LocalDateTime、LocalDate、LocalTime时的转换策略
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            // 定义格式
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter =
                    DateTimeFormatter.ofPattern("HH:mm:ss");

            // ===== LocalDateTime =====
            builder.serializerByType(LocalDateTime.class,
                    new LocalDateTimeSerializer(dateTimeFormatter));
            builder.deserializerByType(LocalDateTime.class,
                    new LocalDateTimeDeserializer(dateTimeFormatter));

            // ===== LocalDate =====
            builder.serializerByType(LocalDate.class,
                    new LocalDateSerializer(dateFormatter));
            builder.deserializerByType(LocalDate.class,
                    new LocalDateDeserializer(dateFormatter));

            // ===== LocalTime =====
            builder.serializerByType(LocalTime.class,
                    new LocalTimeSerializer(timeFormatter));
            builder.deserializerByType(LocalTime.class,
                    new LocalTimeDeserializer(timeFormatter));
        };
    }

    /**
     * 将 Json 类型的解析注册为一个 Module
     * @return Jackson模块
     */
    @Bean
    public Module sqluxJsonModule() {
        SimpleModule module = new SimpleModule("sqlux-json-module");
        module.addSerializer(Json.class, new SqluxJsonSerializer());
        module.addDeserializer(Json.class, new SqluxJsonDeserializer());
        return module;
    }

    /**
     * 自定义 Sqlux 内置类型：Json 的自定义序列化逻辑
     */
    public static class SqluxJsonSerializer extends JsonSerializer<Json> {

        @Override
        public void serialize(Json value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }

            gen.writeObject(value.unwrap());
        }
    }

    /**
     * 自定义 Sqlux 内置类型：Json 的自定义反序列化逻辑
     */
    public static class SqluxJsonDeserializer extends JsonDeserializer<Json> {

        @Override
        public Json deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            Object value = p.readValueAs(Object.class);
            return value == null ? null : Json.of(value);
        }
    }

}
