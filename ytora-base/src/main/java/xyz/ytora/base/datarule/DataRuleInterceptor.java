package xyz.ytora.base.datarule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ytora.sql4j.interceptor.SqlInterceptorAdapter;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.SqlBuilder;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.sql4j.sql.select.SelectWhereStage;

/**
 * 数据规则，在构造 SQL 前改写 WHERE
 */
@Component
@RequiredArgsConstructor
public class DataRuleInterceptor extends SqlInterceptorAdapter {

    @Override
    public SqlBuilder beforeTranslate(SqlBuilder sqlBuilder) {
        if (sqlBuilder instanceof SelectBuilder selectBuilder) {
            SelectWhereStage whereStage = selectBuilder.getWhereStage();
            if (whereStage == null) {
                whereStage = new SelectWhereStage(selectBuilder, new ConditionExpressionBuilder(selectBuilder));
            }
            ConditionExpressionBuilder where = whereStage.getWhere();
            // 追加 WHERE

            return selectBuilder;
        }
        return sqlBuilder;
    }
}
