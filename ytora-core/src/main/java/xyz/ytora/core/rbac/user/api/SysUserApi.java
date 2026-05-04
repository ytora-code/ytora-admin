package xyz.ytora.core.rbac.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.core.rbac.datascope.DataScope;
import xyz.ytora.core.rbac.user.logic.SysUserLogic;
import xyz.ytora.core.rbac.user.model.data.SysUserData;
import xyz.ytora.core.rbac.user.model.entity.SysUser;
import xyz.ytora.core.rbac.user.model.excel.SysUserExcel;
import xyz.ytora.core.rbac.user.model.param.SysUserParam;
import xyz.ytora.core.sys.dict.Dict;
import xyz.ytora.core.sys.log.AutoLog;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;
import xyz.ytora.toolkit.text.Strs;

import java.util.Collections;
import java.util.List;

/**
 * 业务模块的API层
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/rbac/user")
@RequiredArgsConstructor
public class SysUserApi extends BaseApi<SysUserLogic> {

    // ============================== CRUD =================================>

    /**
     * 分页查询用户
     */
    @Dict
    @Operation(summary = "分页查询用户", description = "分页查询用户")
    @GetMapping("/page")
    public Page<SysUserData> page(@ParameterObject SysUserParam param,
                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysUser> page = logic.page(param);
        return page.trans(SysUser::toData);
    }

    /**
     * 根据ID查询
     */
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    @GetMapping("/queryById")
    public R<SysUserData> queryById(@RequestParam String id) {
        SysUser user = logic.queryById(id);
        if (user == null) {
            throw new BaseException("id为[" + id + "]的数据不存在");
        }
        return R.success(user.toData());
    }

    /**
     * 新增或编辑
     */
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    @PostMapping("/upsert")
    public String upsert(@RequestBody SysUserParam param) {
        if (Strs.isEmpty(param.getId())) {
            param.setPassword("123");
        }
        return logic.upsert(param.toEntity());
    }

    /**
     * 根据ID删除
     */
    @Operation(summary = "根据ID删除", description = "根据ID删除")
    @DeleteMapping("/deleteByIds")
    public String deleteByIds(@RequestParam String ids) {
        int affectRows = logic.deleteByIds(ids);
        return affectRows > 0 ? "成功删除" + affectRows + "条数据" : "本次未删除任何数据";
    }

    // ============================== EXCEL =================================>

    @Operation(summary = "下载导入模板", description = "下载导入模板")
    @XlsxMapper(value = "downloadTemplate", fileName = "导入模板.xlsx")
    public List<SysUserExcel> downloadTemplate() {
        return Collections.emptyList();
    }

    @Operation(summary = "导入数据", description = "导入数据")
    @PostMapping("import")
    public String importFromExcel(@Excel("file") List<SysUserExcel> data) {
        List<SysUser> list = data.stream().map(SysUserExcel::toEntity).toList();
        // 设置初始密码
        for (SysUser sysUser : list) {
            sysUser.setPassword("1");
        }
        // 批量导入数据
        logic.upsertBatch(list);
        return "导入成功";
    }

    @Operation(summary = "导出数据", description = "导出数据")
    @XlsxMapper(value = "export")
    public List<SysUserExcel> exportToExcel(@ParameterObject SysUserParam param) {
        List<SysUser> list = logic.list(param);
        return list.stream()
                .map(SysUser::toData)
                .map(SysUserData::toExcel)
                .toList();
    }

}
