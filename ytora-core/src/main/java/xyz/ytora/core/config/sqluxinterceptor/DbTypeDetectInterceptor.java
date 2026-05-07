package xyz.ytora.core.config.sqluxinterceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.sqlux.CustomerDataSourceProperties;
import xyz.ytora.base.sqlux.SqluxProperty;
import xyz.ytora.sqlux.core.SQL;
import xyz.ytora.sqlux.core.SqluxContext;
import xyz.ytora.sqlux.core.enums.DbType;
import xyz.ytora.sqlux.interceptor.Interceptor;
import xyz.ytora.sqlux.interceptor.SqlRewriteContext;

/**
 * 检测本次SQL执行使用的数据库类型
 *
 * @author ytora
 * @since 1.0
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE + 1)
@Component
@RequiredArgsConstructor
public class DbTypeDetectInterceptor implements Interceptor {

    private final SqluxProperty sqluxProperty;

    @Override
    public void beforeTranslate(SqlRewriteContext context) {
        // 绑定了上下文，使用绑定的
        if (ScopedValueContext.DS_CONTEXT.isBound()) {
            String dsName = ScopedValueContext.DS_CONTEXT.get();
            setDbTypContext(dsName);
            return;
        }
        // 没绑定上下文，使用主数据库
        setDbTypContext(sqluxProperty.getPrimaryKey());
    }

    /**
     * 设置数据源类型上下文
     */
    private void setDbTypContext(String dsName) {
        CustomerDataSourceProperties cdp = sqluxProperty.getDynamicDs().get(dsName);
        SqluxContext.setDbType(cdp.getDbType());
    }
}
