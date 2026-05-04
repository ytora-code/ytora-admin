package xyz.ytora.core.sys.staticapi.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统静态API模块的查询参数
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Schema(description = "系统静态API模块的查询参数")
public class SysStaticApiParam {

    /**
     * 接口名称
     */
    @Schema(description = "接口名称，模糊匹配")
    private String name;

    /**
     * 最终请求路径
     */
    @Schema(description = "接口请求路径，模糊匹配")
    private String uri;

    /**
     * 接口请求类型
     */
    @Schema(description = "接口请求类型，模糊匹配")
    private String type;

    /**
     * 所属模块名称
     */
    @Schema(description = "所属模块的名称，模糊匹配")
    private String baseName;

    /**
     * 控制器类名
     */
    @Schema(description = "控制器类全限定名，模糊匹配")
    private String controllerClassName;

}
