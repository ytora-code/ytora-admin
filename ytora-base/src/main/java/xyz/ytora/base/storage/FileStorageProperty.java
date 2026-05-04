package xyz.ytora.base.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created by YT on 2025/12/28 01:14:35
 * <br/>
 */
@Data
@Component
@ConfigurationProperties(prefix = "ytora.storage")
public class FileStorageProperty {
    /**
     * 文件存储类型，也就是IFileStorageService的一个实现类
     */
    private Class<? extends IFileStorageService> type;

    /**
     * 文件桶产生策略
     */
    private Class<? extends IBucketStrategy> strategy;

    /**
     * 文件最大上传的字节数(byte)
     */
    private String max;

    private String path;
}
