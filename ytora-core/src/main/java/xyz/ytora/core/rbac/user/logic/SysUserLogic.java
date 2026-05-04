package xyz.ytora.core.rbac.user.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;

/**
 * 用户模块的业务逻辑层
 *
 * @author ytora
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysUserLogic extends BaseLogic<SysUser, SysUserRepo> {



}
