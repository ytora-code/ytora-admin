package xyz.ytora.core.sys.staticapi.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 系统静态API数据
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Schema(description = "系统静态API数据")
public class SysStaticApiData {

    /**
     * 接口名称
     */
    @Schema(description = "接口名称，优先取@Operation注解的summary字段")
    private String name;

    /**
     * 控制器类上的基础请求路径
     */
    @Schema(description = "基础请求路径，取对应控制器类上的@RequestMapping注解")
    private String baseUri;

    /**
     * 接口的最终请求路径
     */
    @Schema(description = "接口的最终请求路径，多个路径使用英文逗号分隔")
    private String uri;

    /**
     * 接口请求类型
     */
    @Schema(description = "接口请求类型，多个类型使用英文逗号分隔")
    private String type;

    /**
     * 所属模块的名称
     */
    @Schema(description = "所属模块的名称，优先取对应控制器类上的@Tag注解的name字段")
    private String baseName;

    /**
     * 控制器类全限定名
     */
    @Schema(description = "控制器类全限定名")
    private String controllerClassName;

    /**
     * 处理器方法签名
     */
    @Schema(description = "处理器方法签名")
    private String handlerMethod;

    /**
     * 接口可消费的媒体类型
     */
    @Schema(description = "接口可消费的媒体类型，多个类型使用英文逗号分隔")
    private String consumes;

    /**
     * 接口可生产的媒体类型
     */
    @Schema(description = "接口可生产的媒体类型，多个类型使用英文逗号分隔")
    private String produces;

    /**
     * 接口备注
     */
    @Schema(description = "接口备注，取@Operation注解的description字段")
    private String remark;

}
