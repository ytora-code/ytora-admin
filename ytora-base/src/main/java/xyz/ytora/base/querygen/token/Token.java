package xyz.ytora.base.querygen.token;

import lombok.Data;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 分词结果封装类
 */
@Data
public class Token {
    /**
     * 匹配字段
     */
    private String key;
    /**
     * true: 使用"=", false: 使用"!="
     */
    private Boolean positive;
    /**
     * 匹配值
     */
    private String value;
    /**
     *
     * 字段类型
     */
    private Class<?> valueClass;

    public Token(String key, boolean positive, String value) {
        this.key = key;
        this.positive = positive;
        this.value = URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    public Boolean isPositive() {
        return positive;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + positive + ", " + value + ")";
    }
}