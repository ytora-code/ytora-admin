package xyz.ytora.core.sqlux.datascope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.core.rbac.datascope.DataScopeType;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.sqlux.interceptor.Interceptor;
import xyz.ytora.sqlux.interceptor.SqlRewriteContext;
import xyz.ytora.sqlux.sql.condition.ExpressionBuilder;
import xyz.ytora.sqlux.sql.model.ColumnRef;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 数据范围拦截器。
 * <p>
 * 如果当前上下文存在{@link ScopedValueContext#DATA_SCOPE}，则需要为其添加数据权限
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataScopeInterceptor implements Interceptor {

    private static final String COLUMN_DEPART_CODE = "depart_code";
    private static final String COLUMN_CREATE_BY = "create_by";

    @Override
    public void beforeTranslate(SqlRewriteContext context) {
        if (!ScopedValueContext.DATA_SCOPE.isBound()) {
            return;
        }
        if (!ScopedValueContext.LOGIN_USER.isBound()) {
            return;
        }

        List<?> dataScopes = ScopedValueContext.DATA_SCOPE.get();
        if (dataScopes == null || dataScopes.isEmpty()) {
            return;
        }

        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();
        String departCode = loginUser.getDepartCode();
        String userName = loginUser.getUserName();

        List<Consumer<ExpressionBuilder>> conditions = new ArrayList<>();

        for (Object dataScope : dataScopes) {
            if (!(dataScope instanceof SysDataScope scope)) {
                continue;
            }

            DataScopeType dataScopeType = DataScopeType.fromCode(scope.getType());

            // 解析数据范围
            parseDataScope(scope, dataScopeType, departCode, conditions, userName);
        }

        if (conditions.isEmpty()) {
            return;
        }

        context.andWhere(w -> w.and(ww -> {
            boolean first = true;
            for (Consumer<ExpressionBuilder> condition : conditions) {
                if (first) {
                    condition.accept(ww);
                    first = false;
                } else {
                    ww.or(condition);
                }
            }
        }));
    }

    private void parseDataScope(SysDataScope scope, DataScopeType dataScopeType, String departCode, List<Consumer<ExpressionBuilder>> conditions, String userName) {
        // 查看当前主体（组织根）数据
        if (dataScopeType.equals(DataScopeType.ROOT_DEPART)) {
            String rootDepart = extractRootDepartCode(departCode);
            if (rootDepart != null && !rootDepart.isEmpty()) {
                conditions.add(w -> w.likeRight(ColumnRef.raw(COLUMN_DEPART_CODE), rootDepart));
            }
        }
        // 查看当前部门及子部门数据
        else if (dataScopeType.equals(DataScopeType.CRUEENT_DEPART_AND_CHILD)) {
            if (departCode != null && !departCode.isEmpty()) {
                conditions.add(w -> w.likeRight(ColumnRef.raw(COLUMN_DEPART_CODE), departCode));
            }
        }
        // 查看本人创建的数据
        else if (dataScopeType.equals(DataScopeType.SELF_CREATED)) {
            if (userName != null && !userName.isEmpty()) {
                conditions.add(w -> w.eq(ColumnRef.raw(COLUMN_CREATE_BY), userName));
            }
        }
        // 查看指定部门的数据
        else if (dataScopeType.equals(DataScopeType.ASSIGNED_DEPART)) {
            List<String> departCodes = splitCsv(scope.getValue());
            if (!departCodes.isEmpty()) {
                conditions.add(w -> w.in(ColumnRef.raw(COLUMN_DEPART_CODE), departCodes));
            }
        }
        // 查看指定人员创建的数据
        else if (dataScopeType.equals(DataScopeType.ASSIGNED_USER)) {
            List<String> userNames = splitCsv(scope.getValue());
            if (!userNames.isEmpty()) {
                conditions.add(w -> w.in(ColumnRef.raw(COLUMN_CREATE_BY), userNames));
            }
        }
        // 自定义SQL
        else if (dataScopeType.equals(DataScopeType.CUSTOM)) {
            String customSql = scope.getValue();
            if (customSql != null && !customSql.trim().isEmpty()) {
                conditions.add(w -> w.raw(customSql));
            }
        }
    }

    // 通过当前departCode获取主体code
    private String extractRootDepartCode(String departCode) {
        if (departCode == null || departCode.isEmpty()) {
            return null;
        }
        int index = departCode.indexOf("-");
        return index < 0 ? departCode : departCode.substring(0, index);
    }

    private List<String> splitCsv(String value) {
        if (value == null || value.trim().isEmpty()) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (String item : value.split(",")) {
            String trimmed = item.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }

}