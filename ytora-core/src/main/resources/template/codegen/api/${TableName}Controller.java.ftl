package ${path}.${module}.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.ytor.common.model.R;
import org.ytor.common.anno.Dict;
import org.ytor.common.anno.Excel;
import org.ytor.common.util.Strs;
import org.ytor.common.anno.AutoLog;
import org.ytor.common.enums.MimeType;
import org.ytor.common.util.bean.Beans;
import org.ytor.common.exception.BaseException;
import org.ytor.core.util.Pages;
import org.ytor.core.BaseController;
import org.ytor.core.DownloadMapper;
import org.ytor.core.querygen.WhereGenerator;
import org.ytor.core.sqlflow.Page;
import org.ytor.core.sqlflow.builder.support.WhereBuilder;
import org.ytor.core.sqlflow.executor.DMLResult;
import ${path}.${module}.logic.${TableName}Logic;
import ${path}.${module}.model.entity.${TableName};
import ${path}.${module}.repository.${TableName}Repository;
import ${path}.${module}.model.req.${TableName}Req;
import ${path}.${module}.model.resp.${TableName}Resp;
import ${path}.${module}.model.excel.${TableName}Excel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
 * created by ${currentUser} on ${.now}
 * <br/>
 * ${TableComment} API
 */
@RestController
@Tag(name = "${TableComment}")
@RequestMapping("/${module}/${tableName}")
@RequiredArgsConstructor
public class ${TableName}Controller extends BaseController<${TableName}, ${TableName}Logic, ${TableName}Repository> {

    /**
     * 分页查询
     */
    @Dict
    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询")
    public R<Page<${TableName}Resp>> page(@ParameterObject ${TableName}Req reqParam,
                                          @RequestParam(defaultValue = "1") Long pageNo,
                                          @RequestParam(defaultValue = "10") Long pageSize) {
        // 若后续支持从 Req 生成条件，可替换为 WhereGenerator.where(${tableName}Data)
        WhereBuilder where = WhereGenerator.where();
        Page<${TableName}> page = repository.queryPage(where);
        return R.success(Pages.transPage(page, ${TableName}::toResp));
    }

    /**
     * 根据id查询数据
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据id查询", description = "根据id查询数据")
    public R<${TableName}Resp> queryById(String id) {
        ${TableName} ${tableName} = repository.queryById(id);
        ${TableName}Resp resp = null;
        if (${tableName} != null) {
            resp = ${tableName}.toResp();
        }
        return R.success(resp);
    }

    /**
     * 新增或编辑数据
     */
    @AutoLog("新增或编辑数据")
    @PostMapping("/addOrUpdate")
    @Operation(summary = "新增或编辑", description = "根据有无主键id,新增或编辑数据")
    public R<String> addOrUpdate(@RequestBody ${TableName}Req ${tableName}Data) {
        ${TableName} ${tableName} = Beans.copyProperties(${tableName}Data, ${TableName}.class);
        repository.insertOrUpdate(${tableName});
        return R.success(${tableName}Data.getId() == null ? "新增成功" : "编辑成功");
    }

    /**
     * 删除
     */
    @AutoLog("根据id删除数据")
    @DeleteMapping("/delete/{ids}")
    @Operation(summary = "删除", description = "根据id删除数据")
    public R<String> delete(@PathVariable("ids") String ids) {
        if (Strs.isEmpty(ids)) {
            throw new BaseException("ids不能为空");
        }
        List<String> idList = Arrays.asList(ids.split(","));
        repository.deleteById(idList);
        return R.success("删除成功");
    }

    /**
     * 下载导入模板
     */
    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @DownloadMapper(value = "downloadTemplate", filename = "用户信息导入模板.xlsx", type = ${TableName}Excel.class, mime = MimeType.APPLICATION_XLSX, showExpertInfo = false)
    public List<${TableName}Excel> downloadTemplate() {
        return Collections.<${TableName}Excel>emptyList();
    }

    /**
     * 导入数据
     */
    @PostMapping("import")
    @Operation(summary = "导入", description = "导入")
    @Transactional(rollbackFor = Exception.class)
    public String importFromExcel(@Excel(fileName = "file") List<${TableName}Excel> excelList) {
        List<${TableName}> list = excelList.stream().map(${TableName}Excel::toEntity).toList();
        List<DMLResult> dmlResults = repository.insertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出", description = "导出")
    @DownloadMapper(value = "export", filename = "用户信息.xlsx", type = ${TableName}Excel.class, mime = MimeType.APPLICATION_XLSX)
    public List<${TableName}Excel> importFromExcel(@ParameterObject ${TableName}Req reqParam) {
        R<Page<${TableName}Resp>> page = page(reqParam, 1L, 9999L);
        return page.getData().getRecords().stream().map(${TableName}Resp::toExcel).toList();
    }
}
