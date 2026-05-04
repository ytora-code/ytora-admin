package xyz.ytora.core.sys.staticapi.logic;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import xyz.ytora.base.cache.CacheType;
import xyz.ytora.base.cache.Caches;
import xyz.ytora.base.util.Pages;
import xyz.ytora.core.sys.staticapi.model.data.SysStaticApiData;
import xyz.ytora.core.sys.staticapi.model.param.SysStaticApiParam;
import xyz.ytora.sqlux.orm.Page;
import xyz.ytora.toolkit.text.Strs;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * 系统静态API业务逻辑
 *
 * @author ytora
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class SysStaticApiLogic {

    private static final String PROJECT_PACKAGE_PREFIX = "xyz.ytora.";
    private static final String STATIC_API_CACHE_KEY = "SYS_STATIC_API::ALL";

    private final Caches caches;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 分页查询系统已注册的接口
     *
     * @param param 查询参数
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 分页结果
     */
    public Page<SysStaticApiData> page(SysStaticApiParam param, Integer pageNo, Integer pageSize) {
        List<SysStaticApiData> list = listAllFromCache()
                .stream()
                .filter(data -> matches(data, param))
                .toList();
        return Pages.toPage(pageNo, pageSize, list);
    }

    private List<SysStaticApiData> listAllFromCache() {
        return caches.get(CacheType.LOCAL, STATIC_API_CACHE_KEY, this::loadAllApis, -1);
    }

    private List<SysStaticApiData> loadAllApis() {
        return requestMappingHandlerMapping.getHandlerMethods()
                .entrySet()
                .stream()
                .filter(entry -> isProjectHandler(entry.getValue()))
                .map(entry -> toData(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(SysStaticApiData::getUri, Comparator.nullsLast(String::compareTo))
                        .thenComparing(SysStaticApiData::getType, Comparator.nullsLast(String::compareTo))
                        .thenComparing(SysStaticApiData::getControllerClassName, Comparator.nullsLast(String::compareTo))
                        .thenComparing(SysStaticApiData::getHandlerMethod, Comparator.nullsLast(String::compareTo)))
                .toList();
    }

    private boolean isProjectHandler(HandlerMethod handlerMethod) {
        return handlerMethod.getBeanType().getName().startsWith(PROJECT_PACKAGE_PREFIX);
    }

    private SysStaticApiData toData(RequestMappingInfo info, HandlerMethod handlerMethod) {
        Class<?> beanType = handlerMethod.getBeanType();
        Method method = handlerMethod.getMethod();

        Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);
        Tag tag = AnnotatedElementUtils.findMergedAnnotation(beanType, Tag.class);

        SysStaticApiData data = new SysStaticApiData();
        data.setName(operation != null && Strs.isNotEmpty(operation.summary()) ? operation.summary() : method.getName());
        data.setBaseUri(firstOrJoin(extractBaseUris(beanType)));
        data.setUri(join(extractUris(info)));
        data.setType(join(extractHttpMethods(info)));
        data.setBaseName(tag != null && Strs.isNotEmpty(tag.name()) ? tag.name() : beanType.getSimpleName());
        data.setControllerClassName(beanType.getName());
        data.setHandlerMethod(buildHandlerMethodSignature(method));
        data.setConsumes(join(info.getConsumesCondition().getConsumableMediaTypes().stream()
                .map(MediaType::toString)
                .toList()));
        data.setProduces(join(info.getProducesCondition().getProducibleMediaTypes().stream()
                .map(MediaType::toString)
                .toList()));
        data.setRemark(operation != null ? operation.description() : null);
        return data;
    }

    private Set<String> extractBaseUris(Class<?> beanType) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(beanType, RequestMapping.class);
        if (requestMapping == null) {
            return Set.of();
        }
        LinkedHashSet<String> uris = new LinkedHashSet<>();
        uris.addAll(Arrays.asList(requestMapping.path()));
        uris.addAll(Arrays.asList(requestMapping.value()));
        return uris.stream().filter(Strs::isNotEmpty).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private Set<String> extractUris(RequestMappingInfo info) {
        return new LinkedHashSet<>(info.getPatternValues());
    }

    private List<String> extractHttpMethods(RequestMappingInfo info) {
        List<String> methods = info.getMethodsCondition().getMethods().stream()
                .map(Enum::name)
                .sorted()
                .toList();
        if (!methods.isEmpty()) {
            return methods;
        }
        return List.of("ALL");
    }

    private String buildHandlerMethodSignature(Method method) {
        String params = Arrays.stream(method.getParameterTypes())
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
        return method.getDeclaringClass().getName() + "#" + method.getName() + "(" + params + ")";
    }

    private boolean matches(SysStaticApiData data, SysStaticApiParam param) {
        if (param == null) {
            return true;
        }
        return containsIgnoreCase(data.getName(), param.getName())
                && containsIgnoreCase(data.getUri(), param.getUri())
                && containsIgnoreCase(data.getType(), param.getType())
                && containsIgnoreCase(data.getBaseName(), param.getBaseName())
                && containsIgnoreCase(data.getControllerClassName(), param.getControllerClassName());
    }

    private boolean containsIgnoreCase(String source, String keyword) {
        if (Strs.isEmpty(keyword)) {
            return true;
        }
        if (Strs.isEmpty(source)) {
            return false;
        }
        return source.toLowerCase().contains(keyword.toLowerCase());
    }

    private String firstOrJoin(Set<String> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return join(values);
    }

    private String join(Iterable<String> values) {
        if (values == null) {
            return null;
        }
        String joined = join(StreamSupport.stream(values.spliterator(), false).toList());
        return Strs.isEmpty(joined) ? null : joined;
    }

    private String join(List<String> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        String joined = values.stream()
                .filter(Objects::nonNull)
                .filter(Strs::isNotEmpty)
                .distinct()
                .collect(Collectors.joining(", "));
        return Strs.isEmpty(joined) ? null : joined;
    }

}
