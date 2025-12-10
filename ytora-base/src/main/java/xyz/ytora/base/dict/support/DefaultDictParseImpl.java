package xyz.ytora.base.dict.support;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import xyz.ytora.base.cache.CacheType;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.dict.Dict;
import xyz.ytora.base.dict.IDictParser;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.SqlInfo;
import xyz.ytora.sql4j.sql.Wrapper;
import xyz.ytora.ytool.classcache.ClassCache;
import xyz.ytora.ytool.classcache.classmeta.ClassMetadata;
import xyz.ytora.ytool.classcache.classmeta.FieldMetadata;
import xyz.ytora.ytool.json.Jsons;
import xyz.ytora.ytool.str.Strs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * created by yangtong on 2025/4/8 15:50:18
 * <br/>
 */
@Component
@RequiredArgsConstructor
public class DefaultDictParseImpl implements IDictParser {

    private static final String DICT_CACHE_PREFIX = "DICT_";
    private final Caches caches;
    private final SQLHelper sqlHelper;

    @Override
    public <T> List<Map<String, Object>> translate(List<T> dataList) {
        List<Map<String, Object>> dataListMap = dataList.stream().map(Jsons::toMap).toList();

        //标注了系统字典的字段，以及这些字段在集合中的所有字段值
        Map<String, Set<Object>> sysDictFieldValuesMap = new HashMap<>();
        //标注了表字典的字段，以及这些字段在集合中的所有字段值
        Map<String, Set<Object>> tableDictFieldValuesMap = new HashMap<>();

        if (dataList.isEmpty()) {
            return Collections.emptyList();
        }

        //以第一个元素为样本，分析字典情况，并以此为基础对整个集合的字典字段进行解析
        T firstEle = dataList.getFirst();

        /*
         * 解析出来的结构类似：
         * {
            "sex_1":"sexCode",
            "name_2":"sys_user,id,real_name"
           }
         */
        Map<String, String> dictMap = parseDictMeta(firstEle.getClass());

        for (String field : dictMap.keySet()) {
            String realFieldName = field.substring(0, field.length() - 2);
            //当前字典在集合中的所有值
            Set<Object> dataValues = new HashSet<>();
            for (Map<String, Object> dataMap : dataListMap) {
                dataValues.add(dataMap.get(realFieldName));
            }

            //系统字典
            if (field.endsWith("_1")) {
                sysDictFieldValuesMap.put(field, dataValues);
            }
            //表字典
            else if (field.endsWith("_2")) {
                tableDictFieldValuesMap.put(field, dataValues);
            }
        }

        /*
         * 对于表字典，应该避免重复查表，而是一次性将字段所有需要用到的值查出来
         * {
         *  sex:{
         *      1:"男",
         *      1:"女",
         *  }
         * }
         */
        Map<String, Map<String, Object>> allValuesOfFieldMap = new HashMap<>();
        for (String dataKey : tableDictFieldValuesMap.keySet()) {
            String tableDictStr = dictMap.get(dataKey);
            //eg：sys_user,id,real_name
            String[] tableDictArr = tableDictStr.split(",", 3);
            if (tableDictArr.length == 3) {
                String tableName = tableDictArr[0].trim();
                String columnName = tableDictArr[1].trim();
                String queryColumName = tableDictArr[2].trim();

                //得到该字段在集合中的所有取值，然后一次性查表
                List<Object> allValuesOfFields = tableDictFieldValuesMap.get(dataKey).stream().filter(Objects::nonNull).toList();
                /*
                 * select 'queryColumName' as source_value, queryColumName from tableName where columnName in (allValuesOfFields)
                 */
                List<Map<String, Object>> maps = queryColumn(tableName, columnName, allValuesOfFields, queryColumName);

                //该字段的所有表字典，以及对于的取值
                Map<String, Object> keyValueMap = new HashMap<>();
                for (Map<String, Object> map : maps) {
                    keyValueMap.put(String.valueOf(map.get("source_value")), map.get(queryColumName));
                }
                allValuesOfFieldMap.put(dataKey.substring(0, dataKey.length() - 2), keyValueMap);
            }
        }

        //开始处理数据
        for (Map<String, Object> dataMap : dataListMap) {
            //走到这，说明需要字典处理
            List<String> fieldSet = dataMap.keySet().stream().toList();
            //遍历当前元素的所有字段，判断是否应该对字段进行字典处理
            for (String dataKey : fieldSet) {
                //获取当前字段的值
                Object dataValue = dataMap.get(dataKey);
                String dataValueStr = dataValue == null ? "" : dataValue.toString();

                String sysDictCode = dictMap.get(dataKey + "_1");
                String tableDictStr = dictMap.get(dataKey + "_2");

                //处理成系统字典
                if (sysDictCode != null) {
                    //系统字段是查的缓存，因此重复查询并无性能问题
                    String dictItemText = parseDictText(sysDictCode, dataValueStr);
                    dataMap.put(dataKey + "_dict", dictItemText);
                }
                //处理成表字典
                else if (tableDictStr != null) {
                    //eg：sys_user,id,real_name
                    //获取当前字段的所有字典值
                    Map<String, Object> keyMap = allValuesOfFieldMap.get(dataKey);
                    dataMap.put(dataKey + "_dict", keyMap.get(dataValueStr));
                }
            }
        }

        return dataListMap;
    }

    @Override
    public <T> Map<String, Object> translate(T data) {
        //最终返回的map，包含了data的键值对以及解析出来的字典键值对
        Map<String, Object> result = new HashMap<>();

        //解析字段配置
        Map<String, String> dictMap = parseDictMeta(data.getClass());

        //解析对象字段
        Map<String, Object> dataMap = Jsons.toMap(data);
        for (String dataKey : dataMap.keySet()) {
            Object dataValue = dataMap.get(dataKey);
            result.put(dataKey, dataValue);

            String dataValueStr = dataValue == null ? "" : dataValue.toString();

            String sysDictCode = dictMap.get(dataKey + "_1");
            String tableDictStr = dictMap.get(dataKey + "_2");

            //判断当前字段是否标注了系统字典注解
            if (sysDictCode != null) {
                //eg：sexCode:1，就是要去字典表里面查询sexCode这个字典下，字典code=1的字典值
                String dictItemText = parseDictText(sysDictCode, dataValueStr);
                result.put(dataKey + "_dict", dictItemText);
            }
            //判断当前字段是否标注了表字典注解
            else if (tableDictStr != null) {
                //eg：sys_user,id,real_name

                String[] tableDictArr = tableDictStr.split(",", 3);
                if (tableDictArr.length != 3) {
                    result.put(dataKey + "_dict_error_msg", "错误的表字典code:" + Arrays.toString(tableDictArr));
                } else {
                    String tableName = tableDictArr[0];
                    String columnName = tableDictArr[1];
                    String queryColumName = tableDictArr[2];
                    List<Map<String, Object>> maps = queryColumn(tableName, columnName, List.of(dataValueStr), queryColumName);
                    if (maps.size() != 1) {
                        String key = maps.stream().map(i -> i.get(dataValueStr)).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
                        result.put(dataKey + "_dict_error_msg", "期待查到唯一的数据，却查到了：【" + key + "】");
                    } else {
                        Map<String, Object> map = maps.getFirst();
                        Object dataItemText = map.get(queryColumName);
                        result.put(dataKey + "_dict", dataItemText);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 获取class的字典注解信息
     * _1后缀表示普通字典
     * _2后缀表示表字典
     * <pre />
     * eg：{
     *       "sex_1":"sexCode",
     *       "name_2":"sys_user,id,real_name"
     *     }
     */
    private <T> Map<String, String> parseDictMeta(Class<T> clazz) {
        Map<String, String> dictMap = caches.get(CacheType.LOCAL, DICT_CACHE_PREFIX + clazz.getName());
        if (dictMap != null) {
            return dictMap;
        }

        dictMap = new HashMap<>();
        ClassMetadata<T> classMetadata = ClassCache.get(clazz);
        List<FieldMetadata> dictFields = classMetadata.getFields(f -> f.hasAnnotation(Dict.class));
        for (FieldMetadata dictField : dictFields) {
            String name = dictField.getName();
            Dict dictAnno = dictField.getAnnotation(Dict.class);

            String table = dictAnno.table();
            String code = dictAnno.code();
            String text = dictAnno.text();

            //系统字典
            if (Strs.isEmpty(table)) {
                name = name + "_1";
                dictMap.put(name, code);
            }
            //表字典
            else {
                name = name + "_2";
                dictMap.put(name, table + "," + code + "," + text);
            }
        }
        caches.put(CacheType.LOCAL, DICT_CACHE_PREFIX + clazz.getName(), dictMap);

        return dictMap;
    }

    private List<Map<String, Object>> queryColumn(String table, String column, @NonNull List<?> value, String queryColumnArr) {
        return sqlHelper.select(Raw.of(column + " AS source_value"), Raw.of(queryColumnArr))
                .from(table)
                .where(w -> {
                    if (value.isEmpty()) {
                        w.ne(Wrapper.of(1), Wrapper.of(-1));
                    } else {
                        w.in(Raw.of(column), value);
                    }
                }).submit();
    }

    private String parseDictText(String dictCode, String dictItemValue) {
        // 1.先从缓存获取
        String key = DICT_CACHE_PREFIX + "::" + dictCode + "::" + dictItemValue;
        String dictItemText = caches.get(key);
        if (dictItemText != null) {
            return dictItemText;
        }

        // 2.缓存中没有，从数据库查询
        SqlInfo sqlInfo = sqlHelper.select(SysDict::getDictItemText).from(SysDict.class)
                .where(w -> w.eq(SysDict::getDictCode, dictCode).eq(SysDict::getDictItemValue, dictItemValue))
                .end();
        SysDict dict = sqlHelper.getSqlExecutionEngine().executeQuery(sqlInfo).toBean(SysDict.class);
        if (dict != null) {
            dictItemText = dict.getDictItemText();
            caches.put(key, dictItemText);
            return dictItemText;
        }
        return null;
    }
}
