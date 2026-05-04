package xyz.ytora.core.rbac.datascope.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.rbac.datascope.model.SysDataScopeMapper;
import xyz.ytora.core.rbac.datascope.model.entity.SysDataScope;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("数据范围列表")
public class SysDataScopeExcel extends BaseExcel<SysDataScope> {

    /**
     * 所属分组ID
     */
    @Excel("所属分组ID")
    private String groupId;

    /**
     * 数据范围名称
     */
    @Excel("数据范围名称")
    private String name;

    /**
     * 数据范围匹配的列
     */
    @Excel("数据范围匹配的列")
    private String column;

    /**
     * 数据范围类型
     */
    @Excel("数据范围类型")
    private String type;

    /**
     * 数据范围的规则
     */
    @Excel("数据范围的规则")
    private String value;

    @Override
    public SysDataScope toEntity() {
        SysDataScopeMapper mapper = SysDataScopeMapper.mapper;
        return mapper.toEntity(this);
    }
}
