package xyz.ytora.core.rbac.permission.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRolePermission;
import xyz.ytora.toolkit.collection.Colls;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.distinct;
import static xyz.ytora.sqlux.core.SQL.select;

/**
 * 资源模块的持久层
 *
 * @author ytora
 * @since 1.0
 */
@Repository
public class SysPermissionRepo extends BaseRepo<SysPermission> {

    /**
     * 根据 roleId 获取这些角色拥有的所有菜单
     */
    public List<SysPermission> listAllMenuByRoleId(List<String> roleIds) {
        if (Colls.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionType, 2)
                        .in(
                                SysPermission::getId,
                                distinct().select(SysRolePermission::getPermissionId)
                                        .from(SysRolePermission.class)
                                        .where(ww -> ww.in(SysRolePermission::getRoleId, roleIds))
                        )
                )
                .orderByAsc(SysPermission::getIndex)
                .submit(SysPermission.class);
    }

    /**
     * 根据 roleId 获取这些角色拥有的所有表格
     */
    public List<SysPermission> listComponentByType(List<String> roleIds, Integer... types) {
        if (Colls.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return select().from(SysPermission.class)
                .where(w -> w
                        .in(SysPermission::getPermissionType, (Object) types)
                        .in(
                                SysPermission::getId,
                                distinct().select(SysRolePermission::getPermissionId)
                                        .from(SysRolePermission.class)
                                        .where(ww -> ww.in(SysRolePermission::getRoleId, roleIds))
                        )
                )
                .orderByAsc(SysPermission::getIndex)
                .submit(SysPermission.class);
    }

}
