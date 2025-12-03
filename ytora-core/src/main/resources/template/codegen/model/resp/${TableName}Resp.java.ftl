package ${path}.${module}.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ytor.core.model.BaseResp;
import io.swagger.v3.oas.annotations.media.Schema;
import ${path}.${module}.model.${TableName}Mapper;
import ${path}.${module}.model.entity.${TableName};
import ${path}.${module}.model.excel.${TableName}Excel;

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}Resp表实体类
 * <br/>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description ="${TableComment}")
public class ${TableName}Resp extends BaseResp<${TableName}> {

<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    @Schema(description ="${field.columnComment}")
    private ${field.javaType} ${field.columnName};

</#list>

    @Override
    public ${TableName}Excel toExcel() {
        return ${TableName}Mapper.mapper.respToExcel(this);
    }
}