package xyz.ytora.core.sys.file.logic;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.ytora.base.RespUtil;
import xyz.ytora.base.download.Mimes;
import xyz.ytora.base.enums.RespCode;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.exception.DownloadException;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.base.storage.IFileStorageService;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.resp.SysFileRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.io.Ios;
import xyz.ytora.ytool.str.Strs;

import java.io.IOException;
import java.io.InputStream;

/**
 * created by YT on 2025/12/28 00:53:05
 * <br/>
 */
@Service
@RequiredArgsConstructor
public class SysFileLogic extends BaseLogic<SysFile, SysFileRepo> {

    private final SQLHelper sqlHelper;
    private final IFileStorageService fileStorageService;

    public void delete(String id) {
        SelectBuilder selectBuilder = BaseApi.query();
        ConditionExpressionBuilder where = selectBuilder.getWhereStage().getWhere();
        if (Strs.isNotEmpty(where.build())) {
            repository.delete(where);
        }
    }

    public String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        String fileSizeText = Strs.formatSize(fileSize);
        String fileType = file.getContentType();
        try (InputStream is = file.getInputStream()) {
            //上传文件
            String fileId = fileStorageService.upload(is);
            //保持文件上传记录
            SysFile sysFile = new SysFile();
            sysFile.setFileId(fileId);
            sysFile.setFileName(fileName);
            sysFile.setFileSize(fileSize);
            sysFile.setFileSizeText(fileSizeText);
            sysFile.setFileType(fileType);
            sysFile.setDownloadCount(0);
            repository.insert(sysFile);

            return fileId;
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    public void download(String fileId) {
        SysFile sysFile = repository.one(w -> w.eq(SysFile::getFileId, fileId));
        if (sysFile == null) {
            throw new DownloadException(RespCode.FILE_NOT_FOUND);
        }
        //获取文件流
        try (InputStream inputStream = fileStorageService.download(fileId)) {
            HttpServletResponse resp = ScopedValueItem.RESPONSE.get();

            //设置响应头
            RespUtil.downloadResponse(sysFile.getFileName(), Mimes.get(sysFile.getFileType()));
            resp.setContentLengthLong(sysFile.getFileSize());
            Ios.copy(inputStream, resp.getOutputStream());

            //刷新
            resp.flushBuffer();

            sqlHelper.update(SysFile.class)
                    .set(SysFile::getDownloadCount, sysFile.getDownloadCount() + 1)
                    .where(w -> w.eq(SysFile::getId, sysFile.getId()));
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }


}
