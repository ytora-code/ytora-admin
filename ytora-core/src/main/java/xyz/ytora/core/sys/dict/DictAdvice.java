package xyz.ytora.core.sys.dict;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.base.mvc.result.anno.NoWrap;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.core.sys.dict.logic.DictParser;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.reflect.classcache.ClassCache;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 字典增强，数据响应前，对字典进行翻译
 *
 * @author ytora
 * @since 1.0
 */
@ControllerAdvice
@Order(-10)
public class DictAdvice implements ResponseBodyAdvice<Object> {

    private final DictParser dictParser;

    public DictAdvice(@NonNull DictParser dictParser) {
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
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = returnType.getMethod();
        if (method == null || method.isAnnotationPresent(NoWrap.class)) {
            return false;
        }
        Class<?> methodReturnType = method.getReturnType();
        return method.isAnnotationPresent(Dict.class) &&
                (
                        R.class.isAssignableFrom(methodReturnType) ||
                                Page.class.isAssignableFrom(methodReturnType) ||
                                Collection.class.isAssignableFrom(methodReturnType)
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
    @SuppressWarnings("unchecked")
    public @Nullable Object beforeBodyWrite(@Nullable Object body,
                                            @NonNull MethodParameter returnType,
                                            @NonNull MediaType selectedContentType,
                                            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                            @NonNull ServerHttpRequest request,
                                            @NonNull ServerHttpResponse response) {
        if (body instanceof R<?> result) {
            return translateResult((R<Object>) result);
        } else if (body instanceof Page<?> page) {
            return translatePage(page);
        } else if (body instanceof Iterable<?> iterable) {
            return translateIterable(iterable);
        }
        return translateBean(body);
    }

    /**
     * 翻译 R 类型的返回值
     * @param result R 类型对象
     * @return 翻译结果
     */
    private R<Object> translateResult(R<Object> result) {
        Object data = result.getData();
        if (data == null || ClassCache.isPlatformType(data.getClass())) {
            return result;
        }

        if (data instanceof Page<?> page) {
            data = translatePage(page);
        } else if (data instanceof Iterable<?> iterable) {
            data = translateIterable(iterable);
        } else {
            data = translateBean(data);
        }
        result.setData(data);
        return result;
    }

    /**
     * 翻译 Page 类型的返回值
     * @param page page 类型对象
     * @return 翻译结果
     */
    private Page<Map<String, Object>> translatePage(Page<?> page) {
        return page.trans(this::translateBean);
    }

    private List<Map<String, Object>> translateIterable(Iterable<?> iterable) {
        return dictParser.translate(iterable);
    }

    private <T> Map<String, Object> translateBean(T bean) {
        return dictParser.translate(bean);
    }
}
