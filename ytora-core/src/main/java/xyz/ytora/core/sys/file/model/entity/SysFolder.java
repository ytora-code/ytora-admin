package xyz.ytora.core.sys.file.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.file.model.SysFolderMapper;
import xyz.ytora.core.sys.file.model.data.SysFolderData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 系统文件夹
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_folder", idType = IdType.SNOWFLAKE, comment = "系统文件夹")
public class SysFolder extends BaseEntity<SysFolder> {

    /**
     * 父文件夹ID
     */
    @Column(comment = "父文件夹ID", notNull = true)
    private String pid;

    /**
     * 文件夹路径
     */
    @Column(comment = "文件夹路径", notNull = true)
    private String path;

    /**
     * 文件夹深度
     */
    @Column(comment = "文件夹深度")
    private Integer depth;

    @Override
    public SysFolderData toData() {
        SysFolderMapper mapper = SysFolderMapper.mapper;
        return mapper.toData(this);
    }

}
