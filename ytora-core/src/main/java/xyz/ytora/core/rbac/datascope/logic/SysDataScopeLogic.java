package xyz.ytora.core.rbac.datascope.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.datascope.model.data.SysDataScopeData;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.core.rbac.datascope.model.param.SysRoleDataScopeParam;
import xyz.ytora.core.rbac.datascope.repo.SysDataScopeRepo;
import xyz.ytora.toolkit.collection.Colls;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 数据范围模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Service
@AllArgsConstructor
public class SysDataScopeLogic extends BaseLogic<SysDataScope, SysDataScopeRepo> {

    /**
     * 查询指定分组下的数据范围
     * @param groupId 分组ID
     * @return 数据范围列表
     */
    public List<SysDataScopeData> listByGroupId(String groupId) {
        List<SysDataScope> list = repository.list(w -> w.eq(SysDataScope::getGroupId, groupId));
        return list.stream().map(SysDataScope::toData).toList();
    }

    /**
     * 查询指定角色在指定分组下面的数据范围
     * @param roleIds 角色ID数组
     * @param groupId 分组ID
     * @return 数据范围ID
     */
    public List<String> listDataScopeByGroupId(List<String> roleIds, String groupId) {
        if (Colls.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<SysRoleDataScope> groupList = select().from(SysRoleDataScope.class)
                .where(w -> w.in(SysRoleDataScope::getRoleId, roleIds)
                        .eq(SysRoleDataScope::getGroupId, groupId)
                ).submit(SysRoleDataScope.class);
        return groupList.stream().map(SysRoleDataScope::getScopeId).toList();
    }

    /**
     * 更新指定角色在指定分组下的数据范围
     * @param param 参数
     */
    public void refreshRoleGroupDataScope(SysRoleDataScopeParam param) {

        // 目前的分组ID
        List<String> latestDataScopeIds = listDataScopeByGroupId(Collections.singletonList(param.getRoleId()), param.getGroupId());

        // 计算要移除绑定的数据
        List<String> removeIds = Colls.diff(latestDataScopeIds, param.getScopeIds());
        if (Colls.isNotEmpty(removeIds)) {
            delete().from(SysRoleDataScope.class).where(w -> w
                    .eq(SysRoleDataScope::getRoleId, param.getRoleId())
                    .eq(SysRoleDataScope::getGroupId, param.getGroupId())
                    .in(SysRoleDataScope::getScopeId, removeIds)
            ).submit();
        }


        // 计算要新增绑定的分组
        List<String> addIds = Colls.diff(param.getScopeIds(), latestDataScopeIds);
        if (Colls.isNotEmpty(addIds)) {
            List<SysRoleDataScope> list = addIds.stream().map(scopeId -> {
                SysRoleDataScope roleDataScope = new SysRoleDataScope();
                roleDataScope.setRoleId(param.getRoleId());
                roleDataScope.setGroupId(param.getGroupId());
                roleDataScope.setScopeId(scopeId);
                return roleDataScope;
            }).toList();
            insert(SysRoleDataScope.class).into().values(list).submit();
        }

    }
}
