package xyz.ytora.core.rbac.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.download.DownloadMapper;
import xyz.ytora.base.download.Mimes;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.user.logic.SysUserLogic;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.core.rbac.user.model.req.SysUserReq;
import xyz.ytora.core.rbac.user.model.resp.SysUserResp;
import xyz.ytora.core.rbac.user.repo.SysUserRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 用户 控制器
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/rbac/user")
@RequiredArgsConstructor
public class SysUserApi extends BaseApi<SysUser, SysUserLogic, SysUserRepo> {

    // ============================== CRUD =================================>

    /**
     * 分页查询用户
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户", description = "分页查询用户")
    public Page<SysUserResp> page(@ParameterObject SysUserReq param,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        SelectBuilder selectBuilder = query();
        Page<SysUser> page = repository.page(pageNo, pageSize, selectBuilder);
        return Pages.transPage(page, SysUser::toResp);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysUserResp queryById(@RequestParam String id) {
        SysUser entity = repository.one(w -> w.eq(SysUserReq::getId, id));
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
    public R<String> insertOrUpdate(@RequestBody SysUserReq data) {
        if (data.getId() == null) {
            repository.insert(data.toEntity());
            return R.success("新增成功");
        } else {
            repository.update(data.toEntity(), w -> w.eq(SysUserReq::getId, data.getId()));
            return R.success("编辑成功");
        }
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> delete(String id) {
        SelectBuilder selectBuilder = query();
        repository.delete(selectBuilder.getWhereStage().getWhere());
        return R.success("删除成功");
    }

    // ============================== EXCEL =================================>

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @DownloadMapper(value = "downloadTemplate", filename = "导入模板.xlsx", type = SysUserExcel.class, mime = Mimes.APPLICATION_XLSX, showExpertInfo = false)
    public List<SysUserExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @PostMapping("import")
    @Operation(summary = "导入", description = "导入")
    @Transactional(rollbackFor = Exception.class)
    public R<String> importFromExcel(@Excel(fileName = "file") List<SysUserExcel> data) {
        List<SysUser> list = data.stream().map(SysUserExcel::toEntity).toList();
        // 设置初始密码
        for (SysUser sysUser : list) {
            sysUser.setPassword("1");
        }
        repository.insert(list);
        return R.success("导入成功");
    }

    @Operation(summary = "导出", description = "导出")
    @DownloadMapper(value = "export", filename = "用户信息.xlsx", type = SysUserExcel.class, mime = Mimes.APPLICATION_XLSX)
    public List<SysUserExcel> importFromExcel(@ParameterObject SysUserReq param) {
        Page<SysUserResp> page = page(param, 1, Integer.MAX_VALUE);
        return page.getRecords().stream().map(SysUserResp::toExcel).toList();
    }

}