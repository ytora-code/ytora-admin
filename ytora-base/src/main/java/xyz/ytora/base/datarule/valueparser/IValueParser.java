package xyz.ytora.base.datarule.valueparser;

/**
 * created by YT on 2026/1/13 18:23:52
 * <br/>
 */
public interface IValueParser {

    /**
     * 解析规则值，将里面的占位符翻译为合适的内容
     */
    String parser(String ruleValue);

    /**
     * 获取占位符的值
     */
    Object getValue();
}
