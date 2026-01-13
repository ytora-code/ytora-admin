package xyz.ytora.base.datarule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.interceptor.SqlInterceptorAdapter;
import xyz.ytora.sql4j.sql.SqlBuilder;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.str.Strs;

import java.util.List;
import java.util.Map;

/**
 * 数据规则，在构造 SQL 前追加 WHERE
 */
@Component
@RequiredArgsConstructor
public class DataRuleInterceptor extends SqlInterceptorAdapter {

    private final DataRuleManager dataRuleManager;

    @Override
    public SqlBuilder beforeTranslate(SqlBuilder sqlBuilder) {
        if (sqlBuilder instanceof SelectBuilder selectBuilder) {
            // 追加 WHERE
            try {
                LoginUser loginUser = ScopedValueItem.LOGIN_USER.get();
                String rule = ScopedValueItem.DATA_RULE.get();
                if (Strs.isNotEmpty(rule)) {
                    // 获取该角色的数据规则
                    List<Map<String, Object>> list = SQLHelper.getInstance().submitSQL("""
                            SELECT * FROM sys_role_data_rule srdr
                            LEFT JOIN sys_data_rule sdr on srdr.rule_id = sdr.id
                            LEFT JOIN sys_permission sp on srdr.permission_id = sp.id
                            WHERE sp.permission_code = ?
                            """, rule).toBeans();

                    // 翻译为 SQL 片段
                    List<String> sqlSegList = dataRuleManager.parseRules(list);
                    String dataRuleSql = String.join(" OR ", sqlSegList);
                    selectBuilder.addWhere(w -> w.apply(dataRuleSql));
                }
            } catch (Exception e) {
                // 当前上下文没有数据权限注解
                System.out.println(e.getMessage());
            }

            return selectBuilder;
        }
        return sqlBuilder;
    }
}
