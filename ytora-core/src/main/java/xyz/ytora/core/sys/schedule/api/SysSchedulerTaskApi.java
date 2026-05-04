package xyz.ytora.core.sys.schedule.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.sys.schedule.logic.SysSchedulerTaskLogic;
import xyz.ytora.core.sys.schedule.model.data.SysSchedulerTaskData;
import xyz.ytora.core.sys.schedule.model.entity.SysSchedulerTask;
import xyz.ytora.core.sys.schedule.model.excel.SysSchedulerTaskExcel;
import xyz.ytora.core.sys.schedule.model.param.SysSchedulerTaskParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 调度任务模块的API层
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "调度任务")
@RestController
@RequestMapping("/sys/scheduler-task")
@RequiredArgsConstructor
public class SysSchedulerTaskApi extends BaseApi<SysSchedulerTaskLogic> {

    /**
     * 分页查询数据
     */
    @Operation(summary = "分页查询调度任务", description = "分页查询调度任务")
    @GetMapping("/page")
    public Page<SysSchedulerTaskData> page(@ParameterObject SysSchedulerTaskParam param,
                                           @RequestParam(defaultValue = "1") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysSchedulerTask> page = logic.page(param);
        return page.trans(SysSchedulerTask::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysSchedulerTaskData> queryById(@RequestParam String id) {
        SysSchedulerTask entity = logic.queryById(id);
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
    public String upsert(@RequestBody SysSchedulerTaskParam param) {
        param.setStatus((short) 1);
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

    /**
     * 下载导入模板
     */
    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysSchedulerTaskExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    /**
     * 导入数据
     */
    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysSchedulerTaskExcel> data) {
        List<SysSchedulerTask> list = data.stream().map(SysSchedulerTaskExcel::toEntity).toList();
        logic.upsertBatch(list);
        return "导入成功";
    }

    /**
     * 导出数据
     */
    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysSchedulerTaskExcel> exportToExcel(@ParameterObject SysSchedulerTaskParam param) {
        List<SysSchedulerTask> list = logic.list(param);
        return list.stream()
                .map(SysSchedulerTask::toData)
                .map(SysSchedulerTaskData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

    /**
     * 启动定时任务
     */
    @Operation(summary = "启动定时任务", description = "启动定时任务")
    @GetMapping("/start")
    public String start(@RequestParam String id) {
        logic.start(id);
        return "启动成功";
    }

    /**
     * 停止定时任务
     */
    @Operation(summary = "停止定时任务", description = "停止定时任务")
    @GetMapping("/stop")
    public String stop(@RequestParam String id) {
        logic.stop(id);
        return "停止成功";
    }

    /**
     * 立刻执行一次定时任务
     */
    @Operation(summary = "立刻执行一次定时任务", description = "立刻执行一次定时任务")
    @GetMapping("/runOnce")
    public String runOnce(@RequestParam String id) {
        logic.runOnce(id);
        return "执行成功";
    }

}
