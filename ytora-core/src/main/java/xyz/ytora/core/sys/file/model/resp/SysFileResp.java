package xyz.ytora.core.sys.file.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.ytora.base.mvc.BaseResp;
import xyz.ytora.core.sys.file.model.entity.SysFile;

/**
 * 系统文件表响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysFileResp extends BaseResp<SysFile> {

    /**
     * 文件id，全局唯一，根据该id可以找到一个唯一对应的文件
     */
    private String fileId;

    /**
     * 原始文件名称
     */
    private String fileName;

    /**
     * 文件大小，单位字节
     */
    private Long fileSize;

    /**
     * 文件大小
     */
    private String fileSizeText;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 下载次数
     */
    private Integer downloadCount;

}
