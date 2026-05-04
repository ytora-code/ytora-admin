package xyz.ytora.core.rbac.role.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.role.model.SysRoleMapper;
import xyz.ytora.core.rbac.role.model.entity.SysRole;
import xyz.ytora.sqlux.core.anno.Column;

/**
 * 角色表请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色表请求数据")
public class SysRoleParam extends BaseParam<SysRole> {
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
    public SysRole toEntity() {
        SysRoleMapper mapper = SysRoleMapper.mapper;
        return mapper.toEntity(this);
    }
}
