package ${path}.${module}.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ytor.core.model.BaseReq;
import org.ytor.common.util.bean.Beans;
import io.swagger.v3.oas.annotations.media.Schema;
import ${path}.${module}.model.entity.${TableName};

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}Req表实体类
 * <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description ="${TableComment}")
public class ${TableName}Req extends BaseReq<${TableName}> {

<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    @Schema(description ="${field.columnComment}")
    private ${field.javaType} ${field.columnName};

</#list>

    @Override
    public ${TableName} toEntity() {
        return Beans.copyProperties(this, ${TableName}.class);
    }
}