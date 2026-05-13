package xyz.ytora.core.rbac.permission.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleFormSchema;
import xyz.ytora.core.rbac.permission.repo.SysRoleFormSchemaRepo;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 角色-表单项关系模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysRoleFormSchemaLogic extends BaseLogic<SysRoleFormSchema, SysRoleFormSchemaRepo> {

}
