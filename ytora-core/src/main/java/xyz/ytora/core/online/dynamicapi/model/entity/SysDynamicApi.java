package xyz.ytora.core.online.dynamicapi.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiMapper;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.ColumnType;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 动态API接口表
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_dynamic_api", idType = IdType.SNOWFLAKE, comment = "动态API接口表")
public class SysDynamicApi extends BaseEntity<SysDynamicApi> {

    /**
     * 所属分组ID
     */
    @Column(comment = "所属分组ID", notNull = true)
    private String groupId;

    /**
     * 接口URI
     */
    @Column(comment = "接口URI", notNull = true)
    private String uri;

    /**
     * 请求方式,1-get/2-post/3-put/4-delete
     */
    @Column(comment = "请求方式,1-get/2-post/3-put/4-delete")
    private Integer method;

    /**
     * 接口名称
     */
    @Column(comment = "接口名称")
    private String name;

    /**
     * 接口类型，1:dsl-sql/2-sql/3-JavaScript/4-python/5-java
     */
    @Column(comment = "接口类型，1:dsl-sql/2-sql/3-JavaScript/4-python/5-java")
    private Integer type;

    /**
     * 接口内容
     */
    @Column(comment = "接口内容", type = ColumnType.TEXT)
    private String content;

    /**
     * 测试参数
     */
    @Column(comment = "测试参数", type = ColumnType.TEXT)
    private String testParam;

    /**
     * 是否开启事务
     */
    @Column(comment = "是否开启事务")
    private Boolean transactional;

    /**
     * 接口状态，1-未发布/2-已发布
     */
    @Column(comment = "接口状态，1-未发布/2-已发布")
    private Integer status;

    @Override
    public SysDynamicApiData toData() {
        SysDynamicApiMapper mapper = SysDynamicApiMapper.mapper;
        return mapper.toData(this);
    }
}
