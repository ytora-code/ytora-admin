package xyz.ytora.base.storage.support;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ytora.base.enums.RespCode;
import xyz.ytora.base.storage.FileMeta;
import xyz.ytora.base.storage.IFileStorageService;
import xyz.ytora.base.storage.StorageException;
import xyz.ytora.ytool.file.FileTypeDetector;
import xyz.ytora.ytool.json.Jsons;
import xyz.ytora.ytool.str.Strs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 以本地磁盘作为文件持久化存储介质
 */
public class LocalFileStorageServiceImpl implements IFileStorageService {

    private static final Logger log = LoggerFactory.getLogger(LocalFileStorageServiceImpl.class);
    private final String FILE_UPLOAD_PATH;
    private final String DATA_FILE = "data";
    private final String META_FILE = "meta.json";

    public LocalFileStorageServiceImpl(String uploadPath) {
        if (Strs.isEmpty(uploadPath)) {
            uploadPath = "file-storage/";
        }
        if (!uploadPath.endsWith("/")) {
            uploadPath = uploadPath + "/";
        }
        FILE_UPLOAD_PATH = uploadPath;
        log.info("当前的文件存储类：{}", this.getClass().getName());
        log.info("文件会被上传到：{}", FILE_UPLOAD_PATH);
    }

    /**
     * 上传文件
     */
    @Override
    public String upload(InputStream is) {
        try {
            // 使用当前时间戳的36进制+6位随机字符串作为文件id
            String fileId = Long.toString(System.currentTimeMillis(), Character.MAX_RADIX).toUpperCase() + "-" + Strs.randomStr(6, 0, 61);
            // 判断文件类型
            String type = FileTypeDetector.detectFileType(is);

            FileMeta metadata = new FileMeta();
            metadata.setFileId(fileId);
            metadata.setContentType(type);

            return upload(is, metadata);
        } catch (IOException e) {
            throw new StorageException(RespCode.DETECT_FILE_TYPE_FAIL);
        }
    }

    public String upload(InputStream is, @NonNull FileMeta metadata) {
        // 确保为包装流
        if (!(is instanceof BufferedInputStream)) {
            is = new BufferedInputStream(is);
        }
        String fileId = metadata.getFileId();
        if (fileId == null) {
            throw new StorageException("上传文件时，fileId不能为空");
        }

        Path path = getPathByFileId(fileId);

        Path dataFile = path.resolve(DATA_FILE);
        Path metadataFile = path.resolve(META_FILE);

        try {
            //创建文件夹
            Files.createDirectories(path);

            //创建文件并写入数据
            long size = Files.copy(is, dataFile);

            //写入元数据
            metadata.setSize(size);
            metadata.setUploadTime(System.currentTimeMillis());

            String metadataJson = Jsons.toJsonStr(metadata);
            Files.writeString(metadataFile, metadataJson);
        } catch (IOException e) {
            // 失败时清理已创建的文件
            clean(path);
            throw new StorageException(e);
        }
        log.info("文件上传成功，fileId: {}", fileId);
        return fileId;
    }

    /**
     * 根据fileId获取文件流
     * @param fileId 文件ID
     * @return 文件输入流，调用方负责关闭流
     */
    @Override
    public InputStream download(@NonNull String fileId) {
        Path path = getExistingPath(fileId);

        try {
            return Files.newInputStream(path.resolve(DATA_FILE));
        } catch (IOException e) {
            throw new StorageException(RespCode.FILE_DOWNLOAD_FAIL);
        }
    }

    /**
     * 根据fileId删除文件
     */
    @Override
    public void remove(@NonNull String fileId) {
        clean(getExistingPath(fileId));
    }

    /**
     * 根据fileId判断文件是否存在
     */
    @Override
    public Boolean exist(@NonNull String fileId) {
        try {
            Path filePath = getPathByFileId(fileId);
            // 检查实际文件是否存在
            return Files.exists(filePath.resolve(DATA_FILE));
        } catch (Exception e) {

            return false; // fileId格式错误时返回false
        }
    }

    /**
     * 获取文件元数据
     */
    public FileMeta getMetadata(@NonNull String fileId) {
        Path path = getExistingPath(fileId);
        Path metadataFile = path.resolve(META_FILE);

        try {
            String metadataJson = Files.readString(metadataFile);
            return Jsons.fromJsonStr(metadataJson, FileMeta.class);
        } catch (IOException e) {
            throw new StorageException(RespCode.ERROR_FILE_METADATA);
        }
    }

    /**
     * 根据fileId判断文件是否存在
     */
    private Path getExistingPath(@NonNull String fileId) {
        Path filePath = getPathByFileId(fileId);
        if (!Files.exists(filePath)) {
            throw new StorageException(RespCode.FILE_NOT_FOUND);
        }
        return filePath;
    }

    /**
     * 根据fileId获取文件路径
     */
    private Path getPathByFileId(String fileId) {

        if (fileId == null || !fileId.matches("^[A-Za-z0-9\\-]{10,}$")) {
            throw new StorageException(RespCode.ERROR_FILE_ID);
        }

        String hash = Strs.md5(fileId);

        //根据fileId的hash值，产生多层级文件
        Path basePath = Path.of(FILE_UPLOAD_PATH);
        Path level1Path = basePath.resolve(hash.substring(0, 2));
        Path level2Path = level1Path.resolve(hash.substring(2, 4));
        Path level3Path = level2Path.resolve(hash.substring(4, 6));

        return level3Path.resolve(fileId);
    }

    private void clean(Path path) {
        try {
            if (Files.exists(path)) {
                Files.walkFileTree(path, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.deleteIfExists(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.deleteIfExists(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (IOException e) {
            log.warn("清理失败的上传文件时出错，fileId目录: {}, 错误: {}",
                    path, e.getMessage());
            throw new StorageException(RespCode.FILE_DELETE_FAIL);
        }
    }

}
