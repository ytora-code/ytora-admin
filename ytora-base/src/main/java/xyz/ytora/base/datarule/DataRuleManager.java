package xyz.ytora.base.datarule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ytora.base.datarule.ruleparser.IRuleParser;
import xyz.ytora.base.datarule.valueparser.IValueParser;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.ytool.str.Strs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by YT on 2026/1/13 18:06:09
 * <br/>
 */
@Component
@RequiredArgsConstructor
public class DataRuleManager {

    private final List<IRuleParser> ruleParserList;
    private final List<IValueParser> valueParserList;

    public List<String> parseRules(List<Map<String, Object>> rules) {
        // 收集数据规则解析而出的 SQL 规则片段
        List<String> sqlSegList = new ArrayList<>(rules.size());
        for (Map<String, Object> rule : rules) {
            String ruleField = (String) rule.get("rule_field");
            String ruleType = (String) rule.get("rule_type");
            String ruleValue = (String) rule.get("rule_value");
            if (Strs.isEmpty(ruleType)) {
                throw new BaseException("规则类型不能为空");
            }
            // 解析 VALUE
            if (Strs.isNotEmpty(ruleValue)) {
                for (IValueParser valueParser : valueParserList) {
                    ruleValue = valueParser.parser(ruleValue);
                }
            }

            // 解析规则成 SQL 片段
            for (IRuleParser ruleParser : ruleParserList) {
                if (ruleParser.support(ruleType)) {
                    sqlSegList.add(ruleParser.parse(ruleField, ruleType, ruleValue));
                    break;
                }
            }
        }

        return sqlSegList;
    }

}
