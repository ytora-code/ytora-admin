package xyz.ytora.core.rbac.datascope.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 角色-数据范围关系模块的持久层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Repository
public class SysRoleDataScopeRepo extends BaseRepo<SysRoleDataScope> {
}
