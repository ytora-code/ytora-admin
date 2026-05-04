package xyz.ytora.base.mvc.result.anno;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.ytora.base.mvc.result.R;

import java.lang.annotation.*;

/**
 * 标注XLSX文件下载的接口
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
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
                responseCode = "200",
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
public @interface XlsxMapper {
    /**
     * 路径映射，对应 @GetMapping.value
     */
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};

    /**
     * 文件名称
     */
    String fileName() default "";

}
