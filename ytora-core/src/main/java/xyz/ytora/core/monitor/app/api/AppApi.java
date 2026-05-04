package xyz.ytora.core.monitor.app.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.monitor.app.logic.AppLogic;
import xyz.ytora.core.monitor.app.model.data.*;
import xyz.ytora.core.monitor.sse.model.data.AppSseMetricsData;

/**
 * created by YT on 2026/4/24
 * <br/>
 * 应用服务监控接口。
 */
@Slf4j
@Tag(name = "应用")
@RestController
@RequestMapping("/monitor/app")
@RequiredArgsConstructor
public class AppApi {

    private final AppLogic appLogic;

    // ======================== 静态数据 ========================>

    /**
     * 应用基础信息。
     */
    @GetMapping("/basicInfo")
    @Operation(summary = "应用基础信息", description = "应用基础信息")
    public AppBasicInfoData getBasicInfo() {
        return appLogic.getBasicInfo();
    }

    // ======================== 动态数据 ========================>

    /**
     * 应用总览。
     * <p>
     * 把最常用的动态摘要聚合在一起，
     */
    @GetMapping("/overview")
    @Operation(summary = "应用总览", description = "应用总览")
    public AppOverviewData getOverview() {
        return appLogic.getOverview();
    }

    /**
     * Spring Boot 状态。
     */
    @GetMapping("/status")
    @Operation(summary = "SpringBoot状态", description = "SpringBoot状态")
    public AppStatusData getStatus() {
        return appLogic.getStatus();
    }

    /**
     * 请求并发与慢请求统计。
     */
    @GetMapping("/requests")
    @Operation(summary = "请求监控", description = "请求监控")
    public AppRequestMetricsData getRequestMetrics() {
        return appLogic.getRequestMetrics();
    }

    /**
     * 最近错误。
     */
    @GetMapping("/errors")
    @Operation(summary = "最近错误", description = "最近错误")
    public AppErrorMetricsData getErrorMetrics() {
        return appLogic.getErrorMetrics();
    }

    /**
     * 业务埋点指标。
     */
    @GetMapping("/businessMetrics")
    @Operation(summary = "业务埋点指标", description = "业务埋点指标")
    public AppBusinessMetricsData getBusinessMetrics() {
        return appLogic.getBusinessMetrics();
    }
}
