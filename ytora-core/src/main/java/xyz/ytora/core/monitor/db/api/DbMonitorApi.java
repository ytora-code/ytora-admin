package xyz.ytora.core.monitor.db.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.monitor.db.logic.DbLogic;
import xyz.ytora.core.monitor.db.model.data.DbDynamicData;
import xyz.ytora.core.monitor.db.model.data.DbStaticData;

/**
 * created by YT on 2026/4/24
 * <br/>
 * 数据库动态监控接口。
 */
@Slf4j
@Tag(name = "数据库-动态")
@RestController
@RequestMapping("/monitor/db")
@RequiredArgsConstructor
public class DbMonitorApi {

    private final DbLogic dbLogic;

    /**
     * 数据库动态监控总览。
     */
    @GetMapping("/overview")
    @Operation(summary = "数据库动态监控", description = "数据库动态监控")
    public DbDynamicData getOverview() {
        return dbLogic.getDynamicData();
    }

    /**
     * 获取数据源信息。
     */
    @GetMapping("/dataSources")
    @Operation(summary = "获取数据源信息", description = "获取数据源信息")
    public DbStaticData getDataSources() {
        return dbLogic.getDataSources();
    }

}
