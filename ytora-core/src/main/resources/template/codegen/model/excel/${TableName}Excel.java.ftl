package ${path}.${module}.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ytor.common.anno.Excel;
import lombok.experimental.Accessors;
import org.ytor.core.model.BaseExcel;
import org.ytor.common.util.bean.Beans;
import org.ytor.core.sqlflow.anno.Table;
import org.ytor.core.sqlflow.enums.IdType;
import org.ytor.core.model.PersistenceModel;
import io.swagger.v3.oas.annotations.media.Schema;
import ${path}.${module}.model.entity.${TableName};
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
public class ${TableName}Excel extends BaseExcel<${TableName}> {

<#list columnMetas as field>
    /**
     * ${field.columnComment}
     */
    @Excel("${field.columnComment}")
    @Schema(description ="${field.columnComment}")
    private ${field.javaType} ${field.columnName};

</#list>

    @Override
    public ${TableName} toEntity() {
        return ${TableName}Mapper.mapper.excelToEntity(this);
    }
}