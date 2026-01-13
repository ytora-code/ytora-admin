package xyz.ytora.base.datarule.ruleparser.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.datarule.ruleparser.IRuleParser;

/**
 * created by YT on 2026/1/13 14:36:29
 * <br/>
 * 不等于
 */
@Component
public class NeRuleParser implements IRuleParser {
    @Override
    public boolean support(String ruleType) {
        return "<>".equals(ruleType) || "!=".equals(ruleType);
    }

    @Override
    public String parse(String ruleField, String ruleType, String ruleValue) {
        return ruleField + " <> '" + ruleValue + "'";
    }
}
