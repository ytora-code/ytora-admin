package xyz.ytora.core.rbac.datascope.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.core.rbac.datascope.repo.SysRoleDataScopeGroupRepo;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 角色-数据范围分组关系模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysRoleDataScopeGroupLogic extends BaseLogic<SysRoleDataScopeGroup, SysRoleDataScopeGroupRepo> {

}
