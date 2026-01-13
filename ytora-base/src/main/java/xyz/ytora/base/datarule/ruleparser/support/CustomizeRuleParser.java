package xyz.ytora.base.datarule.ruleparser.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.datarule.ruleparser.IRuleParser;

/**
 * created by YT on 2026/1/13 18:19:28
 * <br/>
 * 自定义
 */
@Component
public class CustomizeRuleParser implements IRuleParser {
    @Override
    public boolean support(String ruleType) {
        return "Customize".equalsIgnoreCase(ruleType);
    }

    @Override
    public String parse(String ruleField, String ruleType, String ruleValue) {
        // 自定义下，ruleField将作废，直接使用 ruleValue 作为 SQL 片段
        return ruleValue;
    }
}
