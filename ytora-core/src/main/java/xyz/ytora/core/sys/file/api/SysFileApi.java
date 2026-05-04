package xyz.ytora.core.sys.file.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.anno.DownloadMapper;
import xyz.ytora.core.sys.file.logic.SysFileLogic;
import xyz.ytora.core.sys.file.model.data.SysFileData;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.param.SysFileParam;
import xyz.ytora.sqlux.orm.Page;

/**
 * created by YT on 2025/12/28 00:53:46
 * <br/>
 * 文件
 */
@Slf4j
@RestController
@RequestMapping("/sys/file")
@Tag(name = "文件")
@RequiredArgsConstructor
public class SysFileApi extends BaseApi<SysFileLogic> {

    /**
     * 分页查询文件
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询文件", description = "分页查询文件")
    public Page<SysFileData> page(@ParameterObject SysFileParam param,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysFile> page = logic.page(param);
        return page.trans(SysFile::toData);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysFileData queryById(@RequestParam String id) {
        SysFile sysFile = logic.queryById(id);
        if (sysFile == null) {
            throw new BaseException("文件数据不存在");
        }
        return sysFile.toData();
    }

    /**
     * 新增或编辑
     */
    @PostMapping("/insertOrUpdate")
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    public String insertOrUpdate(@RequestBody SysFileParam param) {
        logic.insertOrUpdate(param);
        return param.getId() == null ? "新增成功" : "编辑成功";
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id_in=1,2,3：表示删除id为1,2,3的数据")
    public String deleteFileById(String id) {
        logic.deleteFileById(id);
        return "删除成功";
    }

    /**
     * 上传文件
     */
    @PostMapping("upload")
    @Operation(summary = "上传文件", description = "上传文件")
    public SysFileData upload(@RequestPart MultipartFile file, String folderId) {
        return logic.upload(file, folderId);
    }

    /**
     * 下载文件
     */
    @Operation(summary = "下载文件", description = "下载文件")
    @DownloadMapper(value = "download")
    public void download(@RequestParam String id) {
        logic.download(id);
    }

}
