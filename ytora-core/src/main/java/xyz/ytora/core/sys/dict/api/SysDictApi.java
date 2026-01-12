package xyz.ytora.core.sys.dict.api;

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
import xyz.ytora.core.sys.dict.logic.SysDictLogic;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.model.excel.SysDictExcel;
import xyz.ytora.core.sys.dict.model.req.SysDictReq;
import xyz.ytora.core.sys.dict.model.resp.SysDictItemResp;
import xyz.ytora.core.sys.dict.model.resp.SysDictResp;
import xyz.ytora.core.sys.dict.repo.SysDictRepo;
import xyz.ytora.sql4j.func.support.Raw;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.SelectBuilder;
import xyz.ytora.ytool.bean.Beans;
import xyz.ytora.ytool.document.excel.Excel;
import xyz.ytora.ytool.str.Strs;

import java.util.Collections;
import java.util.List;

/**
 * 字典 控制器
 */
@Tag(name = "字典")
@RestController
@RequestMapping("/sys/dict")
@RequiredArgsConstructor
public class SysDictApi extends BaseApi<SysDict, SysDictLogic, SysDictRepo> {

    // ============================== CRUD =================================>

    /**
     * 分页查询字典
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询字典", description = "分页查询字典")
    public Page<SysDictResp> page(@ParameterObject SysDictReq param,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        SelectBuilder selectBuilder = query();
        if (param.getType() == null) {
            selectBuilder.addWhere(w -> w.eq(SysDict::getType, 1));
        }
        Page<SysDict> page = repository.page(pageNo, pageSize, selectBuilder);
        return Pages.transPage(page, SysDict::toResp);
    }

    /**
     * 查询字典项
     */
    @GetMapping("/listDictItem")
    @Operation(summary = "查询字典项", description = "查询字典项")
    public List<SysDictItemResp> listDictItem(@RequestParam String dictCode) {
        List<SysDict> list = repository.list(w -> w.eq(SysDict::getDictCode, dictCode).eq(SysDict::getType, 2));
        return Beans.transBean(list, SysDictItemResp.class);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysDictResp queryById(@RequestParam String id) {
        return (SysDictResp) super.queryById(id);
    }

    /**
     * 新增或编辑
     */
    @PostMapping("/insertOrUpdate")
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    public R<String> insertOrUpdate(@RequestBody SysDictReq data) {
        if (data.getId() == null) {
            if (Strs.isEmpty(data.getPid())) {
                data.setPid("0");
            }
            repository.insert(data.toEntity());
        } else {
            data.setPid(null);
            data.setType(null);
            repository.update(data.toEntity(), w -> w.eq(Raw.of("id"), data.getId()));
        }
        return R.success(data.getId() == null ? "新增成功" : "编辑成功");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> delete(String ids) {
        super.delete();
        return R.success("删除成功");
    }

    // ============================== 导入/导出 EXCEL =================================>

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @DownloadMapper(value = "template", filename = "导入模板.xlsx", type = SysDictExcel.class, mime = Mimes.APPLICATION_XLSX, showExpertInfo = false)
    public List<SysDictExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @PostMapping("import")
    @Operation(summary = "导入EXCEL", description = "导入EXCEL")
    @Transactional(rollbackFor = Exception.class)
    public R<String> importFromExcel(@Excel(fileName = "file") List<SysDictExcel> data) {
        // 将 EXCEL 数据转为实体类数据
        List<SysDict> list = data.stream().map(SysDictExcel::toEntity).toList();
        // 入库
        repository.insert(list);
        return R.success("导入成功");
    }

    @Operation(summary = "导出EXCEL", description = "导出EXCEL")
    @DownloadMapper(value = "export", filename = "用户信息.xlsx", type = SysDictExcel.class, mime = Mimes.APPLICATION_XLSX)
    public List<SysDictExcel> importFromExcel(@ParameterObject SysDictReq param) {
        Page<SysDictResp> page = page(param, 1, Integer.MAX_VALUE);
        return page.getRecords().stream().map(SysDictResp::toExcel).toList();
    }

}
