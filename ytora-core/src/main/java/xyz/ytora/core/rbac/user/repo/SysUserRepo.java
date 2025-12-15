package xyz.ytora.core.rbac.user.repo;

import org.springframework.stereotype.Repository;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.sql4j.orm.IRepo;

/**
 * created by YT on 2025/12/8 22:27:34
 * <br/>
 */
@Repository
public interface SysUserRepo extends IRepo<SysUser> {

    SysUser selectByUserNameAndId(String userName, Long id);

}