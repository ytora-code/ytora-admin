package xyz.ytora.core.rbac.depart.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseExcel;
import xyz.ytora.core.rbac.depart.model.SysDepartMapper;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.permission.model.resp.SysComponentsResp;
import xyz.ytora.ytool.document.excel.Excel;

/**
 * EXCEL请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel(fileName = "用户数据")
public class SysDepartExcel extends BaseExcel<SysDepart> {
    /**
     * 资源名称
     */
    @Excel("资源名称")
    private String permissionName;

    /**
     * 资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识
     */
    @Excel("资源编码")
    private String permissionCode;

    /**
     * 资源类型，1-接口、2-页面、3-页面元素
     */
    @Excel("资源类型")
    private Integer permissionType;

    /**
     * 图标
     */
    @Excel("图标")
    private String icon;

    /**
     * 图标
     */
    @Excel("资源的元数据")
    private SysComponentsResp meta;

    /**
     * 是否可见
     */
    @Excel("是否可见")
    private Boolean visible;

    /**
     * 排序
     */
    @Excel("排序")
    private Integer index;

    @Override
    public SysDepart toEntity() {
        return SysDepartMapper.mapper.toEntity(this);
    }
}
