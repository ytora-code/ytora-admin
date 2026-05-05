package xyz.ytora.core.sys.icon.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.sys.icon.logic.SysIconLogic;
import xyz.ytora.core.sys.icon.model.data.SysIconData;
import xyz.ytora.core.sys.icon.model.entity.SysIcon;
import xyz.ytora.core.sys.icon.model.excel.SysIconExcel;
import xyz.ytora.core.sys.icon.model.param.SysIconParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.select;

/**
 * 系统图标库模块的API层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "系统图标库")
@RestController
@RequestMapping("/sys/icon")
@RequiredArgsConstructor
public class SysIconApi extends BaseApi<SysIconLogic> {

    @Operation(summary = "全量查询系统图标库", description = "全量查询系统图标库")
    @GetMapping("/list")
    public List<SysIconData> list(@ParameterObject SysIconParam param) {
        List<SysIcon> list = select()
                .from(SysIcon.class)
                .orderByAsc(SysIcon::getCode)
                .submit(SysIcon.class);

        return list.stream()
                .map(SysIcon::toData).toList();
    }

    @Operation(summary = "分页查询系统图标库", description = "分页查询系统图标库")
    @GetMapping("/page")
    public Page<SysIconData> pageByKey(String key,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.pageByKey(key, pageNo, pageSize);
    }

    /**
     * 根据CODE查询
     */
    @Operation(summary = "根据CODE查询", description = "根据CODE查询")
    @GetMapping("/queryByCode")
    public SysIconData queryByCode(@RequestParam String code) {
        return logic.queryByCode(code);
    }

    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysIconParam param) {
        return logic.upsert(param.toEntity());
    }

    @Operation(summary = "根据ID删除", description = "根据ID删除")
    @DeleteMapping("/deleteByIds")
    public String deleteByIds(@RequestParam String ids) {
        int affectRows = logic.deleteByIds(ids);
        return affectRows > 0 ? "本次成功删除" + affectRows + "条数据" : "本次未删除任何数据";
    }

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysIconExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysIconExcel> data) {
        List<SysIcon> list = data.stream().map(SysIconExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysIconExcel> exportToExcel(@ParameterObject SysIconParam param) {
        List<SysIcon> list = logic.list(param);
        return list.stream()
                .map(SysIcon::toData)
                .map(SysIconData::toExcel)
                .toList();
    }

}
