package xyz.ytora.core.rbac.permission.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.rbac.permission.model.entity.SysFormSchema;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 表格列结构模块的持久层
 *
 * @author 杨桐
 * @since 1.0
 */
@Repository
public class SysFormSchemaRepo extends BaseRepo<SysFormSchema> {
}
