package ${path}.${module}.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.ytor.common.util.bean.Beans;
import org.ytor.core.sqlflow.anno.Table;
import org.ytor.core.sqlflow.enums.IdType;
import org.ytor.core.model.PersistenceModel;
import io.swagger.v3.oas.annotations.media.Schema;
import ${path}.${module}.model.resp.${TableName}Resp;

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}表实体类
 * <br/>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description ="${TableComment}")
@Table(value = "${table_name}", idType = IdType.SNOWFLAKE)
public class ${TableName} extends PersistenceModel<${TableName}> {

<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    <#if field.isPrimaryKey>
    @TableId
    </#if>
    @Schema(description ="${field.columnComment}")
    private ${field.javaType} ${field.columnName};

</#list>

    @Override
    public ${TableName}Resp toResp() {
        return Beans.copyProperties(this, ${TableName}Resp.class);
    }
}