package xyz.ytora.core.rbac.datascope.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;
import xyz.ytora.core.rbac.datascope.repo.SysRoleDataScopeRepo;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 角色-数据范围关系模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysRoleDataScopeLogic extends BaseLogic<SysRoleDataScope, SysRoleDataScopeRepo> {

}
