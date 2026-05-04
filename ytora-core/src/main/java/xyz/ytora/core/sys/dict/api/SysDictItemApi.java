package xyz.ytora.core.sys.dict.api;

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
import xyz.ytora.core.sys.dict.logic.SysDictItemLogic;
import xyz.ytora.core.sys.dict.model.data.SysDictItemData;
import xyz.ytora.core.sys.dict.model.entity.SysDict;
import xyz.ytora.core.sys.dict.model.entity.SysDictItem;
import xyz.ytora.core.sys.dict.model.excel.SysDictItemExcel;
import xyz.ytora.core.sys.dict.model.param.SysDictItemParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 字典项模块的API层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "字典项")
@RestController
@RequestMapping("/sys/dict/item")
@RequiredArgsConstructor
public class SysDictItemApi extends BaseApi<SysDictItemLogic> {

    @Operation(summary = "分页查询字典项", description = "分页查询字典项")
    @GetMapping("/page")
    public Page<SysDictItemData> page(@ParameterObject SysDictItemParam param,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysDictItem> page = logic.page(param);
        return page.trans(SysDictItem::toData);
    }

    @GetMapping("/listDictItem")
    @Operation(summary = "查询字典项", description = "根据字典code，查询所属的全部字典项")
    public List<SysDictItemData> listDictItem(@RequestParam String dictCode) {
        return logic.listDictItem(dictCode);
    }

    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysDictItemData> queryById(@RequestParam String id) {
        SysDictItem entity = logic.queryById(id);
        if (entity == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(entity.toData());
    }

    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysDictItemParam param) {
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
    public List<SysDictItemExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysDictItemExcel> data) {
        List<SysDictItem> list = data.stream().map(SysDictItemExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysDictItemExcel> exportToExcel(@ParameterObject SysDictItemParam param) {
        List<SysDictItem> list = logic.list(param);
        return list.stream()
                .map(SysDictItem::toData)
                .map(SysDictItemData::toExcel)
                .toList();
    }

}
