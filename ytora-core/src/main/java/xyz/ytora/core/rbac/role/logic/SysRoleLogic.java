package xyz.ytora.core.rbac.role.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.rbac.role.model.data.SysRoleData;
import xyz.ytora.core.rbac.role.model.data.SysUserRoleData;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.entity.SysUserRole;
import xyz.ytora.core.rbac.role.model.param.SysUserRoleMapperParam;
import xyz.ytora.core.rbac.role.model.param.SysUserRoleRefreshParam;
import xyz.ytora.core.rbac.role.repo.SysRoleRepo;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.sqlux.sql.stage.select.AbsSelect;
import xyz.ytora.toolkit.text.Strs;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;
import static xyz.ytora.sqlux.sql.func.SqlFuncAggregation.alias;

/**
 * 角色模块的业务逻辑层
 *
 * @author ytora
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysRoleLogic extends BaseLogic<SysRole, SysRoleRepo> {

    /**
     * 获取用户-角色关系
     */
    public Page<SysUserRoleData> listUserRoleMapper(SysUserRoleMapperParam param, Integer pageNo, Integer pageSize) {
        String userId = param.getUserId();
        String roleName = param.getRoleName();
        String roleCode = param.getRoleCode();

//        Page<SysUserRoleData> page = distinct().select(alias(SysRole::getId).as("role_id"))
//                .select(SysRole::getRoleName, SysRole::getRoleCode, SysRole::getRemark, SysUserRole::getUserId)
//                .from(SysUser.class)
//                .leftJoin(SysUserRole.class, on -> on.eq(SysUser::getId, SysUserRole::getUserId))
//                .leftJoin(SysRole.class, on -> on.eq(SysRole::getId, SysUserRole::getRoleId))
//                .where(w -> w.eq(SysUserRole::getUserId, userId)
//                        .like(Strs.isNotEmpty(roleName), SysRole::getRoleName, roleName)
//                        .like(Strs.isNotEmpty(roleCode), SysRole::getRoleCode, roleCode)
//                ).submit(Pages.getPage(SysUserRoleData.class));

        // 当前用户的角色
        List<SysRoleData> currentUserRoles = listUserRole(userId);
        List<String> roleIds = currentUserRoles.stream().map(BaseData::getId).toList();
        Page<SysRole> page = repository.page(w -> w
                        .like(Strs.isNotEmpty(roleName), SysRole::getRoleName, roleName)
                        .like(Strs.isNotEmpty(roleCode), SysRole::getRoleCode, roleCode),
                pageNo, pageSize);


        return page.trans(item -> {
            SysUserRoleData data = new SysUserRoleData();
            data.setUserId(userId);
            data.setRoleId(item.getId());
            data.setRoleName(item.getRoleName());
            data.setRoleCode(item.getRoleCode());
            data.setRoleRemark(item.getRemark());
            data.setOwner(roleIds.contains(item.getId()));
            return data;
        });
    }

    /**
     * 更新用户-角色关系
     */
    public void refreshUserRoleMapper(SysUserRoleRefreshParam userRoleReq) {
        if (userRoleReq.getAdd()) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userRoleReq.getUserId());
            userRole.setRoleId(userRoleReq.getRoleId());
            insert(SysUserRole.class)
                    .into()
                    .values(userRole)
                    .submit();
        } else {
            delete().from(SysUserRole.class)
                    .where(w -> w.eq(SysUserRole::getUserId, userRoleReq.getUserId()).eq(SysUserRole::getRoleId, userRoleReq.getRoleId()))
                    .submit();
        }
    }

    /**
     * 查询当前人员的角色信息
     * @return 角色数组
     */
    public List<SysRoleData> listCurrentRole() {
        if (!ScopedValueContext.LOGIN_USER.isBound()) {
            return Collections.emptyList();
        }
        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();

        return listUserRole(loginUser.getId());
    }

    /**
     * 查询指定人员的角色信息
     * @return 角色数组
     */
    public List<SysRoleData> listUserRole(String userId) {
        if (Strs.isEmpty(userId)) {
            return Collections.emptyList();
        }
        AbsSelect subSelect = select(SysUserRole::getRoleId).from(SysUserRole.class)
                .where(w -> w.eq(SysUserRole::getUserId, userId));

        List<SysRole> list = select().from(SysRole.class)
                .where(w -> w.in(SysRole::getId, subSelect))
                .submit(SysRole.class);

        return list.stream().map(SysRole::toData).toList();
    }

}
