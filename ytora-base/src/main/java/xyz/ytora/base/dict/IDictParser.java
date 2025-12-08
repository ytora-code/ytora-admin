package xyz.ytora.base.dict;

import java.util.List;
import java.util.Map;

/**
 * 字典解析器
 */
public interface IDictParser {

    /**
     * 翻译字典
     */
    <T> List<Map<String, Object>> translate(List<T> data);

    /**
     * 翻译字典
     */
    <T> Map<String, Object> translate(T data);

}
