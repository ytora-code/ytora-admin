package xyz.ytora.core.monitor.online.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.core.monitor.online.logic.OnlineUserLogic;
import xyz.ytora.core.monitor.online.model.data.OnlineUserData;
import xyz.ytora.core.monitor.online.model.param.OnlineUserParam;
import xyz.ytora.core.sys.log.AutoLog;
import xyz.ytora.sqlux.orm.Page;

/**
 * 在线用户
 *
 * @author ytora
 * @since 1.0
 */
@Tag(name = "在线用户")
@RestController
@RequestMapping("/monitor/online-user")
@RequiredArgsConstructor
public class OnlineUserApi {

    private final OnlineUserLogic logic;

    /**
     * 分页查询在线用户
     */
    @Operation(summary = "分页查询在线用户", description = "分页查询在线用户")
    @GetMapping("/page")
    public Page<OnlineUserData> page(@ParameterObject OnlineUserParam param,
                                     @RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.page(param, pageNo, pageSize);
    }

    /**
     * 踢出在线用户
     */
    @AutoLog("踢出在线用户")
    @Operation(summary = "踢出在线用户", description = "根据token踢出在线用户，多个token使用英文逗号分隔")
    @DeleteMapping("/kickByTokens")
    public String kickByTokens(@RequestParam String tokens) {
        int affectRows = logic.kickByTokens(tokens);
        return affectRows > 0 ? "本次成功踢出" + affectRows + "个在线用户" : "本次未踢出任何在线用户";
    }

}
