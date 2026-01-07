package xyz.ytora.core.sys.recyclebin.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/deleteCompletely")
    @Operation(summary = "彻底删除", description = "delete?id=1,2,3：表示删除id为1,2,3的数据")
    public R<String> deleteCompletely(String ids) {
        logic.deleteCompletely(ids);
        return R.success("删除成功");
    }

}
