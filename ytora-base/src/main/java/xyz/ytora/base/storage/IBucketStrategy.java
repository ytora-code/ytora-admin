package xyz.ytora.base.storage;

import java.nio.file.Path;

/**
 * created by YT on 2026/1/16 23:30:13
 * <br/>
 * 文件桶产生策略
 * 桶跟 fileId 是强关联的，所以该接口有一个产生 fileId 的方法，必须使用当前策略产生的 fileId，才能正确得到桶
 */
public interface IBucketStrategy {

    /**
     * 文件 ID 产生器
     * @return 文件ID
     */
    String fileId();

    /**
     * 根据 fileId 得到该文件应该放在哪个桶下
     * @param fileId 文件 ID
     * @return 所属桶的 PATH
     */
    Path bucketDir(String fileId);

}
