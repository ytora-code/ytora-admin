package xyz.ytora.core.rbac.role.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.core.rbac.role.model.excel.SysRoleExcel;
import xyz.ytora.sqlux.core.anno.Column;

/**
 * 角色响应数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色响应数据")
public class SysRoleData extends BaseData<SysRole> {
    /**
     * 角色名称
     */
    @Column(comment = "角色名称")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(comment = "角色编码", notNull = true)
    private String roleCode;

    @Override
    public SysRoleExcel toExcel() {
        return SysRoleMapper.mapper.toExcel(this);
    }
}
