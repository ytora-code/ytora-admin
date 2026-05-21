package xyz.ytora.core.rbac.permission.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.auth.LoginUser;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.scope.ScopedValueContext;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScopeGroup;
import xyz.ytora.core.rbac.permission.PermissionType;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.data.SysTableSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRolePermission;
import xyz.ytora.core.rbac.permission.model.entity.SysRoleTableSchema;
import xyz.ytora.core.rbac.permission.model.entity.SysTableSchema;
import xyz.ytora.core.rbac.permission.model.param.SysRoleTableSchemaParam;
import xyz.ytora.core.rbac.permission.repo.SysTableSchemaRepo;
import xyz.ytora.core.rbac.role.model.entity.SysUserRole;
import xyz.ytora.sqlux.orm.AbsEntity;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.sqlux.sql.stage.select.SelectWhereStage;
import xyz.ytora.toolkit.collection.Colls;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 表格列结构模块的业务逻辑层
 *
 * @author 杨桐
 * @since 1.0
 */
@Service
@AllArgsConstructor
public class SysTableSchemaLogic extends BaseLogic<SysTableSchema, SysTableSchemaRepo> {

    /**
     * 分页查询指定资源下的表格
     */
    public Page<SysPermissionData> pageTables(String permissionId, Integer pageNo, Integer pageSize) {
        Page<SysPermission> page = select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionType, PermissionType.TABLE.code())
                        .eq(SysPermission::getPid, permissionId)
                )
                .orderByAsc(SysPermission::getIndex)
                .submit(Pages.getPage(SysPermission.class));
        return page.trans(SysPermission::toData);
    }

    /**
     * 根据表格code查询当前角色拥有的表格列Schema
     */
    public List<SysTableSchemaData> listSchemasByTableCode(String tableCode) {
        if (!ScopedValueContext.LOGIN_USER.isBound()) {
            return Collections.emptyList();
        }
        LoginUser loginUser = ScopedValueContext.LOGIN_USER.get();

        // 子查询：查询用户的角色信息
        SelectWhereStage subQuery = select(SysUserRole::getRoleId)
                .from(SysUserRole.class)
                .where(w -> w.eq(SysUserRole::getUserId, loginUser.getId()));

        List<SysTableSchema> list = select(SysTableSchema.class)
                .from(SysRoleTableSchema.class)
                .leftJoin(SysTableSchema.class, on -> on.eq(SysRoleTableSchema::getSchemaId, SysTableSchema::getId))
                .leftJoin(SysPermission.class, on -> on.eq(SysPermission::getId, SysTableSchema::getPermissionId))
                .where(w -> w
                        .in(SysRoleTableSchema::getRoleId, subQuery)
                        .eq(SysPermission::getPermissionCode, tableCode)
                        .eq(SysPermission::getPermissionType, PermissionType.TABLE.code())
                ).orderByAsc(SysTableSchema::getIndex)
                .submit(SysTableSchema.class);

        return list.stream().map(SysTableSchema::toData).toList();
    }

    /**
     * 查询指定角色在指定资源下拥有的表格
     * @param roleId 角色ID
     * @param permissionId 资源ID
     * @return 表格ID数组
     */
    public List<String> listTablesForRole(String roleId, String permissionId) {
        List<SysPermission> list = select(SysPermission::getId)
                .from(SysRolePermission.class)
                .leftJoin(SysPermission.class, on -> on.eq(SysRolePermission::getPermissionId, SysPermission::getId))
                .where(w -> w
                        .eq(SysRolePermission::getRoleId, roleId)
                        .eq(SysPermission::getPid, permissionId)
                        .eq(SysPermission::getPermissionType, PermissionType.TABLE.code())
                ).submit(SysPermission.class);
        return list.stream().map(AbsEntity::getId).toList();
    }

    /**
     * 更新指定角色在指定资源下的分组
     * @param param 参数
     */
    public void refreshTablesForRole(SysRoleTableSchemaParam param) {
        // 查询数据库目前的表格拥有情况
        List<String> latestTableIds = listTablesForRole(param.getRoleId(), param.getPermissionId());

        // 计算要移除绑定的表格
        List<String> removeTableIds = Colls.diff(latestTableIds, param.getIds());
        if (Colls.isNotEmpty(removeTableIds)) {
            delete().from(SysRolePermission.class).where(w -> w
                    .eq(SysRolePermission::getRoleId, param.getRoleId())
                    .in(SysRolePermission::getPermissionId, removeTableIds)
            ).submit();
        }

        // 计算要新增绑定的表格
        List<String> addTableIds = Colls.diff(param.getIds(), latestTableIds);
        if (Colls.isNotEmpty(addTableIds)) {
            List<SysRolePermission> list = addTableIds.stream().map(id -> {
                SysRolePermission rolePermission = new SysRolePermission();
                rolePermission.setRoleId(param.getRoleId());
                rolePermission.setPermissionId(id);
                return rolePermission;
            }).toList();
            insert(SysRolePermission.class).into().values(list).submit();
        }
    }

    /**
     * 查询指定角色在指定表格下拥有的表格列字段
     * @param roleId 角色ID
     * @param tableId 表格资源ID
     * @return 表格列ID数组
     */
    public List<String> listSchemasForRole(String roleId, String tableId) {
        List<SysTableSchema> list = select(SysTableSchema::getId)
                .from(SysRoleTableSchema.class)
                .leftJoin(SysTableSchema.class, on -> on.eq(SysRoleTableSchema::getSchemaId, SysTableSchema::getId))
                .where(w -> w
                        .eq(SysRoleTableSchema::getRoleId, roleId)
                        .eq(SysRoleTableSchema::getTableId, tableId)
                ).submit(SysTableSchema.class);
        return list.stream().map(AbsEntity::getId).toList();
    }

    /**
     * 更新指定角色在指定表格下拥有的表格列字段
     * @param param 参数
     */
    public void refreshSchemasForRole(SysRoleTableSchemaParam param) {
        // 查询数据库目前的表格列拥有情况
        List<String> latestTableSchemaIds = listSchemasForRole(param.getRoleId(), param.getTableId());

        // 计算要移除绑定的表格列
        List<String> removeTableSchemaIds = Colls.diff(latestTableSchemaIds, param.getIds());
        if (Colls.isNotEmpty(removeTableSchemaIds)) {
            delete().from(SysRoleTableSchema.class).where(w -> w
                    .eq(SysRoleTableSchema::getRoleId, param.getRoleId())
                    .eq(SysRoleTableSchema::getTableId, param.getTableId())
                    .in(SysRoleTableSchema::getSchemaId, removeTableSchemaIds)
            ).submit();
        }

        // 计算要新增绑定的表格列
        List<String> addTableSchemaIds = Colls.diff(param.getIds(), latestTableSchemaIds);
        if (Colls.isNotEmpty(addTableSchemaIds)) {
            List<SysRoleTableSchema> list = addTableSchemaIds.stream().map(id -> {
                SysRoleTableSchema roleTableSchema = new SysRoleTableSchema();
                roleTableSchema.setRoleId(param.getRoleId());
                roleTableSchema.setTableId(param.getTableId());
                roleTableSchema.setSchemaId(id);
                return roleTableSchema;
            }).toList();
            insert(SysRoleTableSchema.class).into().values(list).submit();
        }
    }
}
