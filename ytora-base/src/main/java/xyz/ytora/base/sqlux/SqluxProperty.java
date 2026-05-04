package xyz.ytora.base.sqlux;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.ytora.sqlux.interceptor.log.SqlLogger;

import java.util.Map;

/**
 * Sqlux相关熟悉
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "ytora.sqlux")
public class SqluxProperty {

    /**
     * 主数据源
     */
    public String primaryKey = "primary";

    /**
     * 慢SQL阈值(毫秒)
     */
    private Long slowSqlThreshold;

    /**
     * 日志实现
     */
    private Class<? extends SqlLogger> sqlLoggerImpl;

    /**
     * 是否开启自动建表
     */
    private Boolean autoCreateTable = false;

    /**
     * 开启自动建表时，实体类所在路径
     */
    private String entityPath;

    /**
     * 多数据源
     */
    private Map<String, CustomerDataSourceProperties> dynamicDs;

    /**
     * 数据库连接池参数
     */
    private Map<String, Object> param;

}
