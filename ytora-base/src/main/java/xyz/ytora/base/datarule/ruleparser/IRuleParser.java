package xyz.ytora.base.datarule.ruleparser;

/**
 * 数据规则接口
 */
public interface IRuleParser {

    /**
     * 判断当前规则器是否支持指定规则类型
     */
    boolean support(String ruleType);

    /**
     * 将规则解析为 SQL 片段
     */
    String parse(String ruleField, String ruleType, String ruleValue);

}
