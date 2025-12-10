package xyz.ytora.base.dict;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyz.ytora.base.model.R;
import xyz.ytora.sql4j.orm.Page;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * springmvc消息转换器在将控制器的返回值写入响应体之前，有一处增强
 * 在这处增强，可以对最终写入响应体的数据做修改
 * 只能对basePackages指定包下类的返回值生效
 */
@ControllerAdvice
@Order(-10)
public class DictAdvice implements ResponseBodyAdvice<Object> {

    private final IDictParser dictParser;

    public DictAdvice(@Nullable IDictParser dictParser) {
        this.dictParser = dictParser;
    }

    /**
     * 判断是否应该对控制器方法的返回值进行增强
     * 如果接口返回类型不是R，也不是void，就转换为R再返回
     *
     * @param returnType    封装后的返回值
     * @param converterType 消息转换器
     * @return 是否增强
     */
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        Method method = returnType.getMethod();
        if (method == null || method.isAnnotationPresent(NoWrap.class)) {
            return false;
        }
        Class<?> methodReturnType = method.getReturnType();
        return method.isAnnotationPresent(Dict.class) &&
                (
                        R.class.isAssignableFrom(methodReturnType) ||
                                Page.class.isAssignableFrom(methodReturnType) ||
                                List.class.isAssignableFrom(methodReturnType)
                );
    }

    /**
     * 对最终写入响应体的返回值进行修改
     *
     * @param body                  控制器方法的返回值
     * @param returnType            封装后的返回值
     * @param selectedContentType   选择的内容类型
     * @param selectedConverterType 选择的转换器类型
     * @param request               请求对象
     * @param response              响应对象
     * @return 返回值就是最终写入响应体的数据
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        //如果是R类型
        if (body instanceof R<?> rBody) {
            Object data = rBody.getData();
            if (data instanceof Page pageBody) {
                List<Object> records = pageBody.getRecords();
                List<Map<String, Object>> dictAfterRecords = dictParser.translate(records);
                pageBody.setRecords(dictAfterRecords);
            }
            return rBody;
        }
        //如果直接就是分页类型
        else if (body instanceof Page pageBody) {
            List<Object> records = pageBody.getRecords();
            List<Map<String, Object>> dictAfterRecords = dictParser.translate(records);
            pageBody.setRecords(dictAfterRecords);
            return pageBody;
        }
        //如果是List类型
        else if (body instanceof List listBody) {
            return dictParser.translate(listBody);
        }
        //如果都不是，就原样写入响应
        return body;
    }
}
