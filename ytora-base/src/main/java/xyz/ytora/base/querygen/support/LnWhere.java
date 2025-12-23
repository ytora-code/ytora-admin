package xyz.ytora.base.querygen.support;

import org.springframework.stereotype.Component;
import xyz.ytora.base.querygen.AbsWhere;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.Wrapper;
import xyz.ytora.ytool.str.Strs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * in匹配 age_in=1,2,3 -> age in(1,2,3)
 */
@Component
public class LnWhere extends AbsWhere {
    @Override
    public Boolean isMatch(Token token) {
        return Strs.isNotEmpty(token.getValue()) && token.getKey().endsWith("_in");
    }

    @Override
    public ConditionExpressionBuilder apply(ConditionExpressionBuilder where, Token token) {
        return positiveOrNegate(where, token);
    }

    @Override
    protected ConditionExpressionBuilder positive(ConditionExpressionBuilder where, Token token) {
        String key = token.getKey();
        String col = Strs.toUnderline(key.substring(0, key.length() - 3));
        List<Object> values = Arrays.stream(token.getValue().split(","))
                .filter(Strs::isNotEmpty)
                .map(String::trim)
                .collect(Collectors.toList());
        return values.isEmpty() ? where : where.in(Raw.of(col), values);
    }

    @Override
    protected ConditionExpressionBuilder negate(ConditionExpressionBuilder where, Token token) {
        String key = token.getKey();
        String col = Strs.toUnderline(key.substring(0, key.length() - 3));
        List<Object> values = Arrays.stream(token.getValue().split(","))
                .filter(Strs::isNotEmpty)
                .map(String::trim)
                .collect(Collectors.toList());
        return values.isEmpty() ? where : where.not().in(Raw.of(col), values);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
