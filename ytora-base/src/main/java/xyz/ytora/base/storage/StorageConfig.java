package xyz.ytora.base.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.ytora.base.storage.support.LocalFileStorageServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * created by yangtong on 2025/4/11 22:18:20
 * <br/>
 * 文件存储配置
 */
@Configuration
public class StorageConfig {

    @Bean
    public IFileStorageService create(FileStorageProperty fileStorageProperty) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends IBucketStrategy> strategy = fileStorageProperty.getStrategy();
        if (strategy == null) {
            throw new IllegalArgumentException("必须指定文件模块的桶策略");
        }
        IBucketStrategy strategyImpl = strategy.getConstructor().newInstance();
        return new LocalFileStorageServiceImpl(fileStorageProperty.getPath(), strategyImpl);
    }

}
