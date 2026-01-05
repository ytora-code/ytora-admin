package xyz.ytora.core.rbac.depart.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.rbac.depart.logic.SysDepartLogic;
import xyz.ytora.core.rbac.depart.model.entity.SysDepart;
import xyz.ytora.core.rbac.depart.model.req.SysDepartReq;
import xyz.ytora.core.rbac.depart.model.resp.SysDepartResp;
import xyz.ytora.core.rbac.depart.repo.SysDepartRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.sql4j.orm.Pages;
import xyz.ytora.sql4j.sql.select.SelectBuilder;

import java.util.List;

/**
 * 部门 控制器
 */
@Tag(name = "部门")
@RestController
@RequestMapping("/rbac/depart")
@RequiredArgsConstructor
public class SysDepartApi extends BaseApi<SysDepart, SysDepartLogic, SysDepartRepo> {

    /**
     * 分页查询部门
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询部门", description = "分页查询部门")
    public Page<SysDepartResp> page(@ParameterObject SysDepartReq userdata,
                                    @RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        SelectBuilder selectBuilder = query();
        Page<SysDepart> page = repository.page(pageNo, pageSize, selectBuilder);
        return Pages.transPage(page, SysDepart::toResp);
    }

    /**
     * 查询部门树
     */
    @GetMapping("/tree")
    @Operation(summary = "查询部门树", description = "查询部门树")
    public List<SysDepartResp> tree(String departName) {
        return logic.tree(departName);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/queryById")
    @Operation(summary = "根据ID查询", description = "根据ID查询")
    public SysDepartResp queryById(@RequestParam String id) {
        SysDepart entity = repository.one(w -> w.eq(SysDepartReq::getId, id));
        if (entity == null) {
            return null;
        }
        return entity.toResp();
    }

    /**
     * 新增或编辑
     */
    @PostMapping("/insertOrUpdate")
    @Operation(summary = "新增或编辑", description = "新增或编辑")
    public R<String> insertOrUpdate(@RequestBody SysDepartReq sysDepartReq) {
        String id = sysDepartReq.getId();
        logic.insertOrUpdate(sysDepartReq);
        return R.success(id == null ? "新增成功" : "编辑成功");
    }

    /**
     * 删除数据
     */
    @DeleteMapping("/delete")
    @Operation(summary = "删除数据", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> delete(String id) {
        SelectBuilder selectBuilder = query();
        repository.delete(selectBuilder.getWhereStage().getWhere());
        return R.success("删除成功");
    }

}
