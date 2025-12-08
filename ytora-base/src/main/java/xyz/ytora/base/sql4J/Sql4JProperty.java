package xyz.ytora.base.sql4J;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.ytora.sql4j.caster.ITypeCaster;
import xyz.ytora.sql4j.log.ISqlLogger;
import xyz.ytora.sql4j.orm.TableCreatorManager;
import xyz.ytora.sql4j.translate.ITranslator;

import java.util.Map;
import java.util.Set;

/**
 * Sql4J属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "ytora.sql4j")
public class Sql4JProperty {

    /**
     * 主数据源
     */
    public static final String PRIMARY_KEY = "primary";

    /**
     * 实体类最左前缀的类路径（不支持 ant 风格的路径通配符）
     */
    private String entityPath;

    /**
     * Repo接口最左前缀的类路径（不支持 ant 风格的路径通配符）
     */
    private String repoPath;

    /**
     * 日志实现
     */
    private Class<? extends ISqlLogger> sqlLogImpl;

    /**
     * SQL 翻译器实现
     */
    private Class<? extends ITranslator> sqlTranslatorImp;

    /**
     * 从数据库读入数据时的类型转换器
     */
    private Class<? extends ITypeCaster> typeCasterImpl;

    /**
     * 当实体类对应表不存在时，是否创建表
     */
    private Boolean createTableIfNotExist = false;

    /**
     * 表创建器管理器
     */
    private Class<? extends TableCreatorManager> tableCreatorManagerImpl;

    /**
     * 多数据源
     */
    private Map<String, DataSourceProperties> dynamicDs;

    /**
     * 数据库连接池参数
     */
    private Map<String, Object> param;

    /**
     * 获取多数据源
     */
    public Map<String, DataSourceProperties> getDynamicDs() {
        if (dynamicDs == null) {
            throw new IllegalArgumentException("多数据源列表为空");
        }
        return dynamicDs;
    }

    /**
     * 设置多数据源
     */
    public void setMultipleDatasource(Map<String, DataSourceProperties> dynamicDs) {
        if (!dynamicDs.containsKey(PRIMARY_KEY)) {
            throw new IllegalArgumentException("多数据源必须要有一个名称为【" + PRIMARY_KEY + "】的数据源作为主数据");
        }
        this.dynamicDs = dynamicDs;
    }

    /**
     * 获取所有数据源名称
     */
    public Set<String> listDsNames() {
        return dynamicDs.keySet();
    }

    /**
     * 获取primary数据源
     */
    public DataSourceProperties getPrimaryDs() {
        return dynamicDs.get(PRIMARY_KEY);
    }

    /**
     * 获取指定名称的数据源
     */
    public DataSourceProperties getDsByName(String dataSourceName) {
        return dynamicDs.get(dataSourceName);
    }
}
