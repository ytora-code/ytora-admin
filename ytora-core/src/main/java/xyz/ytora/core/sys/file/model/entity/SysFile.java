package xyz.ytora.core.sys.file.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseEntity;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.sql4j.anno.Table;
import xyz.ytora.sql4j.enums.IdType;
import xyz.ytora.ytool.anno.Index;

/**
 * 系统文件表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_file", idType = IdType.SNOWFLAKE, createIfNotExist = true, comment = "系统文件表")
public class SysFile extends BaseEntity<SysFile> {
    /**
     * 文件id，全局唯一，根据该id可以找到一个唯一对应的文件
     */
    @Index(1)
    @Column(comment = "文件id", notNull = true)
    private String fileId;

    @Index(2)
    @Column(comment = "所在文件夹")
    private String folderId;

    /**
     * 原始文件名称
     */
    @Index(3)
    @Column(comment = "原始文件名称")
    private String fileName;

    /**
     * 文件大小，单位字节
     */
    @Index(4)
    @Column(comment = "文件大小，单位字节")
    private Long fileSize;

    /**
     * 文件大小-文本
     */
    @Index(5)
    @Column(comment = "文件大小-文本")
    private String fileSizeText;

    /**
     * 文件类型
     */
    @Index(6)
    @Column(comment = "文件类型")
    private String fileType;

    /**
     * 下载次数
     */
    @Index(7)
    @Column(comment = "下载次数")
    private Integer downloadCount;

    @Override
    public SysFileResp toResp() {
        return SysFileMapper.mapper.toResp(this);
    }
}
