package xyz.ytora.base.swagger;

import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.parameters.RequestBody;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import xyz.ytora.ytool.document.excel.Excel;

import java.util.List;

/**
 * 有一种接口：import(@Excel(fileName = "file") List<SysUserExcel> excelList)
 * 该接口本质是excel上传接口，但是由于swagger默认的机制，接口文档会将这个接口识别为普通的数据上传
 * 该配置类就是解决该问题，识别到控制器方法有形如 @Excel List<T> 的参数，就将该控制器方法在接口文档中定义为文件上传
 */
@Configuration
public class ExcelUploadOpenApiConfig {

    @Bean
    public GlobalOperationCustomizer excelUploadGlobalOperationCustomizer() {
        return (operation, handlerMethod) -> {
            // 1) 发现形如 @Excel List<T> 的参数
            boolean hasExcelParam = false;
            String fileField = "file";
            for (MethodParameter mp : handlerMethod.getMethodParameters()) {
                if (mp.hasParameterAnnotation(Excel.class)
                        && List.class.isAssignableFrom(mp.getParameterType())) {
                    hasExcelParam = true;
                    var anno = mp.getParameterAnnotation(Excel.class);
                    if (anno != null && anno.fileName() != null && !anno.fileName().isEmpty()) {
                        fileField = anno.fileName();
                    }
                    break;
                }
            }
            if (!hasExcelParam) return operation;

            // 2) 构建 multipart 对象 schema：只有一个 binary 的文件字段
            ObjectSchema root = new ObjectSchema();
            Schema<?> fileProp = new StringSchema()
                    .format("binary")
                    .description("Excel 文件（.xls / .xlsx）");
            // 已弃用的 addProperties 改为 addProperty
            root.addProperty(fileField, fileProp);
            root.addRequiredItem(fileField);

            MediaType mt = new MediaType().schema(root);

            // 3) 只保留 multipart/form-data（覆盖掉 springdoc 生成的 application/json）
            Content content = new Content()
                    .addMediaType(org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE, mt);

            RequestBody rb = new RequestBody()
                    .required(true)
                    .content(content);

            operation.setRequestBody(rb);

            // （可选）有些主题会把“参数”面板里残留的 JSON 参数也展示出来，直接清掉
            operation.setParameters(null);

            return operation;
        };
    }
}
