package xyz.ytora.core.online.dynamicapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.basemodel.BaseApi;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.core.online.dynamicapi.logic.SysDynamicApiLogic;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiData;
import xyz.ytora.core.online.dynamicapi.model.data.SysDynamicApiTestExecParam;
import xyz.ytora.core.online.dynamicapi.model.entity.SysDynamicApi;
import xyz.ytora.core.online.dynamicapi.model.excel.SysDynamicApiExcel;
import xyz.ytora.core.online.dynamicapi.model.param.SysDynamicApiParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.document.excel.Excel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 动态API接口执行控制器
 *
 * @author 杨桐
 * @since 1.0
 */
@Tag(name = "动态API接口执行控制器")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SysDynamicApiExecApi {

    private final SysDynamicApiLogic logic;

    /**
     * 测试调用动态接口
     * @param param 请求参数
     * @return 响应结果
     */
    @Operation(summary = "调用动态接口", description = "调用动态接口")
    @PostMapping("/test")
    public List<Map<String, Object>> test(@RequestBody SysDynamicApiTestExecParam param) {
        return logic.test(param);
    }

    /**
     * 调用动态接口，执行代码
     * @param request 请求对象
     * @return 响应结果
     */
    @Operation(summary = "调用动态接口", description = "调用动态接口")
    @RequestMapping("/**")
    public List<Map<String, Object>> api(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        // 去掉前缀，得到URI
        String apiPrefix = contextPath + "/api";
        String path = requestUri.substring(apiPrefix.length());

        // 得到请求参数
        Map<String, Object> param = HttpUtil.getReqParam();
        return logic.api(path, param);
    }

}
