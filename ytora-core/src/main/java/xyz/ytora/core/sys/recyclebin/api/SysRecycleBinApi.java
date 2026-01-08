package xyz.ytora.core.sys.recyclebin.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.mvc.R;
import xyz.ytora.core.sys.recyclebin.logic.SysRecycleBinLogic;
import xyz.ytora.sql4j.orm.Page;

import java.util.Map;

/**
 * 回收站 控制器
 */
@Tag(name = "回收站")
@RestController
@RequestMapping("/sys/recycleBin")
@RequiredArgsConstructor
public class SysRecycleBinApi {

    private final SysRecycleBinLogic logic;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询", description = "分页查询指定数据库表的删除数据")
    public Page<Map<String, Object>> page(@RequestParam String originalTable,
                                          @RequestParam(defaultValue = "1") Integer pageNo,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.page(originalTable, pageNo, pageSize);
    }

    /**
     * 还原数据
     */
    @GetMapping("/restore")
    @Operation(summary = "还原数据", description = "还原数据")
    public R<String> restore(String ids) {
        logic.restore(ids);
        return R.success("还原成功");
    }

    /**
     * 彻底删除
     */
    @DeleteMapping("/deleteCompletely")
    @Operation(summary = "彻底删除", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> deleteCompletely(@RequestParam String ids) {
        logic.deleteCompletely(ids);
        return R.success("删除成功");
    }

    /**
     * 清空
     */
    @DeleteMapping("/clear")
    @Operation(summary = "清空回收站数据", description = "清空指定表的回收站数据")
    public R<String> clear(@RequestParam String table) {
        logic.clear(table);
        return R.success("操作成功");
    }

}
