package xyz.ytora.core.sys.file.model.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseExcel;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.toolkit.document.excel.Excel;

/**
 * EXCEL请求数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Excel("系统文件列表")
public class SysFileExcel extends BaseExcel<SysFile> {

    /**
     * 文件id
     */
    @Excel("文件id")
    private String fileId;

    /**
     * 所在文件夹
     */
    @Excel("所在文件夹")
    private String folderId;

    /**
     * 原始文件名称
     */
    @Excel("原始文件名称")
    private String fileName;

    /**
     * 文件大小，单位字节
     */
    @Excel("文件大小，单位字节")
    private Long fileSize;

    /**
     * 文件大小-文本
     */
    @Excel("文件大小-文本")
    private String fileSizeText;

    /**
     * 文件类型
     */
    @Excel("文件类型")
    private String fileType;

    /**
     * 下载次数
     */
    @Excel("下载次数")
    private Integer downloadCount;

    @Override
    public SysFile toEntity() {
        SysFileMapper mapper = SysFileMapper.mapper;
        return mapper.toEntity(this);
    }
}
