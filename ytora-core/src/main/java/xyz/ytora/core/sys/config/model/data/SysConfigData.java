package xyz.ytora.core.sys.config.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.config.model.SysConfigMapper;
import xyz.ytora.core.sys.config.model.entity.SysConfig;
import xyz.ytora.core.sys.config.model.excel.SysConfigExcel;

/**
 * 系统配置响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统配置响应数据")
public class SysConfigData extends BaseData<SysConfig> {

    /**
     * 配置名称
     */
    @Schema(description = "配置名称")
    private String name;


    /**
     * 键
     */
    @Schema(description = "键")
    private String key;


    /**
     * 值
     */
    @Schema(description = "值")
    private String value;


    /**
     * 配置类型
     */
    @Schema(description = "配置类型")
    private String type;


    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean status;


    @Override
    public SysConfigExcel toExcel() {
        SysConfigMapper mapper = SysConfigMapper.mapper;
        return mapper.toExcel(this);
    }
}
