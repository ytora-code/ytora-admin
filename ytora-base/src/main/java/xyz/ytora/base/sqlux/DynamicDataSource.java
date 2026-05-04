package xyz.ytora.base.sqlux;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import xyz.ytora.base.scope.ScopedValueContext;

import java.util.NoSuchElementException;

/**
 * 动态数据源路由
 *
 * <p>本质是一个数据源，替换被Spring管理的默认数据源</p>
 * <p>从数据源获取连接时，会根据当前线程上下文，选择合适的连接池对象获取连接</p>
 *
 * @author ytora 
 * @since 1.0
 */
@AllArgsConstructor
public final class DynamicDataSource extends AbstractRoutingDataSource {

    private final SqluxProperty sqluxProperty;

    /**
     * Spring每次获取数据库连接时，都会先调用该方法获取数据源名称
     * @return 数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        try {
            String dsName = ScopedValueContext.DS_CONTEXT.get();
            return dsName != null ? dsName : sqluxProperty.primaryKey;
        } catch (NoSuchElementException e) {
            return sqluxProperty.primaryKey;
        }
    }

}
