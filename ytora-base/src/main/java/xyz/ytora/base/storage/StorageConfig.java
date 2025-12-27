package xyz.ytora.base.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ytora.base.storage.support.LocalFileStorageServiceImpl;

import java.util.Map;

/**
 * created by yangtong on 2025/4/11 22:18:20
 * <br/>
 * 文件存储配置
 */
@Configuration
public class StorageConfig {

    @Bean
    public IFileStorageService create(FileStorageProperty fileStorageProperty) {
        LocalFileStorageServiceImpl localFileStorageService = new LocalFileStorageServiceImpl(fileStorageProperty.getPath());
        return localFileStorageService;
    }

}
