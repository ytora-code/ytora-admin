package xyz.ytora.base.sql4J;

import org.springframework.jdbc.datasource.DataSourceUtils;
import xyz.ytora.sql4j.core.IConnectionProvider;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * created by YT on 2025/12/5 22:55:05
 * <br/>
 * 基于 Spring 的数据库连接提供者，支持事务
 */
public record SpringConnectionProvider(DataSource dataSource) implements IConnectionProvider {

    @Override
    public Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public void closeConnection(Connection connection) {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }
}
