package xyz.ytora.core.rbac.datarule.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.datarule.model.entity.SysRoleDataRule;

import java.util.List;

/**
 * 角色-数据规则请求关系表
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDataRuleReq extends BaseReq<SysRoleDataRule> {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 资源ID
     */
    @Schema(description = "资源ID")
    private String permissionId;

    /**
     * 原始规则ID数组
     */
    @Schema(description = "原始规则ID数组")
    private List<String> originDataRuleIds;

    /**
     * 最新的规则ID数组
     */
    @Schema(description = "最新的规则ID数组")
    private List<String> currentDataRuleIds;

    @Override
    public SysRoleDataRule toEntity() {
        SysRoleDataRule mapper = new SysRoleDataRule();
        mapper.setRoleId(roleId);
        return mapper;
    }
}
