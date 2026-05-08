package xyz.ytora.core.online.dynamicapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.online.dynamicapi.logic.SysDynamicApiGroupLogic;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiGroupData;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApiGroup;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiGroupExcel;
import xyz.ytora.core.online.dynamicapi.model.param.SysDynamicApiGroupParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 动态API接口分组模块的API层
 *
 * @author 杨桐
 * @since 1.0
 */
@Tag(name = "动态API接口分组")
@RestController
@RequestMapping("/online/dynamic-api-group")
@RequiredArgsConstructor
public class SysDynamicApiGroupApi extends BaseApi<SysDynamicApiGroupLogic> {

    // ============================== CRUD =================================>

    /**
     * 树形查询动态API接口分组
     */
    @Operation(summary = "树形查询动态API接口分组", description = "树形查询动态API接口分组")
    @GetMapping("/tree")
    public List<SysDynamicApiGroupData> tree(@ParameterObject SysDynamicApiGroupParam param) {
        return logic.tree(param);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysDynamicApiGroupData> queryById(@RequestParam String id) {
        SysDynamicApiGroup entity = logic.queryById(id);
        if (entity == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(entity.toData());
    }

    /**
     * 新增或编辑
     */
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysDynamicApiGroupParam param) {
        return logic.upsert(param.toEntity());
    }

    /**
     * 根据ID删除
     */
    @Operation(summary = "根据ID删除", description = "根据ID删除")
    @DeleteMapping("/deleteByIds")
    public String deleteByIds(@RequestParam String ids) {
        int affectRows = logic.deleteByIds(ids);
        return affectRows > 0 ? "本次成功删除" + affectRows + "条数据" : "本次未删除任何数据";
    }

    // ============================== EXCEL导入导出 =================================>

    /**
     * 下载导入模板
     */
    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysDynamicApiGroupExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysDynamicApiGroupExcel> data) {
        List<SysDynamicApiGroup> list = data.stream().map(SysDynamicApiGroupExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysDynamicApiGroupExcel> exportToExcel(@ParameterObject SysDynamicApiGroupParam param) {
        List<SysDynamicApiGroup> list = logic.list(param);
        return list.stream()
                .map(SysDynamicApiGroup::toData)
                .map(SysDynamicApiGroupData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

}
