package xyz.ytora.core.sys.file.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseReq;
import xyz.ytora.core.sys.file.model.SysFileMapper;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.sql4j.anno.Column;
import xyz.ytora.ytool.anno.Index;

/**
 * 系统文件表请求数据
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "文件表请求数据")
public class SysFileReq extends BaseReq<SysFile> {

    /**
     * 文件id，全局唯一，根据该id可以找到一个唯一对应的文件
     */
    @Schema(description = "文件id")
    private String fileId;

    /**
     * 文件夹id
     */
    @Schema(description = "文件夹id")
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
     * 文件类型
     */
    @Schema(description = "文件类型")
    private String fileType;


    @Override
    public SysFile toEntity() {
        return SysFileMapper.mapper.toEntity(this);
    }
}
