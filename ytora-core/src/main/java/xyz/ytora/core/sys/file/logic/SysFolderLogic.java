package xyz.ytora.core.sys.file.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseLogic;
import xyz.ytora.base.storage.IFileStorageService;
import xyz.ytora.core.sys.file.model.data.SysFolderData;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.param.SysFolderParam;
import xyz.ytora.core.sys.file.repo.SysFolderRepo;
import xyz.ytora.toolkit.text.Strs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * created by YT on 2025/12/28 00:53:05
 * <br/>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysFolderLogic extends BaseLogic<SysFolder, SysFolderRepo> {

    // 屏蔽 Windows 和 Linux 下非法的文件夹字符
    // 包含：\ / : * ? " < > |
    private static final Pattern INVALID_CHARS_PATTERN = Pattern.compile("[\\\\/:*?\"<>|]");

    private final IFileStorageService fileStorageService;

    /**
     * 根据PID获取文件夹
     */
    public List<SysFolderData> listFolderByPid(String pid) {
        // 查询文件夹
        List<SysFolder> folders = select().from(SysFolder.class).where(w -> w.eq(SysFolder::getPid, pid)).orderByAsc(SysFolder::getId).submit(SysFolder.class);
        List<SysFolderData> folderRespList = folders.stream().map(SysFolder::toData).peek(folder -> {
            folder.setType(1);
            folder.setIsLeaf(false);
        }).toList();

        // 查询文件
        List<SysFile> files = select().from(SysFile.class).where(w -> w.eq(SysFile::getFolderId, pid)).orderByAsc(SysFile::getId).submit(SysFile.class);
        List<SysFolderData> fileRespList = files.stream().map(file -> {
            SysFolderData folder = new SysFolderData();
            folder.setId(file.getId());
            folder.setPath(file.getFileName());
            folder.setExt(file.getFileType());
            folder.setType(2);
            folder.setIsLeaf(true);
            return folder;
        }).toList();

        // 合并数据
        ArrayList<SysFolderData> all = new ArrayList<>(folderRespList);
        all.addAll(fileRespList);
        return all;
    }

    /**
     * 添加或修改文件夹
     */
    public SysFolderData insertOrUpdateFolder(SysFolderParam data) {
        // 校验同级文件夹下是否有重名的子文件夹
        if (Strs.isEmpty(data.getPid())) {
            data.setPid("0");
        }
        List<Map<String, Object>> count = select(SysFolder::getId).from(SysFolder.class)
                .where(w -> w.eq(SysFolder::getPid, data.getPid()).eq(SysFolder::getPath, data.getPath())).submit();
        if (!count.isEmpty()) {
            throw new BaseException("禁止重名的文件夹!");
        }

        // 校验文件夹名称有消息
        validateFolderName(data.getPath());

        // 新增
        if (Strs.isEmpty(data.getId())) {
            // 获取上一层级深度
            int depth;
            List<Integer> depths = select(SysFolder::getDepth).from(SysFolder.class).where(w -> w.eq(SysFolder::getId, data.getPid())).submit(Integer.class);
            depth = depths.isEmpty() ? 0 : depths.getFirst();

            SysFolder entity = data.toEntity();
            entity.setDepth(depth);
            entity.setPid(data.getPid());
            insert(SysFolder.class).into().values(entity).submit();
            return entity.toData();
        }
        // 编辑，只能编辑文件夹名称
        else {
            update(SysFolder.class).set(SysFolder::getPath, data.getPath()).where(w -> w.eq(SysFolder::getId, data.getId())).submit();

            SysFolderData folderResp = new SysFolderData();
            folderResp.setId(data.getId());
            folderResp.setPid(data.getPid());
            folderResp.setPath(data.getPath());
            folderResp.setType(1);
            return folderResp;
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
