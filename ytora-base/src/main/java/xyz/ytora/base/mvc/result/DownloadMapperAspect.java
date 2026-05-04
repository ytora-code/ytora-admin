package xyz.ytora.base.mvc.result;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.enums.Mimes;
import xyz.ytora.base.mvc.result.anno.DownloadMapper;
import xyz.ytora.base.util.HttpUtil;
import xyz.ytora.toolkit.io.Ios;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

/**
 * DownloadMapper 增强器
 *
 * <p>当控制器方法标注了 {@link DownloadMapper}，并且返回值为如下之一时，将返回数据视为文件：
 * <pre>{@code
 *  1.byte[]
 *  2.InputStream
 *  3.File
 * }</pre>
 * @author ytora
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class DownloadMapperAspect {

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();
    private static final ParserContext TEMPLATE_PARSER_CONTEXT = new ParserContext() {
        @Override
        public boolean isTemplate() {
            return true;
        }

        @Override
        @NonNull
        public String getExpressionPrefix() {
            return "#{";
        }

        @Override
        @NonNull
        public String getExpressionSuffix() {
            return "}";
        }
    };

    @Around("@annotation(downloadMapper)")
    public Object handleDownload(ProceedingJoinPoint joinPoint, DownloadMapper downloadMapper) throws Throwable {
        // 先执行业务方法，拿到真实返回值
        Object result = joinPoint.proceed();

        // 返回 null，直接交回 Spring 处理
        if (result == null) {
            return null;
        }

        // 仅处理 byte[],InputStream,Reader,File
        if (!(
                result instanceof byte[]
                        || result instanceof InputStream
                        || result instanceof Reader
                        || result instanceof File
        )) {
            return result;
        }

        // 媒体文件类型
        Mimes mime = downloadMapper.mime();
        // 文件名称(支持SpEL语法)
        String filename = resolveFilename(joinPoint, downloadMapper);

        // 输出文件响应
        HttpUtil.setDownload(filename, mime, response -> {
            try {
                // 清空响应体缓冲区
                if (!response.isCommitted()) {
                    response.resetBuffer();
                }
                try (ServletOutputStream outputStream = response.getOutputStream()) {
                    writeResponseBody(result, response, outputStream);
                    outputStream.flush();
                }
            } catch (IOException e) {
                throw new BaseException("响应文件失败", e);
            }
        });

        // 已经直接写入响应，阻止 Spring 再继续处理返回值
        return null;
    }

    private String resolveFilename(ProceedingJoinPoint joinPoint, DownloadMapper downloadMapper) {
        String filename = downloadMapper.filename();
        if (filename == null || filename.isBlank()) {
            return "file";
        }

        if (!(filename.contains("#") || filename.contains("#{"))) {
            return filename;
        }

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        StandardEvaluationContext context = new StandardEvaluationContext();

        String[] parameterNames = PARAMETER_NAME_DISCOVERER.getParameterNames(method);
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length && i < args.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }

        for (int i = 0; i < args.length; i++) {
            context.setVariable("p" + i, args[i]);
            context.setVariable("a" + i, args[i]);
        }

        try {
            if (filename.contains("#{")) {
                return SPEL_PARSER.parseExpression(filename, TEMPLATE_PARSER_CONTEXT).getValue(context, String.class);
            }
            return SPEL_PARSER.parseExpression(filename).getValue(context, String.class);
        } catch (Exception e) {
            throw new BaseException("下载文件名SpEL解析失败：{}", filename);
        }
    }

    private void writeResponseBody(Object result, HttpServletResponse response, ServletOutputStream outputStream) throws IOException {
        if (result instanceof byte[] byteResult) {
            response.setContentLengthLong(byteResult.length);
            outputStream.write(byteResult);
            return;
        }

        if (result instanceof InputStream inputStream) {
            try {
                Ios.copy(inputStream, outputStream);
            } finally {
                Ios.close(inputStream);
            }
            return;
        }

        if (result instanceof Reader reader) {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                reader.transferTo(writer);
                writer.flush();
            } finally {
                Ios.close(reader);
            }
            return;
        }

        if (result instanceof File file) {
            response.setContentLengthLong(file.length());
            try (FileChannel fc = FileChannel.open(file.toPath(), StandardOpenOption.READ)) {
                WritableByteChannel out = Channels.newChannel(outputStream);
                fc.transferTo(0, fc.size(), out);
            }
            return;
        }

        throw new BaseException("不支持的下载返回类型：{}", result.getClass().getName());
    }
}
