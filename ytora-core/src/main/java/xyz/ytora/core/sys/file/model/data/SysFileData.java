package xyz.ytora.core.sys.file.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.basemodel.BaseData;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.excel.SysFileExcel;

/**
 * 系统文件响应数据
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统文件表响应数据")
public class SysFileData extends BaseData<SysFile> {

    /**
     * 文件id
     */
    @Schema(description = "文件id")
    private String fileId;

    /**
     * 所在文件夹
     */
    @Schema(description = "所在文件夹")
    private String folderId;

    /**
     * 原始文件名称
     */
    @Schema(description = "原始文件名称")
    private String fileName;

    /**
     * 文件大小，单位字节
     */
    @Schema(description = "文件大小，单位字节")
    private Long fileSize;

    /**
     * 文件大小-文本
     */
    @Schema(description = "文件大小-文本")
    private String fileSizeText;

    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    private String fileType;

    /**
     * 下载次数
     */
    @Schema(description = "下载次数")
    private Integer downloadCount;

    /**
     * 文件是否存在
     */
    @Schema(description = "文件是否存在")
    private Boolean fileExist;

    @Override
    public SysFileExcel toExcel() {
        SysFileMapper mapper = SysFileMapper.mapper;
        return mapper.toExcel(this);
    }
}
