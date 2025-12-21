package xyz.ytora.base.download;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.ytora.base.mvc.R;

import java.lang.annotation.*;

/**
 * 用来标注文件下载接口
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.GET)
@ApiResponses({
        @ApiResponse(
                responseCode = "0",
                description = "文件下载成功",
                content = @Content(
                        mediaType = "application/octet-stream",
                        schema = @Schema(type = "string", format = "binary")
                )
        ),
        @ApiResponse(
                responseCode = "405",
                description = "下载失败，返回 JSON 错误信息",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = R.class)
                )
        )
})
public @interface DownloadMapper {
    /**
     * 路径映射，对应 @GetMapping.value
     */
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};

    /**
     * 可选：支持自定义 produces（覆盖默认值）
     */
    Mimes mime() default Mimes.APPLICATION_OCTET_STREAM;

    /**
     * 可选：文件名称
     */
    String filename() default "file";

    /**
     * 可选：如果接口返回值是List，那么必须要指定该属性
     */
    Class<?> type() default Object.class;

    /**
     * 是否在返回的文件中显示当前下载人
     */
    boolean showExpertInfo() default true;
}
