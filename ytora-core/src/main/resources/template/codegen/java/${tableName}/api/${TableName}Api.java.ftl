<#assign packageBase = path>
<#if module?? && module?has_content>
    <#assign packageBase = packageBase + "." + module>
</#if>
<#assign businessName = TableComment!"业务数据">
<#if businessName?ends_with("表")>
    <#assign businessName = businessName?substring(0, businessName?length - 1)>
</#if>
package ${packageBase}.api;

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
import ${packageBase}.logic.${TableName}Logic;
import ${packageBase}.model.data.${TableName}Data;
import ${packageBase}.model.entity.${TableName};
import ${packageBase}.model.excel.${TableName}Excel;
import ${packageBase}.model.param.${TableName}Param;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

import static xyz.ytora.sqlux.core.SQL.*;

/**
 * ${businessName}模块的API层
 *
 * @author ${currentUser!"ytora"}
 * @since ${currentVersion!"1.0"}
 */
@Tag(name = "${businessName}")
@RestController
@RequestMapping("/${table_name?replace("_", "/")}")
@RequiredArgsConstructor
public class ${TableName}Api extends BaseApi<${TableName}Logic> {

    // ============================== CRUD =================================>

    /**
     * 分页查询数据
     */
    @Operation(summary = "分页查询${businessName}", description = "分页查询${businessName}")
    @GetMapping("/page")
    public Page<${TableName}Data> page(@ParameterObject ${TableName}Param param,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<${TableName}> page = logic.page(param);
        return page.trans(${TableName}::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<${TableName}Data> queryById(@RequestParam String id) {
        ${TableName} entity = logic.queryById(id);
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
    public String upsert(@RequestBody ${TableName}Param param) {
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
    public List<${TableName}Excel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<${TableName}Excel> data) {
        List<${TableName}> list = data.stream().map(${TableName}Excel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<${TableName}Excel> exportToExcel(@ParameterObject ${TableName}Param param) {
        List<${TableName}> list = logic.list(param);
        return list.stream()
                .map(${TableName}::toData)
                .map(${TableName}Data::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

}
