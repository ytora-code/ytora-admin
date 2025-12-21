package xyz.ytora.core.rbac.role.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRolePermission;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.orm.IRepo;

import java.util.List;

/**
 * created by YT on 2025/12/21 16:26:41
 * <br/>
 */
@Repository
public abstract class SysRoleRepo implements IRepo<SysRole> {
    public List<SysPermission> listAllPermissionByRoleId(String roleId) {
        return sqlHelper().select().from(SysPermission.class)
                .where(w -> w.in(
                        SysPermission::getId,
                        sqlHelper().select(SysRolePermission::getPermissionId)
                                .from(SysRolePermission.class)
                                .where(ww -> ww.eq(SysRolePermission::getRoleId, roleId)))
                )
                .orderBy(SysPermission::getIndex, OrderType.ASC)
                .submit(SysPermission.class);
    }
}
