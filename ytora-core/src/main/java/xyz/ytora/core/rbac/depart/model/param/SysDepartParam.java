package xyz.ytora.core.rbac.depart.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.rbac.depart.model.SysDepartMapper;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;

/**
 * 部门模块请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "部门模块请求数据")
public class SysDepartParam extends BaseParam<SysDepart> {

    /**
     * 上级部门id
     */
    @Schema(description = "上级部门id")
    private String pid;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String departName;

    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    private String departCode;

    /**
     * 部门类型
     */
    @Schema(description = "部门类型")
    private String type;

    /**
     * 部门联系人ID
     */
    @Schema(description = "部门联系人ID")
    private String contactId;

    @Override
    public SysDepart toEntity() {
        SysDepartMapper mapper = SysDepartMapper.mapper;
        return mapper.toEntity(this);
    }

}
