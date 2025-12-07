package xyz.ytora.base.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.ytool.document.excel.Excel;
import xyz.ytora.ytool.document.excel.ExcelConfig;
import xyz.ytora.ytool.document.excel.Excels;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 参数解析器，解析文件上传的参数
 */
public class ExcelBodyArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Set<String> EXCEL_MIME_WHITELIST = Set.of(
            "application/vnd.ms-excel",                                   // .xls
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xlsx
            "application/octet-stream" // 有些浏览器/客户端会发这个，后面做魔数再判
    );

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 参数上有 @Exce 且 参数类型是 List<?>
        if (!parameter.hasParameterAnnotation(Excel.class)) {
            return false;
        }

        Class<?> paramType = parameter.getParameterType();
        if (!List.class.isAssignableFrom(paramType)) {
            return false;
        }

        // 必须能拿到 List 的泛型 T
        Type genericType = parameter.getGenericParameterType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        Type[] actuals = ((ParameterizedType) genericType).getActualTypeArguments();
        return actuals.length == 1 && actuals[0] instanceof Class<?>;
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if (servletRequest == null) {
            throw new ServletRequestBindingException("No HttpServletRequest");
        }

        // 1) 判断是否 multipart/form-data
        String contentType = servletRequest.getContentType();
        if (contentType == null || !contentType.toLowerCase(Locale.ROOT).startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            throw new ServletRequestBindingException("Content-Type must be multipart/form-data");
        }

        if (!(servletRequest instanceof MultipartHttpServletRequest multipartRequest)) {
            throw new ServletRequestBindingException("Request is not MultipartHttpServletRequest");
        }

        Excel excelAnno = parameter.getParameterAnnotation(Excel.class);
        if (excelAnno == null) {
            throw new IllegalArgumentException("必须标注Excel注解");
        }
        String fileName = excelAnno.fileName();

        // 2) 获取MultipartFile对象
        MultipartFile file = resolveFile(multipartRequest, fileName);
        if (file == null || file.isEmpty()) {
            return Collections.emptyList();
        }

        // 3) 基于 MIME 是否为 Excel
        String clientMime = Optional.ofNullable(file.getContentType()).orElse("");
        if (!EXCEL_MIME_WHITELIST.contains(clientMime)) {
            throw new BaseException("未知的文件类型：" + clientMime);
        }

        // 4) 确定控制器方法参数 List<T> 的 T
        Class<?> elementType = (Class<?>) ((ParameterizedType) parameter.getGenericParameterType())
                .getActualTypeArguments()[0];

        // 5) 将文件流转为POJO
        try (InputStream input = file.getInputStream()) {
            ExcelConfig<?> config = new ExcelConfig<>(elementType)
                    .setHeaderRowIndex(0)
                    .setShowExpertInfo(false)
                    .setStart(excelAnno.startRow(), excelAnno.startCol());
            return Excels.read(input, config);
        } catch (Exception ex) {
            throw new ServletRequestBindingException("解析Excel失败：" + ex.getMessage(), ex);
        }
    }


    private MultipartFile resolveFile(MultipartHttpServletRequest req, String fieldName) {
        if (StringUtils.hasText(fieldName)) {
            return req.getFile(fieldName);
        }
        // 未指定字段名时，取第一个文件
        Map<String, MultipartFile> map = req.getFileMap();
        if (map.isEmpty()) return null;
        return map.values().iterator().next();
    }
}
