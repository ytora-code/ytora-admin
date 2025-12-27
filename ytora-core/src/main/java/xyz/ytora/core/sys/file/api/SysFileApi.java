package xyz.ytora.core.sys.file.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.ytora.base.download.DownloadMapper;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.querygen.WhereGenerator;
import xyz.ytora.core.sys.file.logic.SysFileLogic;
import xyz.ytora.core.sys.file.model.entity.SysFile;
import xyz.ytora.core.sys.file.model.req.SysFileReq;
import xyz.ytora.core.sys.file.model.resp.SysFileResp;
import xyz.ytora.core.sys.file.resp.SysFileRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.ConditionExpressionBuilder;
import xyz.ytora.ytool.str.Strs;

/**
 * created by YT on 2025/12/28 00:53:46
 * <br/>
 */
@RestController
@RequestMapping("/sys/file")
@Tag(name = "文件")
public class SysFileApi extends BaseApi<SysFile, SysFileLogic, SysFileRepo> {

    /**
     * 分页查询文件
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询文件", description = "分页查询文件")
    public Page<SysFileResp> page(@ParameterObject SysFileReq params,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        ConditionExpressionBuilder where = WhereGenerator.where();
        Page<SysFile> page = repository.page(pageNo, pageSize, where);
        return Pages.transPage(page, SysFile::toResp);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysFileResp queryById(@RequestParam String id) {
        SysFile entity = repository.one(w -> w.eq(SysFileReq::getId, id));
        if (entity == null) {
            return null;
        }
        return entity.toResp();
    }

    /**
     * 新增或编辑
     */
    @PostMapping("/insertOrUpdate")
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    public String insertOrUpdate(@RequestBody SysFileReq sysFileReq) {
        if (sysFileReq.getId() == null) {
            repository.insert(sysFileReq.toEntity());
            return "新增成功";
        } else {
            repository.update(sysFileReq.toEntity(), w -> w.eq(SysFileReq::getId, sysFileReq.getId()));
            return "编辑成功";
        }
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
    public String upload(@RequestPart MultipartFile file) {
        return logic.upload(file);
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
