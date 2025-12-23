package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.Wrapper;
import xyz.ytora.ytool.str.Strs;

/**
 * 大于等于  age_ge=23 -> age >= 23
 */
@Component
public class GeWhere extends AbsWhere {

    private static final String SUFFIX = "_ge";

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
        return where.ge(Raw.of(col), token.getValue());
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        String col = Strs.toUnderline(token.getKey().substring(0, token.getKey().length() - SUFFIX.length()));
        // >= 的反义词是 <
        return where.lt(Raw.of(col), token.getValue());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
