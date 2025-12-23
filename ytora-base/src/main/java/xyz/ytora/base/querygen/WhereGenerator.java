package xyz.ytora.base.querygen;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import xyz.ytora.base.querygen.token.QueryTokenizer;
import xyz.ytora.base.querygen.token.Token;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * WHERE 条件产生器
 */
public class WhereGenerator {

    /**
     * 条件判断
     */
    private static List<AbsWhere> conditions;

    /**
     * 忽略请求中的这些字段
     */
    private static final List<String> ignoreFields = List.of("pageNo", "pageSize");

    public static ConditionExpressionBuilder where() {
        HttpServletRequest request = ScopedValueItem.REQUEST.get();
        String queryString = null;
        if (request != null) {
            queryString = request.getQueryString();
        }
        ConditionExpressionBuilder expression = new ConditionExpressionBuilder(null);
        if (queryString != null) {
            List<Token> tokens = Arrays.stream(queryString.split("&")).filter(i -> {
                String key = i.split("=")[0];
                return !ignoreFields.contains(key);
            }).map(QueryTokenizer::tokenize).toList();

            for (Token token : tokens) {
                for (AbsWhere queryCondition : getConditions()) {
                    if (queryCondition.isMatch(token)) {
                        queryCondition.apply(expression, token);
                        break;
                    }
                }
            }
        }
        return expression;
    }

    private static List<AbsWhere> getConditions() {
        if (conditions == null) {
            ApplicationContext context = ScopedValueItem.APPLICATION_CONTEXT.get();
            Map<String, AbsWhere> queryConditions = context.getBeansOfType(AbsWhere.class);
            conditions = queryConditions.values().stream().sorted(Comparator.comparingInt(Ordered::getOrder)).toList();
        }
        return conditions;
    }

}
