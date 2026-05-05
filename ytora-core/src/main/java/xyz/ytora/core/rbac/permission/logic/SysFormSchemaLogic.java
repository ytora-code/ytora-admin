package xyz.ytora.core.rbac.permission.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.permission.model.entity.SysFormSchema;
import xyz.ytora.core.rbac.permission.model.entity.SysPermission;
import xyz.ytora.core.rbac.permission.repo.SysFormSchemaRepo;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;

/**
 * 表格列结构模块的业务逻辑层
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
    public List<SysPermissionData> listForms(String permissionId) {
        List<SysPermission> list = select().from(SysPermission.class)
                .where(w -> w
                        .eq(SysPermission::getPermissionType, 4)
                        .eq(SysPermission::getPid, permissionId)
                ).submit(SysPermission.class);
        return list.stream().map(SysPermission::toData).toList();
    }

}
