package xyz.ytora.core.sys.config.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sqlux.backup.BackupOnDelete;
import xyz.ytora.core.sys.config.model.SysConfigMapper;
import xyz.ytora.core.sys.config.model.data.SysConfigData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 系统配置
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@BackupOnDelete
@Table(value = "sys_config", idType = IdType.SNOWFLAKE, comment = "系统配置")
public class SysConfig extends BaseEntity<SysConfig> {

    /**
     * 配置名称
     */
    @Column(comment = "配置名称")
    private String name;


    /**
     * 键
     */
    @Column(comment = "键")
    private String key;


    /**
     * 值
     */
    @Column(comment = "值")
    private String value;


    /**
     * 配置类型
     */
    @Column(comment = "配置类型")
    private String type;


    /**
     * 是否启用
     */
    @Column(comment = "是否启用")
    private Boolean status;


    @Override
    public SysConfigData toData() {
        SysConfigMapper mapper = SysConfigMapper.mapper;
        return mapper.toData(this);
    }
}
