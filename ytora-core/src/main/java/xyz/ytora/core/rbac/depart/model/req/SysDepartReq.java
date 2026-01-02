package xyz.ytora.core.rbac.depart.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.rbac.depart.model.SysDepartMapper;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.ytool.anno.Index;

/**
 * 部门表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "部门表")
public class SysDepartReq extends BaseReq<SysDepart> {
    /**
     * 上级部门id
     */
    @Index(1)
    @Schema(description = "上级部门id")
    private String pid;

    /**
     * 部门名称
     */
    @Index(2)
    @Schema(description = "部门名称")
    private String departName;

    /**
     * 部门编码
     */
    @Index(3)
    @Schema(description = "部门编码")
    private String departCode;

    /**
     * 部门类型
     */
    @Index(3)
    @Schema(description = "部门类型")
    private String type;

    /**
     * 部门联系人username
     */
    @Index(4)
    @Schema(description = "部门联系人username")
    private String contactUserName;

    @Override
    public SysDepart toEntity() {
        SysDepartMapper mapper = SysDepartMapper.mapper;
        return mapper.reqToEntity(this);
    }
}
