package xyz.ytora.core.online.dynamicapi.model.excel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiMapper;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("动态API接口列表")
public class SysDynamicApiExcel extends BaseExcel<SysDynamicApi> {

    /**
     * 所属分组ID
     */
    @Excel("所属分组ID")
    private String groupId;

    /**
     * 接口URI
     */
    @Excel("接口URI")
    private String uri;

    /**
     * 请求方式,1-get/2-post/3-put/delete
     */
    @Excel("请求方式,1-get/2-post/3-put/delete")
    private Integer method;

    /**
     * 接口名称
     */
    @Excel("接口名称")
    private String name;

    /**
     * 接口类型，1:dsl-sql/2-sql/3-JavaScript/4-python/5-java
     */
    @Excel("接口类型，1:dsl-sql/2-sql/3-JavaScript/4-python/5-java")
    private String type;

    /**
     * 接口内容
     */
    @Excel("接口内容")
    private String content;

    /**
     * 是否开启事务
     */
    @Excel("是否开启事务")
    private Boolean transactional;

    /**
     * 接口状态，1-未发布/2-已发布
     */
    @Excel("接口状态，1-未发布/2-已发布")
    private Integer status;

    @Override
    public SysDynamicApi toEntity() {
        SysDynamicApiMapper mapper = SysDynamicApiMapper.mapper;
        return mapper.toEntity(this);
    }
}
