package xyz.ytora.base.sql4J;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import xyz.ytora.base.scope.ScopedValueItem;

import java.util.NoSuchElementException;

/**
 * created by YT on 2025/9/24 13:15:51
 * <br/>
 * 动态数据源路由，本质是一个数据源，替换被Spring管理的默认数据源
 * 从数据源获取连接时，会根据当前线程上下文，选择合适的连接池对象获取连接
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        try {
            String dsName = ScopedValueItem.DS_CONTEXT.get();
            return dsName != null ? dsName : Sql4JProperty.PRIMARY_KEY;
        } catch (NoSuchElementException e) {
            return Sql4JProperty.PRIMARY_KEY;
        }
    }
}
