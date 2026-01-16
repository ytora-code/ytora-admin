package xyz.ytora.base.sql4J;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * created by YT on 2026/1/17 02:37:50
 * <br/>
 * 数据源属性
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDataSourceProperties extends DataSourceProperties {

    /**
     * 数据源描述
     */
    private String desc;

}
