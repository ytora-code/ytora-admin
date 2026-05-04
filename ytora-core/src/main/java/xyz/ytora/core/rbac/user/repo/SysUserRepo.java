package xyz.ytora.core.rbac.user.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.base.mvc.basemodel.BaseRepo;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.entity.SysUserRole;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.sqlux.orm.IRepo;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 用户模块的持久层
 *
 * @author ytora
 * @since 1.0
 */
@Repository
public class SysUserRepo extends BaseRepo<SysUser> {

    public List<SysRole> listAllRoleByUserId(String userId) {
        return select().from(SysRole.class)
                .where(w -> w.in(
                                SysRole::getId,
                                select(SysUserRole::getRoleId).from(SysUserRole.class).where(ww -> ww.eq(SysUserRole::getUserId, userId))
                        )
                ).submit(SysRole.class);
    }

}
