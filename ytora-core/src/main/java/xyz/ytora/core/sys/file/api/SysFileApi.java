package xyz.ytora.core.sys.file.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.ytora.base.download.DownloadMapper;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.sys.file.logic.SysFileLogic;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.req.SysFileReq;
import xyz.ytora.core.sys.file.model.req.SysFolderReq;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;
import xyz.ytora.core.sys.file.model.resp.SysFolderResp;
import xyz.ytora.core.sys.file.resp.SysFileRepo;
import xyz.ytora.sql4j.core.SQLHelper;
import xyz.ytora.sql4j.enums.OrderType;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.tree.Trees;

import java.util.List;

/**
 * created by YT on 2025/12/28 00:53:46
 * <br/>
 * 文件
 */
@RestController
@RequestMapping("/sys/file")
@Tag(name = "文件")
@RequiredArgsConstructor
public class SysFileApi extends BaseApi<SysFile, SysFileLogic, SysFileRepo> {
    private final SQLHelper sqlHelper;

    // ============================== 文件夹 =================================>

    /**
     * 获取所有文件夹的树形数据
     */
    @GetMapping("/treeFolder")
    @Operation(summary = "获取所有文件夹的树形数据", description = "获取所有文件夹的树形数据")
    public List<SysFolderResp> treeFolder() {
        List<SysFolder> folders = sqlHelper.select().from(SysFolder.class).orderBy(SysFolder::getCreateTime, OrderType.ASC).submit(SysFolder.class);
        return Trees.toTree(folders.stream().map(SysFolder::toResp).toList());
    }

    /**
     * 根据PID获取文件夹
     */
    @GetMapping("/listFolderByPid")
    @Operation(summary = "根据PID获取文件夹", description = "根据PID获取文件夹")
    public List<SysFolderResp> listFolderByPid(@RequestParam String pid) {
        return logic.listFolderByPid(pid);
    }

    /**
     * 添加或修改文件夹
     */
    @PostMapping("/insertOrUpdateFolder")
    @Operation(summary = "添加或修改文件夹", description = "添加或修改文件夹")
    public SysFolderResp insertOrUpdateFolder(@RequestBody SysFolderReq data) {
        return logic.insertOrUpdateFolder(data);
    }

    /**
     * 删除文件夹
     */
    @DeleteMapping("/deleteFolder")
    @Operation(summary = "删除文件夹", description = "删除文件夹，只删除文件夹本身，并不会删除文件夹下面的子文件夹和文件")
    public R<String> deleteFolder(@RequestParam String id) {
        sqlHelper.delete().from(SysFolder.class).where(w -> w.eq(SysFolder::getId, id)).submit();
        return R.success("操作成功");
    }

    // ============================== 文件 =================================>

    /**
     * 分页查询文件
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询文件", description = "分页查询文件")
    public Page<SysFileResp> page(@ParameterObject SysFileReq params,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        SelectBuilder selectBuilder = query();
        Page<SysFile> page = repository.page(pageNo, pageSize, selectBuilder);
        return Pages.transPage(page, SysFile::toResp);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysFileResp queryById(@RequestParam String id) {
        return logic.queryById(id);
    }

    /**
     * 新增或编辑
     */
    @PostMapping("/insertOrUpdate")
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    public String insertOrUpdate(@RequestBody SysFileReq data) {
        logic.insertOrUpdate(data);
        return data.getId() == null ? "新增成功" : "编辑成功";
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id_in=1,2,3：表示删除id为1,2,3的数据")
    public String delete(String id) {
        logic.delete(id);
        return "删除成功";
    }

    /**
     * 上传文件
     */
    @PostMapping("upload")
    @Operation(summary = "上传文件", description = "上传文件")
    public SysFileResp upload(@RequestPart MultipartFile file, String folderId) {
        return logic.upload(file, folderId);
    }

    /**
     * 下载文件
     */
    @Operation(summary = "下载文件", description = "下载文件")
    @DownloadMapper(value = "download")
    public void download(String fileId) {
        logic.download(fileId);
    }

}
