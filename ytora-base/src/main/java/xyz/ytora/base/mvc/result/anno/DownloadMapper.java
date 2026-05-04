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
import xyz.ytora.base.mvc.enums.Mimes;

import java.lang.annotation.*;

/**
 * 用来标注文件下载接口
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
     * 可选：文件名称，支持普通字符串，也支持 SpEL 表达式。
     *
     * <p>示例：</p>
     * <pre>{@code
     * @DownloadMapper(filename = "report.xlsx")
     * @DownloadMapper(filename = "#tableName + '.zip'")
     * @DownloadMapper(filename = "#p0 + '.txt'")
     * }</pre>
     */
    String filename() default "file";

}
