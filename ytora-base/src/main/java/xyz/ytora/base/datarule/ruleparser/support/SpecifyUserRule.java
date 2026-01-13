package xyz.ytora.base.datarule.ruleparser.support;

import xyz.ytora.base.datarule.ruleparser.IRuleParser;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * created by YT on 2026/1/13 18:13:13
 * <br/>
 * 指定用户
 */
public class SpecifyUserRule implements IRuleParser {
    @Override
    public boolean support(String ruleType) {
        return "SpecifyUser".equalsIgnoreCase(ruleType);
    }

    @Override
    public String parse(String ruleField, String ruleType, String ruleValue) {
        // 指定用户时，ruleValue格式是：将多个userName以逗号分隔，形成字符串
        return "ruleValue IN (" + Arrays.stream(ruleValue.split(",")).map(userName -> "'" + userName.trim() + "'").collect(Collectors.joining(",")) + ")";
    }
}
