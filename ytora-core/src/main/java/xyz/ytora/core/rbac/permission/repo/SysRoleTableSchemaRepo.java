package xyz.ytora.core.rbac.permission.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleTableSchema;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 角色-表字段关系模块的持久层
 *
 * @author 杨桐
 * @since 1.0
 */
@Repository
public class SysRoleTableSchemaRepo extends BaseRepo<SysRoleTableSchema> {
}
