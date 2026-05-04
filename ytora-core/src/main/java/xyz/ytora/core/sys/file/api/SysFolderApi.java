package xyz.ytora.core.sys.file.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.core.sys.file.logic.SysFolderLogic;
import xyz.ytora.core.sys.file.model.data.SysFolderData;
import xyz.ytora.core.sys.file.model.entity.SysFolder;
import xyz.ytora.core.sys.file.model.param.SysFolderParam;
import xyz.ytora.toolkit.tree.Trees;

import java.util.List;

import static xyz.ytora.sqlux.core.SQL.delete;
import static xyz.ytora.sqlux.core.SQL.select;

/**
 * created by YT on 2025/12/28 00:53:46
 * <br/>
 * 文件夹
 */
@RestController
@RequestMapping("/sys/folder")
@Tag(name = "文件夹")
@RequiredArgsConstructor
public class SysFolderApi extends BaseApi<SysFolderLogic> {

    /**
     * 获取所有文件夹的树形数据
     */
    @GetMapping("/treeFolder")
    @Operation(summary = "获取所有文件夹的树形数据", description = "获取所有文件夹的树形数据")
    public List<SysFolderData> treeFolder() {
        List<SysFolder> folders = select().from(SysFolder.class).orderByAsc(SysFolder::getCreateTime).submit(SysFolder.class);
        return Trees.toTree(folders.stream().map(SysFolder::toData).toList());
    }

    /**
     * 根据PID获取文件夹
     */
    @GetMapping("/listFolderByPid")
    @Operation(summary = "根据PID获取文件夹", description = "根据PID获取文件夹")
    public List<SysFolderData> listFolderByPid(@RequestParam String pid) {
        return logic.listFolderByPid(pid);
    }

    /**
     * 添加或修改文件夹
     */
    @PostMapping("/insertOrUpdateFolder")
    @Operation(summary = "添加或修改文件夹", description = "添加或修改文件夹")
    public SysFolderData insertOrUpdateFolder(@RequestBody SysFolderParam data) {
        return logic.insertOrUpdateFolder(data);
    }

    /**
     * 删除文件夹
     */
    @DeleteMapping("/deleteFolder")
    @Operation(summary = "删除文件夹", description = "删除文件夹，只删除文件夹本身，并不会删除文件夹下面的子文件夹和文件")
    public R<String> deleteFolder(@RequestParam String id) {
        delete().from(SysFolder.class).where(w -> w.eq(SysFolder::getId, id)).submit();
        return R.success("操作成功");
    }
}
