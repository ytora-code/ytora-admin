package xyz.ytora.core.rbac.permission.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionType;
import xyz.ytora.core.rbac.permission.model.data.SysRolePermissionDetail;
import xyz.ytora.core.rbac.permission.model.data.SysRolePermissionTree;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRolePermission;
import xyz.ytora.core.rbac.permission.model.param.SysRolePermissionParam;
import xyz.ytora.core.rbac.permission.repo.SysPermissionRepo;
import xyz.ytora.toolkit.collection.Colls;
import xyz.ytora.toolkit.tree.Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 用户模块的业务逻辑层
 *
 * @author ytora
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysPermissionLogic extends BaseLogic<SysPermission, SysPermissionRepo> {

    /**
     * 获取指定角色的菜单
     */
    public List<SysPermissionData> listAllMenu(List<String> roleIds) {
        List<SysPermission> menus = repository.listAllMenuByRoleId(roleIds);
        return Trees.toTree(menus.stream().map(SysPermission::toData).toList());
    }

    /**
     * 获取指定角色的组件
     */
    public SysPermissionType listAllComponent(List<String> roleIds) {
        // 获取所有页面元素
        List<SysPermission> componentList = repository.listComponentByType(roleIds, 3, 4, 5);
        List<SysPermissionData> allPermissions = componentList.stream().map(SysPermission::toData).toList();

        // 基础组件元素，包括一些独立的按钮，输入框等组件
        // 这里得到的 items 包含了全部的基础组件，只需要单独的组件元素，依附于table、form的组件需要在下面 findPermissionChildren 里进一步排除
        List<SysPermissionData> items = new ArrayList<>(allPermissions.stream().filter(i -> i.getPermissionType() == 3).toList());

        // 表格
        List<SysPermissionData> tables = allPermissions.stream().filter(i -> i.getPermissionType() == 4).toList();
        for (SysPermissionData table : tables) {
            table.setChildren(findPermissionChildren(table, allPermissions, items));
        }

        // 表单
        List<SysPermissionData> forms = allPermissions.stream().filter(i -> i.getPermissionType() == 5).toList();
        for (SysPermissionData form : forms) {
            form.setChildren(findPermissionChildren(form, allPermissions, items));
        }

        // TODO 将来可能引入其他组件类型
        SysPermissionType permissionType = new SysPermissionType();
        permissionType.setTables(tables);
        permissionType.setForms(forms);
        permissionType.setItems(items);
        return permissionType;
    }

    public List<SysPermissionData> tree(String permissionName) {
        List<SysPermission> list = select(SysPermission.class)
                .from(SysPermission.class)
                .where(w -> w.eq(SysPermission::getPermissionType, 2))
                .orderByAsc(SysPermission::getIndex)
                .submit(SysPermission.class);

        return Trees.toTree(list.stream().map(SysPermission::toData).toList(), (level, current, parent) -> {
            current.setLevel(level);
            current.setLevelKey("└" + "─".repeat(level + 1) + ">");
            if (parent != null) {
                current.setPName(parent.getPermissionName());
            }
        });
    }

    /**
     * 根据PID查询资源
     * @param pid 父资源ID
     * @return 指定PID下面所有子资源的数据
     */
    public List<SysPermissionData> listByPid(String pid) {
        List<SysPermission> list = repository
                .list(w -> w
                        .eq(SysPermission::getPid, pid)
                        .eq(SysPermission::getPermissionType, 2)
                );
        return list.stream().map(SysPermission::toData).toList();
    }

    /**
     * 从 allPermissions 中收集 permission 的子数据
     * 同时移除 items 里面依附于 table、form 的零散元素
     */
    private List<SysPermissionData> findPermissionChildren(SysPermissionData permission, List<SysPermissionData> allPermissions, List<SysPermissionData> items) {
        List<SysPermissionData> children = allPermissions.stream().filter(item -> item.getPid().equals(permission.getId())).toList();
        if (Colls.isEmpty(children)) {
            return null;
        }
        for (SysPermissionData child : children) {
            items.remove(child);
            child.setChildren(findPermissionChildren(child, allPermissions, items));
        }
        permission.setChildren(children);
        return children;
    }

    /**
     * 获取指定角色的资源树
     */
    public SysRolePermissionDetail treePermissionByRoleId(String roleId) {
        SysRolePermissionDetail rolePermissionResp = new SysRolePermissionDetail();
        // 获取所有资源
        List<SysRolePermissionTree> allPermission = select(SysPermission::getId, SysPermission::getPid, SysPermission::getPermissionName, SysPermission::getPermissionType)
                .from(SysPermission.class)
                .orderByAsc(SysPermission::getIndex)
                .submit(SysRolePermissionTree.class);

        // 转为TREE
        List<SysRolePermissionTree> tree = Trees.toTree(allPermission);
        rolePermissionResp.setTree(tree);

        // 获取指定角色的所有资源ID
        List<String> rolePermissions = select(SysRolePermission::getPermissionId).from(SysRolePermission.class).where(w -> w.eq(SysRolePermission::getRoleId, roleId)).submit(String.class);
        rolePermissionResp.setPermissionIds(rolePermissions);

        return rolePermissionResp;
    }

    /**
     * 更新角色-资源映射
     */
    @Transactional(rollbackFor = Exception.class)
    public void refreshRolePermission(SysRolePermissionParam param) {
        // 兜底
        List<String> origin = Optional.ofNullable(param.getOriginPermissionIds())
                .orElse(Collections.emptyList());
        List<String> current = Optional.ofNullable(param.getCurrentPermissionIds())
                .orElse(Collections.emptyList());

        // 是否有变更
        if (Colls.equals(origin, current)) {
            return;
        }

        // 新增
        List<String> addIds = Colls.diff(current, origin);

        // 删除
        List<String> removeIds = Colls.diff(origin, current);

        if (!addIds.isEmpty()) {
            List<SysRolePermission> list = addIds.stream().map(id -> {
                SysRolePermission rp = new SysRolePermission();
                rp.setRoleId(param.getRoleId());
                rp.setPermissionId(id);
                return rp;
            }).toList();
            insert(SysRolePermission.class)
                    .into(SysRolePermission::getRoleId, SysRolePermission::getPermissionId)
                    .values(list)
                    .submitBatch();
        }

        if (!removeIds.isEmpty()) {
            delete().from(SysRolePermission.class)
                    .where(w -> w.eq(SysRolePermission::getRoleId, param.getRoleId())
                            .in(SysRolePermission::getPermissionId, removeIds)
                    )
                    .submit();
        }
    }


}
