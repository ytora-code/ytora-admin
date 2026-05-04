package xyz.ytora.core.sys.config.api;

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
import xyz.ytora.core.sys.config.logic.SysConfigLogic;
import xyz.ytora.core.sys.config.model.data.SysConfigData;
import xyz.ytora.core.sys.config.model.entity.SysConfig;
import xyz.ytora.core.sys.config.model.excel.SysConfigExcel;
import xyz.ytora.core.sys.config.model.param.SysConfigParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * 系统配置模块的API层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "系统配置")
@RestController
@RequestMapping("/sys/config")
@RequiredArgsConstructor
public class SysConfigApi extends BaseApi<SysConfigLogic> {

    // ============================== CRUD =================================>

    /**
     * 分页查询数据
     */
    @Operation(summary = "分页查询系统配置", description = "分页查询系统配置")
    @GetMapping("/page")
    public Page<SysConfigData> page(@ParameterObject SysConfigParam param,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysConfig> page = logic.page(param);
        return page.trans(SysConfig::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysConfigData> queryById(@RequestParam String id) {
        SysConfig entity = logic.queryById(id);
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
    public String upsert(@RequestBody SysConfigParam param) {
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
    public List<SysConfigExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysConfigExcel> data) {
        List<SysConfig> list = data.stream().map(SysConfigExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysConfigExcel> exportToExcel(@ParameterObject SysConfigParam param) {
        List<SysConfig> list = logic.list(param);
        return list.stream()
                .map(SysConfig::toData)
                .map(SysConfigData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

}
