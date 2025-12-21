package xyz.ytora.core.rbac.user.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.entity.SysUserRole;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.sql4j.orm.IRepo;

import java.util.List;

/**
 * created by YT on 2025/12/8 22:27:34
 * <br/>
 */
@Repository
public abstract class SysUserRepo implements IRepo<SysUser> {

    public List<SysRole> listAllRoleByUserId(String userId) {
        return sqlHelper().select().from(SysRole.class)
                .where(w -> w.in(
                        SysRole::getId,
                        sqlHelper().select(SysUserRole::getRoleId)
                                .from(SysUserRole.class)
                                .where(ww -> ww.eq(SysUserRole::getUserId, userId)))
                ).submit(SysRole.class);
    }
}