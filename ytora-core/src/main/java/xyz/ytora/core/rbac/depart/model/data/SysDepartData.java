package xyz.ytora.core.rbac.depart.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.rbac.depart.model.SysDepartMapper;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.excel.SysDepartExcel;
import xyz.ytora.core.sys.dict.Dict;
import xyz.ytora.toolkit.tree.ITree;

import java.util.List;

/**
 * 用户响应数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户表响应数据")
public class SysDepartData extends BaseData<SysDepart> implements ITree<SysDepartData> {

    /**
     * 上级部门id
     */
    @Schema(description = "上级部门id")
    private String pid;

    /**
     * 父资源名称
     */
    @Schema(description = "父资源名称")
    private String pName;

    /**
     * 层级
     */
    @Schema(description = "层级")
    private Integer level;

    /**
     * 层级Key
     */
    @Schema(description = "层级Key")
    private String levelKey;

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
    @Dict(table = "sys_user", code = "id", text = "real_name")
    @Schema(description = "部门联系人ID")
    private String contactId;

    /**
     * 子元数集合
     */
    private List<SysDepartData> children;

    @Override
    public SysDepartExcel toExcel() {
        SysDepartMapper mapper = SysDepartMapper.mapper;
        return mapper.toExcel(this);
    }
}
