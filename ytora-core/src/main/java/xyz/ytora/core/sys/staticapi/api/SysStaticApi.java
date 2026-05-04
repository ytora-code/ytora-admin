package xyz.ytora.core.sys.staticapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ytora.core.sys.staticapi.logic.SysStaticApiLogic;
import xyz.ytora.core.sys.staticapi.model.data.SysStaticApiData;
import xyz.ytora.core.sys.staticapi.model.param.SysStaticApiParam;
import xyz.ytora.sqlux.orm.Page;

/**
 * 系统接口
 *
 * <p>可以查询本系统里面定义的所有API接口</p>
 *
 * @author 杨桐
 * @since 1.0-SNAPSHOT
 */
@Tag(name = "系统接口")
@RestController
@RequestMapping("/sys/static-api")
@RequiredArgsConstructor
public class SysStaticApi {

    private final SysStaticApiLogic logic;

    /**
     * 分页查询系统的API接口
     */
    @Operation(summary = "分页查询系统的API接口", description = "分页查询系统的API接口")
    @GetMapping("/page")
    public Page<SysStaticApiData> page(@ParameterObject SysStaticApiParam param,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        return logic.page(param, pageNo, pageSize);
    }

}
