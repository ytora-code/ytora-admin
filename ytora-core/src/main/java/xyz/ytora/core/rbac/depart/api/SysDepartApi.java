package xyz.ytora.core.rbac.depart.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.rbac.depart.logic.SysDepartLogic;
import xyz.ytora.core.rbac.depart.model.data.SysDepartData;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.entity.SysUserDepart;
import xyz.ytora.core.rbac.depart.model.excel.SysDepartExcel;
import xyz.ytora.core.rbac.depart.model.param.SysDepartParam;
import xyz.ytora.core.rbac.depart.model.param.SysUserDepartParam;
import xyz.ytora.core.rbac.permission.model.data.SysPermissionData;
import xyz.ytora.core.rbac.user.model.data.SysUserData;
import xyz.ytora.core.sys.dict.Dict;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;

/**
 * 部门模块的API层
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "部门")
@RestController
@RequestMapping("/rbac/depart")
@RequiredArgsConstructor
public class SysDepartApi extends BaseApi<SysDepartLogic> {

    // ============================== CRUD =================================>

    /**
     * 查询整颗部门树
     */
    @GetMapping("/tree")
    @Operation(summary = "查询整颗部门树", description = "查询整颗部门树")
    public List<SysDepartData> tree(String departName) {
        return logic.tree(departName);
    }

    /**
     * 根据PID查询部门
     */
    @GetMapping("/listByPid")
    @Operation(summary = "根据PID查询部门", description = "根据PID查询部门，顶级部门PID为0")
    public List<SysDepartData> listByPid(@RequestParam String pid) {
        return logic.listByPid(pid);
    }

    /**
     * 根据ID查询部门
     */
    @Operation(summary = "根据ID查询部门", description = "根据ID查询部门")
    @Dict
    @GetMapping("/queryById")
    public R<SysDepartData> queryById(@RequestParam String id) {
        SysDepart depart = logic.queryById(id);
        if (depart == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(depart.toData());
    }

    /**
     * 新增或编辑
     */
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysDepartParam param) {
        String id = param.getId();
        logic.insertOrUpdate(param);
        return id == null ? "新增成功" : "编辑成功";
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

    // ============================== EXCEL =================================>

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysDepartExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysDepartExcel> data) {
        List<SysDepart> list = data.stream().map(SysDepartExcel::toEntity).toList();
        // 批量导入数据
        logic.upsertBatch(list);
        return "导入成功";
    }

    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysDepartExcel> exportToExcel(@ParameterObject SysDepartParam param) {
        List<SysDepart> list = logic.list(param);
        return list.stream()
                .map(SysDepart::toData)
                .map(SysDepartData::toExcel)
                .toList();
    }

    // ============================== 其他 =================================>

    /**
     * 分页查询部门下的用户
     */
    @Dict
    @Operation(summary = "分页查询部门下的用户", description = "根据部门ID，分页查询下面的用户")
    @GetMapping("/pageUserByDepartId")
    public Page<SysUserData> pageUserByDepartId(@RequestParam String id) {
        return logic.pageUserByDepartId(id);
    }

    /**
     * 分页查询部门下没有的用户
     */
    @Operation(summary = "分页查询部门下没有的用户", description = "根据部门ID，分页查询部门下没有的用户")
    @GetMapping("/pageNonUserByDepartId")
    public Page<SysUserData> pageNonUserByDepartId(@RequestParam String id) {
        return logic.pageNonUserByDepartId(id);
    }

    /**
     * 更新用户-部门关系
     */
    @Operation(summary = "更新用户-部门关系", description = "更新用户-部门关系")
    @PostMapping("/refreshUserDepartMapper")
    public String refreshUserDepartMapper(@RequestBody SysUserDepartParam param) {
        logic.refreshUserDepartMapper(param);
        return "更新成功";
    }

    /**
     * 查询指定用户拥有的部门
     */
    @Operation(summary = "查询指定用户拥有的部门", description = "查询指定用户拥有的部门")
    @GetMapping("/listDepartByUserId")
    public List<SysDepartData> listDepartByUserId(@RequestParam String userId) {
        return logic.listDepartByUserId(userId);
    }

}
