package xyz.ytora.core.sys.dict.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import xyz.ytora.base.cache.CacheType;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.util.json.Jsons;
import xyz.ytora.core.sys.dict.Dict;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.toolkit.collection.Colls;
import xyz.ytora.toolkit.reflect.classcache.ClassCache;
import xyz.ytora.toolkit.text.Strs;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static xyz.ytora.sqlux.sql.func.SqlFuncAggregation.alias;

/**
 * 字典解析器
 *
 * @author ytora 
 * @since 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DictParser {

    private static final String DICT_CACHE_PREFIX = "DICT";
    private final Caches caches;

    /**
     * 翻译对象集合
     * @param dataList 对象集合
     * @return 翻译结果
     * @param <T> 对象类型
     */
    public <T> List<Map<String, Object>> translate(Iterable<T> dataList) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (T next : dataList) {
            list.add(translate(next));
        }
        return list;
    }

    /**
     * 翻译单个对象
     * @param data 对象
     * @return 翻译结果
     * @param <T> 对象类型
     */
    public <T> Map<String, Object> translate(T data) {
        if (data == null) {
            return Collections.emptyMap();
        }
        if (ClassCache.isPlatformType(data.getClass())) {
            throw new IllegalArgumentException("无法对类型[" + data.getClass().getName() + "]进行字典翻译");
        }

        Map<String, Object> dataMap = Jsons.toMap(data);
        Map<String, String> dictMetaMap = parseDictMeta(data.getClass());
        for (String key : dictMetaMap.keySet()) {
            String dictResult;
            String dictMeta = dictMetaMap.get(key);
            // 查询当前字典的真实值
            String realKey = key.substring(0, key.length() - 2);
            Object values = dataMap.get(realKey);

            try {
                if (!(values instanceof String strValues)) {
                    continue;
                }
                if (Strs.isEmpty(strValues)) {
                    continue;
                }
                Set<String> valueSet = Arrays.stream(strValues.split(",")).collect(Collectors.toSet());

                Map<String, String> translateResult;
                // 普通字典：sexCode
                if (key.endsWith("_1")) {
                    // 翻译结果
                    translateResult = translateDict(dictMeta, valueSet);
                }
                // 表字典：sys_user,id,real_name
                else if (key.endsWith("_2")) {
                    String[] tableDictInfo = dictMeta.split(",", 3);
                    translateResult = translateTableDict(tableDictInfo[0], tableDictInfo[1], valueSet, tableDictInfo[2]);
                }
                // 未知字典
                else {
                    translateResult = Collections.emptyMap();
                }

                dictResult = valueSet.stream().map(translateResult::get).filter(Objects::nonNull).collect(Collectors.joining(","));
            } catch (Exception e) {
                dictResult = "字典翻译失败:" + e.getMessage();
            }

            dataMap.put(realKey + "_DICT", dictResult);
        }

        return dataMap;
    }

    /**
     * 获取class的字典注解信息
     *
     * <p>_1后缀表示普通字典</p>
     * <p>_2后缀表示表字典</p>
     *
     * <pre><code>
     *  eg：{
     *          // 普通字典
     *          "sex_1":"sexCode",
     *          // 表级字典
     *          "name_2":"sys_user,id,real_name"
     *      }
     * </code></pre>
     */
    private <T> Map<String, String> parseDictMeta(Class<T> clazz) {
        String dictKey = DICT_CACHE_PREFIX + "::" + "clazz" + "::" + clazz.getName();
        Map<String, String> dictMap = caches.get(CacheType.LOCAL, dictKey);
        if (dictMap != null) {
            return dictMap;
        }

        dictMap = new HashMap<>();

        // 获取该类型中所有标注了Dict注解的字段
        List<Field> fieldList = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Dict.class))
                .toList();
        for (Field dictField : fieldList) {
            String name = dictField.getName();
            Dict dictAnno = dictField.getAnnotation(Dict.class);

            String table = dictAnno.table();
            String code = dictAnno.code();
            String text = dictAnno.text();

            //表字典
            if (Strs.isNotEmpty(table) && Strs.isNotEmpty(code) && Strs.isNotEmpty(text)) {
                name = name + "_2";
                dictMap.put(name, table + "," + code + "," + text);
            }
            //系统字典
            else {
                name = name + "_1";
                dictMap.put(name, code);
            }
        }

        caches.put(CacheType.LOCAL, dictKey, dictMap);
        return dictMap;
    }

    /**
     * 翻译普通字典
     *
     * <p>从字典中获取指定value字典项的text</p>
     * <pre><code>
     *     "DICT::sex": {
     *         "0": "女",
     *         "1": "男"
     *     }
     * </code></pre>
     *
     * @param dictCode 字典code
     * @param itemValueSet 字典项value，可传多个value
     * @return 字典项的text
     */
    private Map<String, String> translateDict(String dictCode, Set<String> itemValueSet) {
        try {
            if (Colls.isEmpty(itemValueSet)) {
                return Collections.emptyMap();
            }

            String key = DICT_CACHE_PREFIX + "::" + dictCode;
            Map<String, String> dictTextMap = caches.get(key);
            if (dictTextMap != null) {
                return dictTextMap;
            }

            List<Map<String, Object>> list = SQL.select(
                            alias(SysDictItem::getItemValue).as("value"),
                            alias(SysDictItem::getItemText).as("text")
                    )
                    .from(SysDictItem.class)
                    .where(w -> w.in(SysDictItem::getItemValue, itemValueSet))
                    .submit();
            Map<String, String> newDictTextMap = new HashMap<>();
            for (Map<String, Object> map : list) {
                newDictTextMap.put(String.valueOf(map.get("value")), String.valueOf(map.get("text")));
            }
            caches.put(CacheType.LOCAL, key, newDictTextMap);
            return newDictTextMap;
        } catch (Exception e) {
            log.error("字典翻译失败", e);
            return Map.of("error", "字典翻译失败:" + e.getMessage());
        }

    }

    /**
     * 翻译表级字典
     *
     * <pre><code>
     *     "DICT::sys_user::id::real_name": {
     *         "1": "张三",
     *         "2": "李四"，
     *         "3": "王五"，
     *         "4": "陈留"
     *     }
     * </code></pre>
     *
     * @param table 表名称
     * @param column 列字典
     * @param values 列的值
     * @param targetColumn 要翻译的目标列字典
     * @return 翻译结果
     */
    private Map<String, String> translateTableDict(String table, String column, @NonNull Set<String> values, String targetColumn) {
        try {
            String key = DICT_CACHE_PREFIX + "::" + table + "::" + column + "::" + targetColumn;
            Map<String, String> dictTextMap = caches.get(CacheType.LOCAL, key);
            if (dictTextMap == null) {
                dictTextMap = new HashMap<>();
            }
            if (dictTextMap.keySet().containsAll(values)) {
                return dictTextMap;
            }
            // 尚未缓存的值：["1","2","3"]
            Set<String> nonCacheValue = new HashSet<>();
            // 先获取已经缓存的值
            for (String value : values) {
                String data = dictTextMap.get(value);
                if (data != null) {
                    dictTextMap.put(value, data);
                    continue;
                }
                nonCacheValue.add(value);
            }

            // 尚未缓存的值去查询数据库
            String p = nonCacheValue.stream().map(i -> "?").collect(Collectors.joining(", "));
            List<Map<String, Object>> list = SQL
                    .rawQuery(Strs.format("select {}, {} from {} where {} in ({})", column, targetColumn, table, column, p), nonCacheValue)
                    .submit();
            Map<String, String> newDictTextMap = new HashMap<>();
            for (Map<String, Object> map : list) {
                newDictTextMap.put(String.valueOf(map.get(column)), String.valueOf(map.get(targetColumn)));
            }
            dictTextMap.putAll(newDictTextMap);
            caches.put(CacheType.LOCAL, key, dictTextMap);

            return dictTextMap;
        } catch (Exception e) {
            log.error("字典翻译失败", e);
            return Map.of("error", "字典翻译失败:" + e.getMessage());
        }
    }

}
