package xyz.ytora.core.sys.icon.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.icon.model.SysIconMapper;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("系统图标库列表")
public class SysIconExcel extends BaseExcel<SysIcon> {

    /**
     * 图标code
     */
    @Excel("图标code")
    private String code;

    /**
     * 图标名称
     */
    @Excel("图标名称")
    private String name;

    /**
     * 图标库类型
     */
    @Excel("图标库类型")
    private String type;

    @Override
    public SysIcon toEntity() {
        SysIconMapper mapper = SysIconMapper.mapper;
        return mapper.toEntity(this);
    }
}
