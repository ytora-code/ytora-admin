package xyz.ytora.core.rbac.permission.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.core.rbac.datarule.model.entity.SysDataRule;
import xyz.ytora.core.rbac.datarule.model.entity.SysRoleDataRule;
import xyz.ytora.core.rbac.datarule.model.req.SysRoleDataRuleReq;
import xyz.ytora.core.rbac.datarule.model.resp.SysRoleDataRuleResp;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRolePermission;
import xyz.ytora.core.rbac.permission.model.req.SysRolePermissionReq;
import xyz.ytora.core.rbac.permission.model.resp.SysPermissionResp;
import xyz.ytora.core.rbac.permission.model.resp.SysRolePermissionResp;
import xyz.ytora.core.rbac.permission.model.resp.SysRolePermissionTreeResp;
import xyz.ytora.core.rbac.permission.repo.SysPermissionRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.sql.insert.IntoStage;
import xyz.ytora.sql4j.util.OrmUtil;
import xyz.ytora.ytool.coll.Colls;
import xyz.ytora.ytool.str.Strs;
import xyz.ytora.ytool.tree.Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public List<SysPermissionResp> tree(String permissionName) {
        List<SysPermission> list = sqlHelper.select(SysPermission.class).from(SysPermission.class).orderBy(SysPermission::getIndex, OrderType.ASC).submit(SysPermission.class);
        return Trees.toTree(list.stream().map(SysPermission::toResp).toList(), permissionName, (level, current, parent) -> {
            current.setLevel(level);
            current.setLevelKey("└" + "─".repeat(level + 1) + ">");
            if (parent != null) {
                current.setPName(parent.getPermissionName());
            }
        });
    }

    /**
     * 获取指定角色的资源树
     */
    public SysRolePermissionResp treePermissionByRoleId(String roleId) {
        SysRolePermissionResp rolePermissionResp = new SysRolePermissionResp();
        // 获取所有资源
        List<SysRolePermissionTreeResp> allPermission = sqlHelper
                .select(SysPermission::getId, SysPermission::getPid, SysPermission::getPermissionName, SysPermission::getPermissionType)
                .from(SysPermission.class)
                .orderBy(SysPermission::getIndex, OrderType.ASC)
                .submit(SysRolePermissionTreeResp.class);

        // 转为TREE
        List<SysRolePermissionTreeResp> tree = Trees.toTree(allPermission);
        rolePermissionResp.setTree(tree);

        // 获取指定角色的所有资源ID
        List<String> rolePermissions = sqlHelper.select(SysRolePermission::getPermissionId).from(SysRolePermission.class).where(w -> w.eq(SysRolePermission::getRoleId, roleId)).submit(String.class);
        rolePermissionResp.setPermissionIds(rolePermissions);

        return rolePermissionResp;
    }

    /**
     * 更新角色-资源映射
     */
    @Transactional(rollbackFor = Exception.class)
    public void refreshRolePermission(SysRolePermissionReq rolePermissionReq) {
        // 兜底
        List<String> origin = Optional.ofNullable(rolePermissionReq.getOriginPermissionIds())
                .orElse(Collections.emptyList());
        List<String> current = Optional.ofNullable(rolePermissionReq.getCurrentPermissionIds())
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
            IntoStage insert = sqlHelper.insert(SysRolePermission.class).into(SysRolePermission::getRoleId, SysRolePermission::getPermissionId);
            List<List<Object>> valuesList = new ArrayList<>();
            for (String id : addIds) {
                valuesList.add(List.of(rolePermissionReq.getRoleId(), id));
            }
            insert.values(valuesList).submit();
        }

        if (!removeIds.isEmpty()) {
            sqlHelper.delete().from(SysRolePermission.class)
                    .where(
                            w -> w.eq(SysRolePermission::getRoleId, rolePermissionReq.getRoleId())
                                    .in(SysRolePermission::getPermissionId, removeIds)
                    ).submit();
        }
    }

    /**
     * 获取指定资源的数据规则
     */
    public List<SysDataRule> listDataRule(String permissionId) {
        List<SysDataRule> dataRules = sqlHelper.select().from(SysDataRule.class)
                .where(w -> w.eq(SysDataRule::getPermissionId, permissionId))
                .orderBy(SysDataRule::getId, OrderType.ASC)
                .submit(SysDataRule.class);
        for (SysDataRule dataRule : dataRules) {
            if (Strs.isEmpty(dataRule.getRuleField())) {
                dataRule.setRuleField("-");
            }
            if (Strs.isEmpty(dataRule.getRuleValue())) {
                dataRule.setRuleValue("-");
            }
        }
        return dataRules;
    }

    /**
     * 新增或编辑指定资源的数据规则
     */
    public void addOrUpdateDataRule(SysDataRule data) {
        if (Strs.isEmpty(data.getId())) {
            OrmUtil.insert(SysDataRule.class, data);
        } else {
            OrmUtil.update(SysDataRule.class, data, w -> w.eq(SysDataRule::getId, data.getId()));
        }
    }

    /**
     * 删除指定数据规则
     */
    public void deleteDataRule(String id) {
        sqlHelper.delete().from(SysDataRule.class).where(w -> w.eq(SysDataRule::getId, id)).submit();
    }

    /**
     * 获取指定角色的数据规则信息
     */
    public SysRoleDataRuleResp listRoleDataRule(String roleId, String permissionId) {
        // 获取指定资源的数据规则
        List<SysDataRule> dataRules = listDataRule(permissionId);

        // 获取指定角色在指定资源上的数据规则
        List<String> ruleIds = sqlHelper.select(SysRoleDataRule::getRuleId).from(SysRoleDataRule.class)
                .where(w -> w.eq(SysRoleDataRule::getRoleId, roleId).eq(SysRoleDataRule::getPermissionId, permissionId))
                .submit(String.class);

        SysRoleDataRuleResp roleDataRule = new SysRoleDataRuleResp();
        roleDataRule.setDataRules(dataRules);
        roleDataRule.setRuleIds(ruleIds);
        return roleDataRule;
    }

    /**
     * 更新角色-数据规则
     */
    public void refreshRoleDataRule(SysRoleDataRuleReq roleDataRuleReq) {
        // 兜底
        List<String> origin = Optional.ofNullable(roleDataRuleReq.getOriginDataRuleIds())
                .orElse(Collections.emptyList());
        List<String> current = Optional.ofNullable(roleDataRuleReq.getCurrentDataRuleIds())
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
            List<SysRoleDataRule> list = new ArrayList<>();
            for (String addId : addIds) {
                SysRoleDataRule roleDataRule = new SysRoleDataRule();
                roleDataRule.setRuleId(addId);
                roleDataRule.setRoleId(roleDataRuleReq.getRoleId());
                roleDataRule.setPermissionId(roleDataRuleReq.getPermissionId());
                list.add(roleDataRule);
            }
            OrmUtil.insert(SysRoleDataRule.class, list);
        }

        if (!removeIds.isEmpty()) {
            sqlHelper.delete().from(SysRoleDataRule.class)
                    .where(
                            w -> w.eq(SysRoleDataRule::getRoleId, roleDataRuleReq.getRoleId())
                                    .eq(SysRoleDataRule::getPermissionId, roleDataRuleReq.getPermissionId())
                                    .in(SysRoleDataRule::getRuleId, removeIds)
                    ).submit();
        }
    }
}
