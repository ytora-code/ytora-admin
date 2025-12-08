//package xyz.ytora.base.dict.support;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import xyz.ytora.base.dict.IDictParser;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * created by yangtong on 2025/4/8 15:50:18
// * <br/>
// */
//@Component
//@RequiredArgsConstructor
//public class DefaultDictParseImpl implements IDictParser {
//
//    private final CommonApi commonApi;
//
//    private final SysDictLogic dictLogic;
//
//    @Override
//    public <T> List<Map<String, Object>> translate(List<T> datas) {
//        Map<String, String> dictMap = null;
//        List<Map<String, Object>> datasMap = datas.stream().map(Jsons::toMap).toList();
//
//        //标注了系统字典的字段，以及这些字段在集合中的所有字段值
//        //Map<String, Set<Object>> sysDictFieldValuesMap = new HashMap<>();
//        //标注了表字典的字段，以及这些字段在集合中的所有字段值
//        Map<String, Set<Object>> tableDictFieldValuesMap = new HashMap<>();
//
//        //解析字段配置
//        if (!datas.isEmpty()) {
//            //以第一个元素为样本，分析字典情况，并以此为基础对整个集合的字典字段进行解析
//            T firstEle = datas.getFirst();
//            dictMap = DictCache.get(firstEle.getClass());
//
//            for (String field : dictMap.keySet()) {
//                String realFieldName = field.substring(0, field.length() - 2);
//                //当前字典在集合中的所有值
//                Set<Object> dataValues = new HashSet<>();
//                for (Map<String, Object> dataMap : datasMap) {
//                    dataValues.add(dataMap.get(realFieldName));
//                }
//
//                //系统字典
//                if (field.endsWith("_1")) {
//                    //sysDictFieldValuesMap.put(field, dataValues);
//                }
//                //表字典
//                else if (field.endsWith("_2")) {
//                    tableDictFieldValuesMap.put(field, dataValues);
//                }
//            }
//        }
//
//        /*
//         * 对于表字典，应该避免重复查表，而是一次性将字段所有需要用到的值查出来
//         * {
//         *  sex:{
//         *      1:"男",
//         *      1:"女",
//         *  }
//         * }
//         */
//        Map<String, Map<String, Object>> allValuesOfFieldMap = new HashMap<>();
//        for (String dataKey : tableDictFieldValuesMap.keySet()) {
//            String tableDictStr = dictMap.get(dataKey);
//            //eg：sys_user,id,real_name
//            String[] tableDictArr = tableDictStr.split(",", 3);
//            if (tableDictArr.length == 3) {
//                String tableName = tableDictArr[0].trim();
//                String columnName = tableDictArr[1].trim();
//                String queryColumName = tableDictArr[2].trim();
//
//                //得到该字段在集合中的所有取值，然后一次性查表
//                List<Object> allValuesOfFields = tableDictFieldValuesMap.get(dataKey).stream().filter(Objects::nonNull).toList();
//                List<Map<String, Object>> maps = commonApi.queryColumn(tableName, columnName, allValuesOfFields, queryColumName);
//
//                //该字段的所有表字典，以及对于的取值
//                Map<String, Object> keyValueMap = new HashMap<>();
//                for (Map<String, Object> map : maps) {
//                    keyValueMap.put(String.valueOf(map.get("source_value")), map.get(queryColumName));
//                }
//                allValuesOfFieldMap.put(dataKey.substring(0, dataKey.length() - 2), keyValueMap);
//            }
//        }
//
//        //开始处理数据
//        for (Map<String, Object> dataMap : datasMap) {
//            //如果不需要字典处理，直接跳过
//            if (dictMap == null) {
//                continue;
//            }
//
//            //走到这，说明需要字典处理
//            List<String> fieldSet = dataMap.keySet().stream().toList();
//            //遍历当前元素的所有字段，判断是否应该对字段进行字典处理
//            for (String dataKey : fieldSet) {
//                //获取当前字段的值
//                Object dataValue = dataMap.get(dataKey);
//                String dataValueStr = dataValue == null ? "" : dataValue.toString();
//
//                String sysDictCode = dictMap.get(dataKey + "_1");
//                String tableDictStr = dictMap.get(dataKey + "_2");
//
//                //处理成系统字典
//                if (sysDictCode != null) {
//                    //系统字段是查的缓存，因此重复查询并无性能问题
//                    String dictItemText = dictLogic.parseDictText(sysDictCode, dataValueStr);
//                    dataMap.put(dataKey + "_dict", dictItemText);
//                }
//                //处理成表字典
//                else if (tableDictStr != null) {
//                    //eg：sys_user,id,real_name
//                    //获取当前字段的所有字典值
//                    Map<String, Object> keyMap = allValuesOfFieldMap.get(dataKey);
//                    dataMap.put(dataKey + "_dict", keyMap.get(dataValueStr));
//                }
//            }
//        }
//
//        return datasMap;
//    }
//
//    @Override
//    public <T> Map<String, Object> translate(T data) {
//        //最终返回的map，包含了data的键值对以及解析出来的字典键值对
//        Map<String, Object> result = new HashMap<>();
//
//        //解析字段配置
//        Map<String, String> dictMap = DictCache.get(data.getClass());
//
//        //解析对象字段
//        Map<String, Object> dataMap = Jsons.toMap(data);
//        for (String dataKey : dataMap.keySet()) {
//            Object dataValue = dataMap.get(dataKey);
//            result.put(dataKey, dataValue);
//
//            String dataValueStr = dataValue == null ? "" : dataValue.toString();
//
//            String sysDictCode = dictMap.get(dataKey + "_1");
//            String tableDictStr = dictMap.get(dataKey + "_2");
//
//            //判断当前字段是否标注了系统字典注解
//            if (sysDictCode != null) {
//                //eg：sexCode:1，就是要去字典表里面查询sexCode这个字典下，字典code=1的字典值
//                String dictItemText = dictLogic.parseDictText(sysDictCode, dataValueStr);
//                result.put(dataKey + "_dict", dictItemText);
//            }
//            //判断当前字段是否标注了表字典注解
//            else if (tableDictStr != null) {
//                //eg：sys_user,id,real_name
//
//                String[] tableDictArr = tableDictStr.split(",", 3);
//                if (tableDictArr.length != 3) {
//                    result.put(dataKey + "_dict_error_msg", "错误的表字典code:" + Arrays.toString(tableDictArr));
//                } else {
//                    String tableName = tableDictArr[0];
//                    String columnName = tableDictArr[1];
//                    String queryColumName = tableDictArr[2];
//                    List<Map<String, Object>> maps = commonApi.queryColumn(tableName, columnName, List.of(dataValueStr), queryColumName);
//                    if (maps.size() != 1) {
//                        String key = maps.stream().map(i -> i.get(dataValueStr)).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
//                        result.put(dataKey + "_dict_error_msg", "期待查到唯一的数据，却查到了：【" + key + "】");
//                    } else {
//                        Map<String, Object> map = maps.getFirst();
//                        Object dataItemText = map.get(queryColumName);
//                        result.put(dataKey + "_dict", dataItemText);
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
//
//}
