package xyz.ytora.base.sqlux;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * 数据源属性扩展
 *
 * @author ytora 
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDataSourceProperties extends DataSourceProperties {

    /**
     * 数据源描述
     */
    private String desc;

}
