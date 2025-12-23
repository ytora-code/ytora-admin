package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.ytool.str.Strs;

/**
 * 等值匹配，age=1，优先级最高
 */
@Component
public class BetweenAndWhere extends AbsWhere {

    private static final String SUFFIX = "_between";

    @Override
    public Boolean isMatch(Token token) {
        return Strs.isNotEmpty(token.getValue()) && token.getKey().endsWith(SUFFIX);
    }

    @Override
    public ConditionExpressionBuilder apply(ConditionExpressionBuilder where, Token token) {
        return positiveOrNegate(where, token);
    }

    @Override
    protected ConditionExpressionBuilder positive(ConditionExpressionBuilder where, Token token) {
        String col = Strs.toUnderline(token.getKey().substring(0, token.getKey().length() - SUFFIX.length()));
        String[] values = token.getValue().split(",");
        if (values.length != 2) {
            throw new BaseException("参数为between模式时，必须以逗号分隔value且有两个value");
        }
        String start = values[0];
        String end = values[1];
        return where.betweenAnd(Raw.of(Strs.toUnderline(col)), start, end);
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        String col = Strs.toUnderline(token.getKey().substring(0, token.getKey().length() - SUFFIX.length()));
        String[] values = token.getValue().split(",");
        if (values.length != 2) {
            throw new BaseException("参数为between模式时，必须以逗号分隔value且有两个value");
        }
        String start = values[0];
        String end = values[1];
        return where.not().betweenAnd(Raw.of(Strs.toUnderline(col)), start, end);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
