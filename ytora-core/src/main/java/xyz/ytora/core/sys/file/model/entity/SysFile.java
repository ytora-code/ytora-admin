package xyz.ytora.core.sys.file.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseEntity;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.data.SysFileData;
import xyz.ytora.sqlux.core.anno.Column;
import xyz.ytora.sqlux.core.anno.Table;
import xyz.ytora.sqlux.core.enums.IdType;

/**
 * 系统文件表
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_file", idType = IdType.SNOWFLAKE, comment = "系统文件表")
public class SysFile extends BaseEntity<SysFile> {

    /**
     * 文件id
     */
    @Column(comment = "文件id", notNull = true)
    private String fileId;

    /**
     * 所在文件夹
     */
    @Column(comment = "所在文件夹")
    private String folderId;

    /**
     * 原始文件名称
     */
    @Column(comment = "原始文件名称")
    private String fileName;

    /**
     * 文件大小，单位字节
     */
    @Column(comment = "文件大小，单位字节")
    private Long fileSize;

    /**
     * 文件大小-文本
     */
    @Column(comment = "文件大小-文本")
    private String fileSizeText;

    /**
     * 文件类型
     */
    @Column(comment = "文件类型")
    private String fileType;

    /**
     * 下载次数
     */
    @Column(comment = "下载次数")
    private Integer downloadCount;

    @Override
    public SysFileData toData() {
        SysFileMapper mapper = SysFileMapper.mapper;
        return mapper.toData(this);
    }
}
