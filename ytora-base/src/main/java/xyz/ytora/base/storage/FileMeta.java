package xyz.ytora.base.storage;

import lombok.Data;

/**
 * 文件元数据
 */
@Data
public class FileMeta {
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 元素文件名称
     */
    private String originalName;
    /**
     * 文件字节数
     */
    private long size;
    /**
     * 文件类型
     */
    private String contentType;
    /**
     * 上传时间
     */
    private long uploadTime;
    /**
     * 上传者
     */
    private String uploadUser;
}
