package xyz.ytora.core.online.dynamicapi.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.online.dynamicapi.model.SysDynamicApiGroupMapper;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiGroupData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 动态API接口分组表
 *
 * @author 杨桐
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_dynamic_api_group", idType = IdType.SNOWFLAKE, comment = "动态API接口分组表")
public class SysDynamicApiGroup extends BaseEntity<SysDynamicApiGroup> {

    /**
     * 上级分组ID
     */
    @Column(comment = "上级分组ID")
    private String pid;

    /**
     * 分组名称
     */
    @Column(comment = "分组名称", notNull = true)
    private String name;

    @Override
    public SysDynamicApiGroupData toData() {
        SysDynamicApiGroupMapper mapper = SysDynamicApiGroupMapper.mapper;
        return mapper.toData(this);
    }
}
