package xyz.ytora.core.sys.file.logic;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.ytora.base.RespUtil;
import xyz.ytora.base.download.Mimes;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.exception.DownloadException;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.BaseLogic;
import xyz.ytora.base.mvc.RespCode;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.base.storage.IFileStorageService;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.req.SysFolderReq;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;
import xyz.ytora.core.sys.file.model.resp.SysFolderResp;
import xyz.ytora.core.sys.file.resp.SysFileRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.sql4j.util.OrmUtil;
import xyz.ytora.ytool.io.Ios;
import xyz.ytora.ytool.str.Strs;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * created by YT on 2025/12/28 00:53:05
 * <br/>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysFileLogic extends BaseLogic<SysFile, SysFileRepo> {

    // 屏蔽 Windows 和 Linux 下非法的文件夹字符
    // 包含：\ / : * ? " < > |
    private static final Pattern INVALID_CHARS_PATTERN = Pattern.compile("[\\\\/:*?\"<>|]");

    private final SQLHelper sqlHelper;
    private final IFileStorageService fileStorageService;

    /**
     * 根据PID获取文件夹
     */
    public List<SysFolderResp> listFolderByPid(String pid) {
        // 查询文件夹
        List<SysFolder> folders = sqlHelper.select().from(SysFolder.class).where(w -> w.eq(SysFolder::getPid, pid)).submit(SysFolder.class);
        List<SysFolderResp> folderRespList = folders.stream().map(SysFolder::toResp).peek(folder -> {
            folder.setType(1);
            folder.setIsLeaf(false);
        }).toList();

        // 查询文件
        List<SysFile> files = repository.list(w -> w.eq(SysFile::getFolderId, pid));
        List<SysFolderResp> fileRespList = files.stream().map(file -> {
            SysFolderResp folder = new SysFolderResp();
            folder.setId(file.getId());
            folder.setPath(file.getFileName());
            folder.setExt(file.getFileType());
            folder.setType(2);
            folder.setIsLeaf(true);
            return folder;
        }).toList();

        // 合并数据
        ArrayList<SysFolderResp> all = new ArrayList<>(folderRespList);
        all.addAll(fileRespList);
        return all;
    }

    /**
     * 添加或修改文件夹
     */
    public SysFolderResp insertOrUpdateFolder(SysFolderReq data) {
        // 校验
        validateFolderName(data.getPath());

        // 新增
        if (Strs.isEmpty(data.getId())) {
            // 获取上一层级深度
            int depth;
            String pid = "0";
            if (Strs.isNotEmpty(data.getPid())) {
                List<Integer> depths = sqlHelper.select(SysFolder::getDepth).from(SysFolder.class).where(w -> w.eq(SysFolder::getId, data.getPid())).submit(Integer.class);
                depth = depths.isEmpty() ? 0 : depths.getFirst();
                pid = data.getPid();
            } else {
                depth = 0;
            }

            SysFolder entity = data.toEntity();
            entity.setDepth(depth);
            entity.setPid(pid);
            OrmUtil.insert(SysFolder.class, entity);
            return entity.toResp();
        }
        // 编辑，只能编辑文件夹名称
        else {
            sqlHelper.update(SysFolder.class).set(SysFolder::getPath, data.getPath()).where(w -> w.eq(SysFolder::getId, data.getId())).submit();

            SysFolderResp folderResp = new SysFolderResp();
            folderResp.setId(data.getId());
            folderResp.setPid(data.getPid());
            folderResp.setPath(data.getPath());
            folderResp.setType(1);
            return folderResp;
        }
    }

    public void delete(String id) {
        SelectBuilder selectBuilder = BaseApi.query();
        ConditionExpressionBuilder where = selectBuilder.getWhereStage().getWhere();
        if (Strs.isNotEmpty(where.build())) {
            repository.delete(where);
        }
    }

    public SysFileResp upload(MultipartFile file, String folderId) {
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
            repository.insert(sysFile);

            return sysFile.toResp();
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    public void download(String fileId) {
        log.info("开始下载");
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
        } finally {
            log.info("下载完毕");
        }
    }

    /**
     * 校验文件夹名称是否合法
     */
    public static void validateFolderName(String name) {
        // 1. 判空
        if (Strs.isEmpty(name)) {
            throw new BaseException("名称不能为空");
        }

        // 2. 长度校验 (数据库通常限制 255，建议业务限制更短)
        if (name.length() > 20) {
            throw new BaseException("名称过长，请保持在20个字符以内");
        }

        // 3. 路径穿越校验 (防止攻击者通过 ../ 访问上级目录)
        if (name.contains("..")) {
            throw new BaseException("名称中不能包含路径跳转符 '..'");
        }

        // 4. 非法字符校验
        if (INVALID_CHARS_PATTERN.matcher(name).find()) {
            throw new BaseException("名称不能包含非法字符: \\ / : * ? \" < > |");
        }

        // 5. 特殊系统保留名校验
        String[] reservedNames = {"CON", "PRN", "AUX", "NUL", "COM1", "LPT1"};
        for (String reserved : reservedNames) {
            if (name.equalsIgnoreCase(reserved)) {
                throw new BaseException("名称不能使用系统保留字: " + reserved);
            }
        }
    }

}
