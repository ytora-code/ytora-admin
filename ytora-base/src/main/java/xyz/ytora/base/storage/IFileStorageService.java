package xyz.ytora.base.storage;

import lombok.NonNull;

import java.io.InputStream;

/**
 * 文件持久化存储规范
 */
public interface IFileStorageService {
    /**
     * 上传文件
     * @param inputStream 上传的数据流
     * @return 上传后得到的文件id
     */
    String upload(InputStream inputStream);

    /**
     * 根据文件id响应文件二进制流
     * @param fileId 文件id
     * @return 要读取文件的输入流
     */
    InputStream download(@NonNull String fileId);

    /**
     * 根据文件id删除文件
     * @param fileId 文件id
     */
    void remove(@NonNull String fileId);

    /**
     * 判断指定文件是否存在
     * @param fileId 文件id
     * @return 如果文件存在返回File对象，不存在返回null
     */
    Boolean exist(@NonNull String fileId);
}
