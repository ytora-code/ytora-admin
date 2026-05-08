package xyz.ytora.core.online.dynamicapi.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseParam;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiMapper;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi;

/**
 * 动态API接口请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "动态API接口表请求数据")
public class SysDynamicApiParam extends BaseParam<SysDynamicApi> {

    /**
     * 所属分组ID
     */
    @Schema(description = "所属分组ID")
    private String groupId;

    /**
     * 接口URI
     */
    @Schema(description = "接口URI")
    private String uri;

    /**
     * 请求方式,1-get/2-post/3-put/delete
     */
    @Schema(description = "请求方式,1-get/2-post/3-put/delete")
    private Integer method;

    /**
     * 接口名称
     */
    @Schema(description = "接口名称")
    private String name;

    /**
     * 接口类型，1:dsl-sql/2-sql/3-JavaScript/4-python/5-java
     */
    @Schema(description = "接口类型，1:dsl-sql/2-sql/3-JavaScript/4-python/5-java")
    private String type;

    /**
     * 接口内容
     */
    @Schema(description = "接口内容")
    private String content;

    /**
     * 测试参数
     */
    @Schema(description = "测试参数")
    private String testParam;

    /**
     * 返回结果字段说明
     */
    @Schema(description = "返回结果字段说明")
    private String resultDesc;

    /**
     * 是否开启事务
     */
    @Schema(description = "是否开启事务")
    private Boolean transactional;

    /**
     * 接口状态，1-未发布/2-已发布
     */
    @Schema(description = "接口状态，1-未发布/2-已发布")
    private Integer status;

    @Override
    public SysDynamicApi toEntity() {
        SysDynamicApiMapper mapper = SysDynamicApiMapper.mapper;
        return mapper.toEntity(this);
    }
}
