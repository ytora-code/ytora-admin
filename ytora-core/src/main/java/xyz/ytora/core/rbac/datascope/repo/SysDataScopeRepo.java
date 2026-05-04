package xyz.ytora.core.rbac.datascope.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 数据范围模块的持久层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Repository
public class SysDataScopeRepo extends BaseRepo<SysDataScope> {
}
