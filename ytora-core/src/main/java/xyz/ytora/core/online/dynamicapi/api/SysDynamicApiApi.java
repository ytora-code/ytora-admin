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
import xyz.ytora.core.online.dynamicapi.logic.SysDynamicApiLogic;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiData;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiExcel;
import xyz.ytora.core.online.dynamicapi.model.param.SysDynamicApiParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 动态API接口模块的API层
 *
 * @author 杨桐
 * @since 1.0
 */
@Tag(name = "动态API接口")
@RestController
@RequestMapping("/online/dynamic-api")
@RequiredArgsConstructor
public class SysDynamicApiApi extends BaseApi<SysDynamicApiLogic> {

    // ============================== CRUD =================================>

    /**
     * 分页查询数据
     */
    @Operation(summary = "分页查询动态API接口", description = "分页查询动态API接口")
    @GetMapping("/page")
    public Page<SysDynamicApiData> page(@ParameterObject SysDynamicApiParam param,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi> page = logic.page(param);
        return page.trans(xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysDynamicApiData> queryById(@RequestParam String id) {
        xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi entity = logic.queryById(id);
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
    public String upsert(@RequestBody SysDynamicApiParam param) {
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
    public List<SysDynamicApiExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysDynamicApiExcel> data) {
        List<xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi> list = data.stream().map(SysDynamicApiExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysDynamicApiExcel> exportToExcel(@ParameterObject SysDynamicApiParam param) {
        List<xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi> list = logic.list(param);
        return list.stream()
                .map(xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi::toData)
                .map(SysDynamicApiData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

    /**
     * 接口上线
     */
    @Operation(summary = "接口上线", description = "接口上线")
    @GetMapping("/publish")
    public String publish(@RequestParam String id) {
        logic.publish(id);
        return "接口上线";
    }

    /**
     * 接口下线
     */
    @Operation(summary = "接口下线", description = "接口下线")
    @GetMapping("/offline")
    public String offline(@RequestParam String id) {
        logic.offline(id);
        return "接口下线";
    }


}
