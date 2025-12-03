package ${path}.${module}.service.impl;

import org.ytor.core.sqlflow.Page;
import org.ytor.core.sqlflow.BaseLogic;
import org.ytor.core.sqlflow.SQLHelper;
import org.ytor.core.sqlflow.builder.support.WhereBuilder;
import org.ytor.core.sqlflow.executor.DMLResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${path}.${module}.model.entity.${TableName};
import ${path}.${module}.repository.${TableName}Repository;

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment}服务
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class ${TableName}Logic extends BaseLogic<${TableName}, ${TableName}Repository> {

}