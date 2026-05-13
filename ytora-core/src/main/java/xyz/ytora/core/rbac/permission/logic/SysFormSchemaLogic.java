package xyz.ytora.core.rbac.permission.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.entity.*;
import xyz.ytora.core.rbac.permission.model.param.SysRoleFormSchemaParam;
import xyz.ytora.core.rbac.permission.repo.SysFormSchemaRepo;
import xyz.ytora.sqlux.orm.AbsEntity;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.collection.Colls;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;
import static xyz.ytora.sqlux.core.SQL.delete;
import static xyz.ytora.sqlux.core.SQL.insert;

/**
 * 表单列结构模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysFormSchemaLogic extends BaseLogic<SysFormSchema, SysFormSchemaRepo> {

    /**
     * 列表查询指定资源下的表单
     */
    public Page<SysPermissionData> pageForms(String permissionId, Integer pageNo, Integer pageSize) {
        Page<SysPermission> page = select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionType, 4)
                        .eq(SysPermission::getPid, permissionId)
                ).submit(Pages.getPage(SysPermission.class));
        return page.trans(SysPermission::toData);
    }

    /**
     * 查询指定角色在指定资源下拥有的表单
     * @param roleId 角色ID
     * @param permissionId 资源ID
     * @return 表单ID数组
     */
    public List<String> listFormsForRole(String roleId, String permissionId) {
        List<SysPermission> list = select(SysPermission::getId)
                .from(SysRolePermission.class)
                .leftJoin(SysPermission.class, on -> on.eq(SysRolePermission::getPermissionId, SysPermission::getId))
                .where(w -> w
                        .eq(SysRolePermission::getRoleId, roleId)
                        .eq(SysPermission::getPid, permissionId)
                        .eq(SysPermission::getPermissionType, 4)
                ).submit(SysPermission.class);
        return list.stream().map(AbsEntity::getId).toList();
    }

    /**
     * 更新指定角色在指定资源下的分组
     * @param param 参数
     */
    public void refreshFormsForRole(SysRoleFormSchemaParam param) {
        // 查询数据库目前的表单拥有情况
        List<String> latestFormIds = listFormsForRole(param.getRoleId(), param.getPermissionId());

        // 计算要移除绑定的表单
        List<String> removeFormIds = Colls.diff(latestFormIds, param.getIds());
        if (Colls.isNotEmpty(removeFormIds)) {
            delete().from(SysRolePermission.class).where(w -> w
                    .eq(SysRolePermission::getRoleId, param.getRoleId())
                    .in(SysRolePermission::getPermissionId, removeFormIds)
            ).submit();
        }

        // 计算要新增绑定的表单
        List<String> addFormIds = Colls.diff(param.getIds(), latestFormIds);
        if (Colls.isNotEmpty(addFormIds)) {
            List<SysRolePermission> list = addFormIds.stream().map(id -> {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setRoleId(param.getRoleId());
                rolePermission.setPermissionId(id);
                return rolePermission;
            }).toList();
            insert(SysRolePermission.class).into().values(list).submit();
        }
    }

    /**
     * 查询指定角色在指定表单下拥有的表单列字段
     * @param roleId 角色ID
     * @param tableId 表单资源ID
     * @return 表单列ID数组
     */
    public List<String> listSchemasForRole(String roleId, String formId) {
        List<SysFormSchema> list = select(SysFormSchema::getId)
                .from(SysRoleFormSchema.class)
                .leftJoin(SysFormSchema.class, on -> on.eq(SysRoleFormSchema::getSchemaId, SysFormSchema::getId))
                .where(w -> w
                        .eq(SysRoleFormSchema::getRoleId, roleId)
                        .eq(SysRoleFormSchema::getFormId, formId)
                ).submit(SysFormSchema.class);
        return list.stream().map(AbsEntity::getId).toList();
    }

    /**
     * 更新指定角色在指定表单下拥有的表单列字段
     * @param param 参数
     */
    public void refreshSchemasForRole(SysRoleFormSchemaParam param) {
        // 查询数据库目前的表单列拥有情况
        List<String> latestFormSchemaIds = listSchemasForRole(param.getRoleId(), param.getFormId());

        // 计算要移除绑定的表单列
        List<String> removeFormSchemaIds = Colls.diff(latestFormSchemaIds, param.getIds());
        if (Colls.isNotEmpty(removeFormSchemaIds)) {
            delete().from(SysRoleFormSchema.class).where(w -> w
                    .eq(SysRoleFormSchema::getRoleId, param.getRoleId())
                    .eq(SysRoleFormSchema::getFormId, param.getFormId())
                    .in(SysRoleFormSchema::getSchemaId, removeFormSchemaIds)
            ).submit();
        }

        // 计算要新增绑定的表单列
        List<String> addFormSchemaIds = Colls.diff(param.getIds(), latestFormSchemaIds);
        if (Colls.isNotEmpty(addFormSchemaIds)) {
            List<SysRoleFormSchema> list = addFormSchemaIds.stream().map(id -> {
                SysRoleFormSchema roleFormSchema = new SysRoleFormSchema();
                roleFormSchema.setRoleId(param.getRoleId());
                roleFormSchema.setFormId(param.getFormId());
                roleFormSchema.setSchemaId(id);
                return roleFormSchema;
            }).toList();
            insert(SysRoleFormSchema.class).into().values(list).submit();
        }
    }
}
