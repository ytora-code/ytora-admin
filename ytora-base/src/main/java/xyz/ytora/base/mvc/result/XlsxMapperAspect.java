package xyz.ytora.base.mvc.result;

import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.enums.Mimes;
import xyz.ytora.base.mvc.result.anno.XlsxMapper;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.toolkit.document.excel.Excel;
import xyz.ytora.toolkit.document.excel.Excels;
import xyz.ytora.toolkit.text.Strs;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * XlsxMapper 增强器
 *
 * <p>当控制器方法标注了 {@link XlsxMapper}，并且返回值为 {@code List<Bean>} 时，
 * 自动将返回数据导出为 XLSX 文件并直接写入 HTTP 响应。</p>
 *
 * <p>当前仅支持直接返回具体泛型类型的列表，例如：</p>
 * <pre>
 * {@code
 * public List<UserVO> list() { ... }
 * }
 * </pre>
 *
 * <p>以下场景暂不支持自动泛型解析，将跳过增强并原样返回：</p>
 * <pre>
 * {@code
 * public Object list() { ... }
 * public List<?> list() { ... }
 * public List<? extends UserVO> list() { ... }
 * public Result<List<UserVO>> list() { ... }
 * }
 * </pre>
 *
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class XlsxMapperAspect {

    private static final String DEFAULT_FILE_NAME = "XLSX文件";
    private static final String FILE_SUFFIX = ".xlsx";

    @Around("@annotation(xlsxMapper)")
    public Object handleDownload(ProceedingJoinPoint joinPoint, XlsxMapper xlsxMapper) throws Throwable {
        // 先执行业务方法，拿到真实返回值
        Object result = joinPoint.proceed();

        // 返回 null，直接交回 Spring 处理
        if (result == null) {
            return null;
        }

        // 仅处理 List 返回值
        if (!(result instanceof List<?> list)) {
            return result;
        }

        Method method = getTargetMethod(joinPoint);

        // 解析 List 中元素的真实类型
        Class<?> elementType = resolveListElementType(method);
        if (elementType == null) {
            log.warn("方法 {} 的返回值不是明确的 List<Bean> 类型，跳过 Excel 导出",
                    method.toGenericString());
            return result;
        }

        // 生成 Excel 数据
        byte[] data = Excels.writeBeans(list, elementType);

        // 解析文件名
        String fileName = resolveFileName(xlsxMapper, elementType);

        // 输出文件响应
        HttpUtil.setDownload(fileName, Mimes.APPLICATION_XLSX, response -> {
            try {
                // 清空响应体缓冲区
                if (!response.isCommitted()) {
                    response.resetBuffer();
                }

                // ===== 数据长度 =====
                response.setContentLengthLong(data.length);

                // ===== 写出文件 =====
                try (ServletOutputStream outputStream = response.getOutputStream()) {
                    outputStream.write(data);
                    outputStream.flush();
                }
            } catch (IOException e) {
                throw new BaseException("响应文件失败", e);
            }
        });

        // 已经直接写入响应，阻止 Spring 再继续处理返回值
        return null;
    }

    /**
     * 获取当前切点对应的方法对象。
     */
    private Method getTargetMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    /**
     * 解析控制器方法返回值中 List 的元素类型。
     *
     * <p>仅支持直接声明为 {@code List<具体类>} 的场景。</p>
     *
     * @param method 控制器方法
     * @return List 的元素类型，解析失败时返回 null
     */
    private Class<?> resolveListElementType(Method method) {
        Type genericReturnType = method.getGenericReturnType();

        if (!(genericReturnType instanceof ParameterizedType parameterizedType)) {
            return null;
        }

        Type rawType = parameterizedType.getRawType();
        if (!(rawType instanceof Class<?> rawClass) || !List.class.isAssignableFrom(rawClass)) {
            return null;
        }

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length == 0) {
            return null;
        }

        Type elementType = actualTypeArguments[0];
        if (elementType instanceof Class<?> clazz) {
            return clazz;
        }

        return null;
    }

    /**
     * 解析导出文件名称。
     *
     * <p>优先级：</p>
     * <ol>
     *     <li>{@link XlsxMapper#fileName()}</li>
     *     <li>实体类上的 {@link Excel} 注解值</li>
     *     <li>默认文件名</li>
     * </ol>
     *
     * @param xlsxMapper 注解
     * @param elementType 列表元素类型
     * @return 最终文件名（一定带 .xlsx 后缀）
     */
    private String resolveFileName(XlsxMapper xlsxMapper, Class<?> elementType) {
        String fileName = null;

        if (Strs.isNotEmpty(xlsxMapper.fileName())) {
            fileName = xlsxMapper.fileName();
        } else {
            Excel excel = elementType.getAnnotation(Excel.class);
            if (excel != null && Strs.isNotEmpty(excel.value())) {
                fileName = excel.value();
            }
        }

        if (Strs.isEmpty(fileName)) {
            fileName = DEFAULT_FILE_NAME;
        }

        return ensureXlsxSuffix(fileName);
    }

    /**
     * 确保文件名以 .xlsx 结尾。
     */
    private String ensureXlsxSuffix(String fileName) {
        if (Strs.isEmpty(fileName)) {
            return DEFAULT_FILE_NAME + FILE_SUFFIX;
        }

        if (fileName.endsWith(FILE_SUFFIX)) {
            return fileName;
        }

        return fileName + FILE_SUFFIX;
    }

}
