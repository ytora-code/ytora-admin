package xyz.ytora.core.sys.file.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;
import xyz.ytora.core.sys.file.model.resp.SysFolderResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 系统文件夹
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_folder", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "系统文件夹")
public class SysFolder extends BaseEntity<SysFolder> {
    /**
     * 文件id，全局唯一，根据该id可以找到一个唯一对应的文件
     */
    @Index(1)
    @Column(comment = "父文件夹ID", notNull = true, defaultVal = "0")
    private String pid;

    /**
     * 文件夹路径
     */
    @Index(2)
    @Column(comment = "文件夹路径", notNull = true)
    private String path;

    /**
     * 文件夹深度
     */
    @Index(3)
    @Column(comment = "文件夹深度")
    private Integer depth;

    @Override
    public SysFolderResp toResp() {
        SysFolderResp sysFolderResp = new SysFolderResp();
        sysFolderResp.setId(id);
        sysFolderResp.setPid(pid);
        sysFolderResp.setPath(path);
        sysFolderResp.setDepth(depth);
        return sysFolderResp;
    }
}
