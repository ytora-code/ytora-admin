package xyz.ytora.base.download;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import xyz.ytora.base.RespUtil;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.scope.ScopedValueItem;
import xyz.ytora.ytool.document.excel.ExcelConfig;
import xyz.ytora.ytool.document.excel.Excels;
import xyz.ytora.ytool.io.Ios;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * created by yangtong on 2025/5/30 21:33:54
 * <br/>
 * 对DownloadMapper进行增强
 */
@Aspect
@Component
public class DownloadMapperAspect {

    private static final List<Class<?>> SUPPORTED = List.of(List.class, InputStream.class, File.class);

    @SuppressWarnings("unchecked")
    @Around("@annotation(downloadMapper)")
    public Object handleDownload(ProceedingJoinPoint joinPoint, DownloadMapper downloadMapper) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 判定是否增强，只增强DownloadMapper注解标准的方法
        if (method == null || !method.isAnnotationPresent(DownloadMapper.class)) {
            // 原样处理
            return joinPoint.proceed();
        }

        Class<?> returnType = method.getReturnType();
        Object result = joinPoint.proceed(); // 获取控制器方法的返回值

        // 是否满足增强条件
        boolean shouldHandle = false;
        if (returnType.isArray() && returnType.getComponentType().equals(byte.class)) {
            shouldHandle = true;
        } else if (SUPPORTED.stream().anyMatch(c -> c.isAssignableFrom(returnType))) {
            shouldHandle = true;
        }

        if (!shouldHandle) {
            return result; // 非支持类型，交给 Spring 自己处理
        }

        // 进入增强逻辑
        HttpServletResponse response = ScopedValueItem.RESPONSE.get();
        RespUtil.downloadResponse(downloadMapper.filename(), downloadMapper.mime());

        try (ServletOutputStream out = response.getOutputStream()) {
            switch (result) {
                case byte[] bytesResult ->
                    //如果接口返回数据是字节，则直接将字节写入响应流
                        out.write(bytesResult);
                case List listResult -> {
                    //如果接口返回数据是List，将List转为Excel文件流，再响应
                    Class<?> type = downloadMapper.type();
                    String userName = ScopedValueItem.LOGIN_USER.get().getUserName();
                    ExcelConfig<?> config = new ExcelConfig<>(type)
                            .to(out)
                            .setShowExpertInfo(downloadMapper.showExpertInfo())
                            .setExpertUser(userName)
                            .setCount(listResult.size());
                    Excels.write(listResult, config);
                }
                case InputStream isResult ->
                    //如果接口返回数据是流，直接写入
                        Ios.copyAndClose(isResult, out);
                case File fileResult -> {
                    //如果接口返回数据是File对象，则将File转为流，再写入
                    if (!fileResult.exists()) {
                        throw new BaseException("接口返回的文件不存在");
                    }
                    //获取文件长度
                    response.setContentLengthLong(fileResult.length());
                    //将文件写入响应
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileResult));
                    Ios.copyAndClose(bis, out);
                }
                default -> throw new IllegalArgumentException("不支持的返回类型：" + result.getClass());
            }
            out.flush();
        }

        // 阻止 Spring 再处理响应
        return null;
    }

}
