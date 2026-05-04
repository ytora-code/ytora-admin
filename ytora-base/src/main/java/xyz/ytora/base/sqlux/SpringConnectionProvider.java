package xyz.ytora.base.sqlux;

import jakarta.annotation.Resource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import xyz.ytora.sqlux.core.IConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 基于 Spring 的数据库连接提供者，支持事务
 *
 * @author ytora 
 * @since 1.0
 */
@Component
public class SpringConnectionProvider implements IConnectionProvider {

    @Resource
    public DataSource dataSource;

    @Override
    public Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public void closeConnection(Connection connection) {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

}
