package xyz.ytora.core.online.dynamicapi.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiMapper;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiExcel;

import java.util.Map;

/**
 * 动态API接口测试的请求参数
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@Schema(description = "动态API接口测试的请求参数")
public class SysDynamicApiTestExecParam {

    /**
     * 测试使用的dsl内容
     */
    @Schema(description = "测试使用的dsl内容")
    private String content;

    /**
     * 测试使用的参数
     */
    @Schema(description = "测试使用的参数")
    private Map<String, Object> param;

    /**
     * 最多查询数据量
     */
    @Schema(description = "最多查询数据量")
    private Integer max;

}
