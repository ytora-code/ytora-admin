package xyz.ytora.core.sys.file.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.mvc.enums.Mimes;
import xyz.ytora.base.mvc.result.ResultCode;
import xyz.ytora.base.storage.IFileStorageService;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.core.sys.file.model.data.SysFileData;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.param.SysFileParam;
import xyz.ytora.core.sys.file.repo.SysFileRepo;
import xyz.ytora.toolkit.io.Ios;
import xyz.ytora.toolkit.text.Strs;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * created by YT on 2025/12/28 00:53:05
 * <br/>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysFileLogic extends BaseLogic<SysFile, SysFileRepo> {

    private final IFileStorageService fileStorageService;

    /**
     * 根据ID查询
     */
    public SysFileData findFileById(String id) {
        SysFile sysFile = super.queryById(id);
        if (sysFile == null) {
            throw new BaseException("文件[" + id + "]不存在");
        }
        SysFileData resp = sysFile.toData();
        resp.setFileExist(fileStorageService.exist(sysFile.getFileId()));
        return resp;
    }

    /**
     * 新增或编辑文件
     */
    public void insertOrUpdate(SysFileParam data) {
        // 校验所属文件夹下是否有重名的文件
        List<Map<String, Object>> count = select(SysFile::getId).from(SysFile.class)
                .where(w -> w.eq(SysFile::getFolderId, data.getFolderId()).eq(SysFile::getFileName, data.getFileName())).submit();
        if (!count.isEmpty()) {
            throw new BaseException("禁止重名的文件!");
        }

        super.upsert(data.toEntity());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteFileById(String id) {
        SysFileData fileResp = findFileById(id);
        if (fileResp == null) {
            throw new BaseException("文件在数据库中不存在");
        }

        // 删除数据库
        deleteByIds(id);

        // 删除磁盘文件
        fileStorageService.remove(fileResp.getFileId());
    }

    public SysFileData upload(MultipartFile file, String folderId) {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        String fileSizeText = Strs.formatSize(fileSize);
        String fileType = file.getContentType();
        try (InputStream is = file.getInputStream()) {
            //上传文件
            String fileId = fileStorageService.upload(is);
            //保持文件上传记录
            SysFile sysFile = new SysFile();
            sysFile.setFolderId(folderId);
            sysFile.setFileId(fileId);
            sysFile.setFileName(fileName);
            sysFile.setFileSize(fileSize);
            sysFile.setFileSizeText(fileSizeText);
            sysFile.setFileType(fileType);
            sysFile.setDownloadCount(0);
            insert(SysFile.class).into().values(sysFile).submit();

            return sysFile.toData();
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    public void download(String id) {
        log.info("开始下载");
        SysFileData file = findFileById(id);
        if (file == null) {
            throw new BaseException(ResultCode.FILE_NOT_FOUND);
        }
        //获取文件流
        String fileId = file.getFileId();
        try (InputStream inputStream = fileStorageService.download(fileId)) {

            //设置响应头
            HttpUtil.setDownload(file.getFileName(), Mimes.get(file.getFileType()), response -> {
                response.setContentLengthLong(file.getFileSize());
                try {
                    Ios.copy(inputStream, response.getOutputStream());
                    //刷新
                    response.flushBuffer();
                } catch (IOException e) {
                    throw new BaseException("响应文件失败", e);
                }
            });

            update(SysFile.class)
                    .set(SysFile::getDownloadCount, file.getDownloadCount() + 1)
                    .where(w -> w.eq(SysFile::getId, file.getId()))
                    .submit();
        } catch (IOException e) {
            throw new BaseException(e);
        } finally {
            log.info("下载完毕");
        }
    }


}
