package xyz.ytora.core.rbac.permission.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRolePermission;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.orm.IRepo;
import xyz.ytora.ytool.coll.Colls;

import java.util.Collections;
import java.util.List;

/**
 * created by YT on 2026/1/1 21:14:40
 * <br/>
 */
@Repository
public abstract class SysPermissionRepo implements IRepo<SysPermission> {

    /**
     * 根据 roleId 获取这些角色拥有的所有菜单
     */
    public List<SysPermission> listAllMenuByRoleId(List<String> roleIds) {
        if (Colls.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return sqlHelper().select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionType, 2)
                        .in(
                                SysPermission::getId,
                                sqlHelper().distinct().select(SysRolePermission::getPermissionId)
                                        .from(SysRolePermission.class)
                                        .where(ww -> ww.in(SysRolePermission::getRoleId, roleIds))
                        )
                )
                .orderBy(SysPermission::getIndex, OrderType.ASC)
                .submit(SysPermission.class);
    }

    /**
     * 根据 roleId 获取这些角色拥有的所有表格
     */
    public List<SysPermission> listComponentByType(List<String> roleIds, Integer... types) {
        if (Colls.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return sqlHelper().select().from(SysPermission.class)
                .where(w -> w
                        .in(SysPermission::getPermissionType, types)
                        .in(
                                SysPermission::getId,
                                sqlHelper().distinct().select(SysRolePermission::getPermissionId)
                                        .from(SysRolePermission.class)
                                        .where(ww -> ww.in(SysRolePermission::getRoleId, roleIds))
                        )
                )
                .orderBy(SysPermission::getIndex, OrderType.ASC)
                .submit(SysPermission.class);
    }
}
