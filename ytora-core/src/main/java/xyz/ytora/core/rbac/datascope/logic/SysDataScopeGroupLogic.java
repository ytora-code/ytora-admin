package xyz.ytora.core.rbac.datascope.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeGroupParam;
import xyz.ytora.core.rbac.datascope.repo.SysDataScopeGroupRepo;
import xyz.ytora.toolkit.collection.Colls;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 数据范围组模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysDataScopeGroupLogic extends BaseLogic<SysDataScopeGroup, SysDataScopeGroupRepo> {

    public SysDataScopeGroup queryGroupByCode(String code) {
        return repository.one(w -> w.eq(SysDataScopeGroup::getCode, code));
    }

    /**
     * 查询指定角色在指定资源下的分组
     *
     * @param roleId 决定ID
     * @param permissionId 资源ID
     * @return 拥有的分组ID
     */
    public List<String> listGroup(String roleId, String permissionId) {
        List<SysRoleDataScopeGroup> groupList = select().from(SysRoleDataScopeGroup.class)
                .where(w -> w.eq(SysRoleDataScopeGroup::getRoleId, roleId)
                        .eq(SysRoleDataScopeGroup::getPermissionId, permissionId)
                ).submit(SysRoleDataScopeGroup.class);
        return groupList.stream().map(SysRoleDataScopeGroup::getGroupId).toList();
    }

    /**
     * 查询指定角色在指定资源下的分组
     * @param param 请求参数
     */
    public void refreshRoleGroup(SysRoleDataScopeGroupParam param) {
        // 目前的分组ID
        List<String> latestGroupIds = listGroup(param.getRoleId(), param.getPermissionId());

        // 计算要移除绑定的分组
        List<String> removeGroupIds = Colls.diff(latestGroupIds, param.getGroupIds());
        if (Colls.isNotEmpty(removeGroupIds)) {
            delete().from(SysRoleDataScopeGroup.class).where(w -> w
                    .eq(SysRoleDataScopeGroup::getRoleId, param.getRoleId())
                    .eq(SysRoleDataScopeGroup::getPermissionId, param.getPermissionId())
                    .in(SysRoleDataScopeGroup::getGroupId, removeGroupIds)
            ).submit();
        }


        // 计算要新增绑定的分组
        List<String> addGroupIds = Colls.diff(param.getGroupIds(), latestGroupIds);
        if (Colls.isNotEmpty(addGroupIds)) {
            List<SysRoleDataScopeGroup> list = addGroupIds.stream().map(groupId -> {
                SysRoleDataScopeGroup roleDataScopeGroup = new SysRoleDataScopeGroup();
                roleDataScopeGroup.setRoleId(param.getRoleId());
                roleDataScopeGroup.setPermissionId(param.getPermissionId());
                roleDataScopeGroup.setGroupId(groupId);
                return roleDataScopeGroup;
            }).toList();
            insert(SysRoleDataScopeGroup.class).into().values(list).submit();
        }

    }
}
