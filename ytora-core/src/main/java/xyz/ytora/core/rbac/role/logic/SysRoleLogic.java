package xyz.ytora.core.rbac.role.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.repo.SysRoleRepo;

import java.util.List;

/**
 * created by YT on 2025/12/21 16:25:59
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class SysRoleLogic extends BaseLogic<SysRole, SysRoleRepo> {
    public List<SysPermission> listAllPermission(String roleId) {
        return repository.listAllPermissionByRoleId(roleId);
    }
}
