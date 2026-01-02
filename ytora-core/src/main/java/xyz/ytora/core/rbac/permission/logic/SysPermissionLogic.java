package xyz.ytora.core.rbac.permission.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.core.rbac.permission.repo.SysPermissionRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.ytool.tree.Trees;

import java.util.List;

/**
 * created by YT on 2025/12/21 16:25:59
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class SysPermissionLogic extends BaseLogic<SysPermission, SysPermissionRepo> {

    private final SQLHelper sqlHelper;

    /**
     * 获取指定角色的菜单
     */
    public List<SysPermissionResp> listAllMenu(List<String> roleIds) {
        List<SysPermission> menus = repository.listAllMenuByRoleId(roleIds);
        return Trees.toTree(menus.stream().map(SysPermission::toResp).toList());
    }

    /**
     * 获取指定角色的组件
     */
    public List<SysPermissionResp> listAllComponent(List<String> roleIds) {
        List<SysPermission> table = repository.listAllComponent(roleIds);
        return Trees.toTree(table.stream().map(SysPermission::toResp).toList());
    }

    public List<SysPermissionResp> list(String permissionCode) {
        List<SysPermission> list = sqlHelper.select(SysPermission.class).from(SysPermission.class).orderBy(SysPermission::getIndex, OrderType.ASC).submit(SysPermission.class);
        return Trees.toTree(list.stream().map(SysPermission::toResp).toList(), permissionCode);
    }
}
