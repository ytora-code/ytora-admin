package xyz.ytora.core.rbac.datascope.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeGroupMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScopeGroup;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("数据范围组列表")
public class SysDataScopeGroupExcel extends BaseExcel<SysDataScopeGroup> {

    /**
     * 所属的资源ID
     */
    @Excel("所属的资源ID")
    private String permissionId;

    /**
     * 分组名称
     */
    @Excel("分组名称")
    private String name;

    /**
     * 分组编码
     */
    @Excel("分组编码")
    private String code;

    @Override
    public SysDataScopeGroup toEntity() {
        SysDataScopeGroupMapper mapper = SysDataScopeGroupMapper.mapper;
        return mapper.toEntity(this);
    }
}
