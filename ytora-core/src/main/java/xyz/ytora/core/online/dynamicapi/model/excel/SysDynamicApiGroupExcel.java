package xyz.ytora.core.online.dynamicapi.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiGroupMapper;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("动态API接口分组列表")
public class SysDynamicApiGroupExcel extends BaseExcel<SysDynamicApiGroup> {

    /**
     * 上级分组ID
     */
    @Excel("上级分组ID")
    private String pid;

    /**
     * 分组名称
     */
    @Excel("分组名称")
    private String name;

    @Override
    public SysDynamicApiGroup toEntity() {
        SysDynamicApiGroupMapper mapper = SysDynamicApiGroupMapper.mapper;
        return mapper.toEntity(this);
    }
}
