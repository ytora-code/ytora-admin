package xyz.ytora.core.rbac.role.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.entity.SysUserRole;
import xyz.ytora.core.rbac.role.model.req.SysUserRoleReq;
import xyz.ytora.core.rbac.role.model.resp.SysUserRoleResp;
import xyz.ytora.core.rbac.role.repo.SysRoleRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.func.Alias;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.AbsSelect;
import xyz.ytora.sql4j.sql.select.OrderByStage;
import xyz.ytora.sql4j.sql.select.SelectWhereStage;
import xyz.ytora.sql4j.util.OrmUtil;
import xyz.ytora.ytool.str.Strs;

import java.util.List;

/**
 * created by YT on 2025/12/21 16:25:59
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class SysRoleLogic extends BaseLogic<SysRole, SysRoleRepo> {

    private final SQLHelper sqlHelper;

    /**
     * 获取用户-角色关系
     */
    public Page<SysUserRoleResp> listUserRoleMapper(String userId, String roleName, String roleCode, Integer pageNo, Integer pageSize) {
        AbsSelect select = sqlHelper
                .select(Alias.of(SysRole::getId, "role_id"), Alias.of(SysRole::getRoleName, "role_name"), Alias.of(SysRole::getRoleCode, "role_code"), Alias.of(SysRole::getRemark, "role_remark"))
                .select(SysUserRole::getUserId)
                .from(SysRole.class)
                .leftJoin(SysUserRole.class, on -> on.eq(SysRole::getId, SysUserRole::getRoleId))
                .where(w -> w.like(Strs.isNotEmpty(roleName), SysRole::getRoleName, roleName)
                        .like(Strs.isNotEmpty(roleCode), SysRole::getRoleCode, roleCode)
                )
                .orderBy(SysRole::getId, OrderType.ASC);

        Page<SysUserRoleResp> page = OrmUtil.page(SysUserRoleResp.class, pageNo, pageSize, select.getSelectBuilder());

        return Pages.transPage(page, item -> {
            item.setOwner(userId.equals(item.getUserId()));
            return item;
        });
    }

    /**
     * 更新用户-角色关系
     */
    public void refreshUserRoleMapper(SysUserRoleReq userRoleReq) {
        if (userRoleReq.getAdd()) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userRoleReq.getUserId());
            userRole.setRoleId(userRoleReq.getRoleId());
            sqlHelper.insert(SysUserRole.class)
                    .into(SysUserRole::getUserId, SysUserRole::getRoleId)
                    .value(userRoleReq.getUserId(), userRoleReq.getRoleId())
                    .submit();
        } else {
            sqlHelper.delete().from(SysUserRole.class)
                    .where(w -> w.eq(SysUserRole::getUserId, userRoleReq.getUserId()).eq(SysUserRole::getRoleId, userRoleReq.getRoleId()))
                    .submit();
        }
    }

}
