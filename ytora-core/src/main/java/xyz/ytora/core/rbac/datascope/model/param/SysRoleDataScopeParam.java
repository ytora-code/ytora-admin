package xyz.ytora.core.rbac.datascope.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.datascope.model.SysRoleDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysRoleDataScope;

import java.util.List;

/**
 * 角色-数据范围关系请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@Schema(description = "角色-数据范围关系表请求数据")
public class SysRoleDataScopeParam {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 数据分组ID
     */
    @Schema(description = "数据分组ID")
    private String groupId;

    /**
     * 数据范围ID数组
     */
    @Schema(description = "数据范围ID数组")
    private List<String> scopeIds;

}
