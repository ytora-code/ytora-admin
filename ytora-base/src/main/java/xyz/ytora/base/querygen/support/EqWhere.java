package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.Wrapper;
import xyz.ytora.ytool.str.Strs;

import java.util.function.Consumer;

/**
 * 等值匹配，age=1，优先级最高
 */
@Component
public class EqWhere extends AbsWhere {
    @Override
    public Boolean isMatch(Token token) {
        String k = token.getKey();
        String v = token.getValue();
        if (Strs.isEmpty(k) || Strs.isEmpty(v)) return false;
        if (k.endsWith("_gt") || k.endsWith("_ge") || k.endsWith("_lt") || k.endsWith("_le")
                || k.endsWith("_in") || k.endsWith("_or")) return false;
        return !v.startsWith("*") || !v.endsWith("*");
    }

    @Override
    public ConditionExpressionBuilder apply(ConditionExpressionBuilder where, Token token) {
        return positiveOrNegate(where, token);
    }

    @Override
    protected ConditionExpressionBuilder positive(ConditionExpressionBuilder where, Token token) {
        return where.eq(Raw.of(Strs.toUnderline(token.getKey())), token.getValue());
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        return where.ne(Raw.of(Strs.toUnderline(token.getKey())), token.getValue());
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
