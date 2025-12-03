package ${path}.${module}.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ${path}.${module}.model.entity.${TableName};
import ${path}.${module}.model.excel.${TableName}Excel;
import ${path}.${module}.model.req.${TableName}Req;
import ${path}.${module}.model.resp.${TableName}Resp;

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * 类型转换
 */
@Mapper
public interface ${TableName}Mapper {
    ${TableName}Mapper mapper = Mappers.getMapper(${TableName}Mapper.class);

    ${TableName} reqToEntity(${TableName}Req req);

    ${TableName} excelToEntity(${TableName}Excel req);

    ${TableName}Resp entityToResp(${TableName} ${TableName});

    ${TableName}Excel respToExcel(${TableName}Resp resp);
}
