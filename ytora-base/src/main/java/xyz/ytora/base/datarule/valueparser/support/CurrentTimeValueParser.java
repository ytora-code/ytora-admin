package xyz.ytora.base.datarule.valueparser.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.datarule.valueparser.IValueParser;
import xyz.ytora.ytool.date.Dates;
import xyz.ytora.ytool.str.Placeholder;
import xyz.ytora.ytool.str.Strs;

import java.util.Date;
import java.util.Map;

/**
 * created by YT on 2026/1/13 18:26:06
 * <br/>
 * 解析${currentTime}
 */
@Component
public class CurrentTimeValueParser implements IValueParser {

    private final String KEY = "currentTime";

    @Override
    public String parser(String ruleValue) {
        Map<String, Object> data = Map.of(KEY, getValue());
        String value = Strs.parseByPlaceholder(ruleValue, data, Placeholder.LEFT_CURLY_BRACE_DOLLAR, Placeholder.RIGHT_CURLY_BRACE);
        return Strs.isEmpty(value) ? ruleValue : value;
    }

    @Override
    public Object getValue() {
        return Dates.formatDateTime(new Date());
    }
}
