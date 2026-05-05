package xyz.ytora.core.rbac.permission.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.data.SysTableSchemaData;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.model.entity.SysTableSchema;
import xyz.ytora.core.rbac.permission.repo.SysTableSchemaRepo;

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
    public List<SysPermissionData> listTables(String permissionId) {
        List<SysPermission> list = select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionType, 3)
                        .eq(SysPermission::getPid, permissionId)
                ).submit(SysPermission.class);
        return list.stream().map(SysPermission::toData).toList();
    }

    /**
     * 根据表格code查询数据表格列Schema数据
     */
    public List<SysTableSchemaData> listSchemasByTableCode(String tableCode) {
        Optional<SysPermission> permissionOptional = select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionCode, tableCode)
                ).submit(SysPermission.class, 0);
        if (permissionOptional.isPresent()) {
            SysPermission permission = permissionOptional.get();
            List<SysTableSchema> list = repository.list(w -> w.eq(SysTableSchema::getPermissionId, permission.getId()));
            return list.stream().map(SysTableSchema::toData).toList();
        }

        return Collections.emptyList();
    }
}
