package xyz.ytora.base.querygen;

import org.springframework.core.Ordered;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;

/**
 * 抽象的条件解释器
 */
public abstract class AbsWhere implements Ordered {
    /**
     * 判断当前解释器是否支持该 TOKEN
     */
    public abstract Boolean isMatch(Token token);

    /**
     * 将 TOKEN 拼接到 WhereBuilder
     */
    public abstract ConditionExpressionBuilder apply(ConditionExpressionBuilder where, Token token);

    /**
     * 正、逆逻辑分发
     */
    protected ConditionExpressionBuilder positiveOrNegate(ConditionExpressionBuilder where, Token token) {
        return token.isPositive() ? positive(where, token) : negate(where, token);
    }

    /**
     * 正向匹配（如 =、>、IN、LIKE 等）
     */
    protected abstract ConditionExpressionBuilder positive(ConditionExpressionBuilder where, Token token);

    /**
     * 逆向匹配（如 !=、NOT IN、NOT LIKE…）
     */
    protected abstract ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token);
}
