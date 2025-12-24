package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.ytool.str.Strs;

/**
 * 排序字段，固定字段名称：orderCol，+表示升序，-表示降序，orderCol=id+,userName- 表示先按id升序，再按userName降序
 */
@Component
public class OrderWhere extends AbsWhere {

    @Override
    public Boolean isMatch(Token token) {
        return token.getKey().equals("orderCol");
    }

    @Override
    public ConditionExpressionBuilder apply(ConditionExpressionBuilder where, Token token) {
        return positiveOrNegate(where, token);
    }

    @Override
    protected ConditionExpressionBuilder positive(ConditionExpressionBuilder where, Token token) {
        String[] orderCols = token.getValue().split(",");
        return where;
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        return where;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
