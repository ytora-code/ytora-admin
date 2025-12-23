package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.Wrapper;
import xyz.ytora.ytool.str.Strs;

/**
 * 小于等于 age_lt=23 -> age <= 23
 */
@Component
public class LeWhere extends AbsWhere {

    private static final String SUFFIX = "_le";

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
        return where.le(Raw.of(Strs.toUnderline(col)), token.getValue());
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        String col = Strs.toUnderline(token.getKey().substring(0, token.getKey().length() - SUFFIX.length()));
        return where.gt(Raw.of(Strs.toUnderline(col)), token.getValue());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
