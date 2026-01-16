package xyz.ytora.base.storage.support;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ytora.base.mvc.RespCode;
import xyz.ytora.base.storage.IBucketStrategy;
import xyz.ytora.base.storage.IFileStorageService;
import xyz.ytora.base.storage.StorageException;
import xyz.ytora.base.storage.Target;
import xyz.ytora.ytool.str.Strs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LocalFileStorageServiceImpl implements IFileStorageService {

    private static final Logger log = LoggerFactory.getLogger(LocalFileStorageServiceImpl.class);

    // 文件根目录
    private final Path basePath;
    // 绝对规范化，用于越界校验
    private final Path basePathAbsNorm;
    private final IBucketStrategy strategy;

    private static final String DATA_SUFFIX = ".data";

    // 重试次数，写入文件前会检查文件是否也存在，如果存在就重新产生 fileId
    private static final int MAX_ID_RETRY = 1000;

    public LocalFileStorageServiceImpl(String uploadPath, @NonNull IBucketStrategy strategy) {
        if (Strs.isEmpty(uploadPath)) {
            uploadPath = "file-storage/";
        }
        this.basePath = Path.of(uploadPath);
        this.basePathAbsNorm = this.basePath.toAbsolutePath().normalize();
        this.strategy = strategy;

        log.info("当前的文件存储类：{}", this.getClass().getName());
        log.info("文件会被上传到：{}", this.basePathAbsNorm);
    }

    /**
     * 上传文件：返回 fileId
     * 约束：
     * - 生成 fileId 后先检查是否存在；存在则重试生成
     * - 真正写入时仍可能发生极小概率并发冲突（窗口期），这时直接报错（不重试）
     */
    @Override
    public String upload(InputStream is) {
        if (is == null) {
            throw new StorageException("InputStream不能为空");
        }

        // 1) 循环生成并选择一个“当前不存在”的 fileId
        Target target = chooseNonExistingTarget();

        // 2) 真正写入文件
        try {
            Files.createDirectories(target.getDirAbsNorm());
            Files.copy(is, target.getDataFileAbsNorm());
        } catch (FileAlreadyExistsException e) {
            // 检查通过到写入这段极短窗口发生并发冲突：直接报错
            throw new StorageException("写入时发生并发fileId冲突: " + target.getFileId(), e);
        } catch (IOException e) {
            // 不要求清理桶目录；文件如果写了一半，deleteIfExists 尽量清理掉
            safeDelete(target.getDataFileAbsNorm());
            throw new StorageException(e);
        }

        log.info("文件上传成功，fileId: {}", target.getFileId());
        return target.getFileId();
    }

    /**
     * 外部指定 fileId 上传
     * 语义：
     * - fileId 必须合法
     * - 如果对应文件已存在：直接报错
     */
    public String upload(@NonNull InputStream is, @NonNull String fileId) {
        validateFileId(fileId);
        Target target = resolveTargetForGivenFileId(fileId);
        try {
            Files.createDirectories(target.getDirAbsNorm());
            Files.copy(is, target.getDataFileAbsNorm());
        } catch (FileAlreadyExistsException e) {
            throw new StorageException("fileId已存在: " + fileId, e);
        } catch (IOException e) {
            safeDelete(target.getDataFileAbsNorm());
            throw new StorageException(e);
        }

        log.info("文件上传成功（外部指定fileId），fileId: {}", fileId);
        return fileId;
    }


    /**
     * 下载
     */
    @Override
    public InputStream download(@NonNull String fileId) {
        validateFileId(fileId);

        Path dataFile = resolveDataFile(fileId);
        if (!Files.exists(dataFile)) throw new StorageException(RespCode.FILE_NOT_FOUND);

        try {
            return Files.newInputStream(dataFile, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new StorageException(RespCode.FILE_DOWNLOAD_FAIL);
        }
    }

    /**
     * 删除
     */
    @Override
    public void remove(@NonNull String fileId) {
        validateFileId(fileId);

        Path dataFile = resolveDataFile(fileId);
        boolean deleted = safeDelete(dataFile);
        if (!deleted) throw new StorageException(RespCode.FILE_NOT_FOUND);
    }

    /**
     * 是否存在
     */
    @Override
    public Boolean exist(@NonNull String fileId) {
        try {
            validateFileId(fileId);
            return Files.exists(resolveDataFile(fileId));
        } catch (Exception e) {
            return false;
        }
    }

    // -------------------- 选择一个当前不存在的 fileId --------------------

    private Target chooseNonExistingTarget() {
        for (int i = 0; i < MAX_ID_RETRY; i++) {
            String fileId = strategy.fileId();
            validateFileId(fileId);

            // 计算桶目录（相对）-> 拼到 basePath（绝对规范化）并做越界防护
            Path relBucket = strategy.bucketDir(fileId);
            if (relBucket == null) {
                throw new StorageException("bucketDir返回null");
            }
            if (relBucket.isAbsolute()) {
                throw new StorageException("bucketDir必须返回相对路径，不允许绝对路径: " + relBucket);
            }

            Path dirAbsNorm = basePathAbsNorm.resolve(relBucket).normalize();
            ensureUnderBase(dirAbsNorm);

            Path dataFileAbsNorm = dirAbsNorm.resolve(fileId + DATA_SUFFIX).normalize();
            ensureUnderBase(dataFileAbsNorm);

            // “写入前检查”：存在就换一个 id
            if (Files.exists(dataFileAbsNorm)) {
                continue;
            }

            return new Target(fileId, dirAbsNorm, dataFileAbsNorm);
        }
        throw new StorageException("fileId生成重试次数过多（可能是策略异常或目录被污染），请检查");
    }

    // -------------------- 路径解析与校验 --------------------

    private Path resolveDataFile(String fileId) {
        Path relBucket = strategy.bucketDir(fileId);
        if (relBucket == null) {
            throw new StorageException("bucketDir返回null");
        }
        if (relBucket.isAbsolute()) {
            throw new StorageException("bucketDir必须返回相对路径");
        }

        Path dirAbsNorm = basePathAbsNorm.resolve(relBucket).normalize();
        ensureUnderBase(dirAbsNorm);

        Path dataFileAbsNorm = dirAbsNorm.resolve(fileId + DATA_SUFFIX).normalize();
        ensureUnderBase(dataFileAbsNorm);

        return dataFileAbsNorm;
    }

    private Target resolveTargetForGivenFileId(String fileId) {
        Path relBucket = strategy.bucketDir(fileId);
        if (relBucket == null) {
            throw new StorageException("bucketDir返回null");
        }
        if (relBucket.isAbsolute()) {
            throw new StorageException("bucketDir必须返回相对路径");
        }

        Path dirAbsNorm = basePathAbsNorm.resolve(relBucket).normalize();
        ensureUnderBase(dirAbsNorm);

        Path dataFileAbsNorm = dirAbsNorm.resolve(fileId + DATA_SUFFIX).normalize();
        ensureUnderBase(dataFileAbsNorm);

        return new Target(fileId, dirAbsNorm, dataFileAbsNorm);
    }

    private void validateFileId(String fileId) {
        if (fileId == null || !fileId.matches("^[A-Za-z0-9\\-]{10,}$")) {
            throw new StorageException(RespCode.ERROR_FILE_ID);
        }
    }

    /**
     * 防止 bucketDir 返回 ../ 越界写入
     */
    private void ensureUnderBase(Path pathAbsNorm) {
        // pathAbsNorm 已经是绝对 + normalize
        if (!pathAbsNorm.startsWith(basePathAbsNorm)) {
            throw new StorageException("非法路径越界: " + pathAbsNorm);
        }
    }

    private boolean safeDelete(Path p) {
        try {
            return Files.deleteIfExists(p);
        } catch (IOException e) {
            log.warn("删除失败: {}, err={}", p, e.getMessage());
            throw new StorageException(RespCode.FILE_DELETE_FAIL);
        }
    }


}