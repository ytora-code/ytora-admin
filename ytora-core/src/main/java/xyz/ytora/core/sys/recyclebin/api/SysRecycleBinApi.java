package xyz.ytora.core.sys.recyclebin.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.BaseApi;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.sys.recyclebin.logic.SysRecycleBinLogic;
import xyz.ytora.core.sys.recyclebin.model.entity.SysRecycleBin;
import xyz.ytora.core.sys.recyclebin.repo.SysRecycleBinRepo;
import xyz.ytora.sql4j.orm.Page;
import xyz.ytora.ytool.json.JSON;

/**
 * 回收站 控制器
 */
@Tag(name = "回收站")
@RestController
@RequestMapping("/sys/recycleBin")
@RequiredArgsConstructor
public class SysRecycleBinApi extends BaseApi<SysRecycleBin, SysRecycleBinLogic, SysRecycleBinRepo> {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询指定数据库表的删除数据")
    public Page<JSON> page(@RequestParam String originalTable,
                           @RequestParam(defaultValue = "1") Integer pageNo,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.page(originalTable, pageNo, pageSize);
    }

    /**
     * 彻底删除
     */
    @DeleteMapping("/delete")
    @Operation(summary = "彻底删除", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> delete(String ids) {
        super.delete();
        return R.success("删除成功");
    }

}
