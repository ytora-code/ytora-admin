package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.ytool.str.Strs;

/**
 模糊查询，支持全模糊，左模糊，右模糊，name=*张三*，优先级仅次于等值查询
 */
@Component
public class LikeWhere extends AbsWhere {

    private static final String SUFFIX = "_le";

    @Override
    public Boolean isMatch(Token token) {
        String v = token.getValue();
        return Strs.isNotEmpty(v) && v.startsWith("*") && v.endsWith("*");
    }

    @Override
    public ConditionExpressionBuilder apply(ConditionExpressionBuilder where, Token token) {
        return positiveOrNegate(where, token);
    }

    @Override
    protected ConditionExpressionBuilder positive(ConditionExpressionBuilder where, Token token) {
        String col = Strs.toUnderline(token.getKey());
        String pat = token.getValue().substring(1, token.getValue().length() - 1);
        return where.like(Raw.of(Strs.toUnderline(col)), "%" + pat + "%");
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        String col = Strs.toUnderline(token.getKey());
        String pat = token.getValue().substring(1, token.getValue().length() - 1);
        return where.not().like(Raw.of(Strs.toUnderline(col)), "%" + pat + "%");
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE + 1;
    }
}
