package xyz.ytora.core.sys.config.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.config.model.SysConfigMapper;
import xyz.ytora.core.sys.config.model.entity.SysConfig;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("系统配置列表")
public class SysConfigExcel extends BaseExcel<SysConfig> {

    /**
     * 配置名称
     */
    @Excel("配置名称")
    private String name;


    /**
     * 键
     */
    @Excel("键")
    private String key;


    /**
     * 值
     */
    @Excel("值")
    private String value;


    /**
     * 配置类型
     */
    @Excel("配置类型")
    private String type;


    /**
     * 是否启用
     */
    @Excel("是否启用")
    private Boolean status;


    @Override
    public SysConfig toEntity() {
        SysConfigMapper mapper = SysConfigMapper.mapper;
        return mapper.toEntity(this);
    }
}
