package xyz.ytora.base.datarule.ruleparser.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.datarule.ruleparser.IRuleParser;

/**
 * created by YT on 2026/1/13 14:36:29
 * <br/>
 * 小于
 */
@Component
public class StartWithRuleParser implements IRuleParser {
    @Override
    public boolean support(String ruleType) {
        return "startWith".equalsIgnoreCase(ruleType);
    }

    @Override
    public String parse(String ruleField, String ruleType, String ruleValue) {
        return ruleField + " LIKE '" + ruleValue + "%'";
    }
}
