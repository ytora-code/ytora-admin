package xyz.ytora.base.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.enums.Mimes;
import xyz.ytora.base.mvc.result.R;
import xyz.ytora.toolkit.text.Strs;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * HTTP 工具类
 * <p>提供了跟 HTTP 请求、响应有关的方法</p>
 *
 * @author ytora
 * @since 1.0
 */
public final class HttpUtil {

    private static final List<String> IP_HEADER = List.of(
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_REAL_IP",
            "X-Real-IP"
    );

    /**
     * 可以预览的响应文件类型
     */
    private static final Set<String> PREVIEWABLE_MIME_TYPES = Set.of(
            "application/pdf",
            "text/plain",
            "text/html",
            "application/xml",
            "text/xml",
            "application/json",
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/webp",
            "image/bmp",
            "image/svg+xml",
            "video/mp4",
            "video/webm",
            "audio/mpeg",
            "audio/ogg",
            "audio/wav"
    );

    /**
     * 获取当前的请求对象
     */
    public static HttpServletRequest getReq() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        return requestAttributes.getRequest();
    }

    /**
     * 获取当前的响应对象
     */
    public static HttpServletResponse getResp() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        return requestAttributes.getResponse();
    }

    /**
     * 设置响应文件时的响应头数据
     *
     * <p>根据文件类型自动设置响应头，支持中文文件名、文件大小、前端跨域读取响应头等。</p>
     *
     * @param filename 文件名称，建议带扩展名，例如 report.xlsx
     * @param mimes    文件 MIME 类型，为 null 时默认 application/octet-stream
     * @param consumer 对响应数据的自定义处理
     */
    public static void setDownload(String filename, Mimes mimes, Consumer<HttpServletResponse> consumer) {
        if (filename == null || filename.isBlank()) {
            throw new BaseException("文件名称不能为空");
        }

        HttpServletResponse response = getResp();
        if (response == null) {
            throw new BaseException("无法获取响应对象");
        }

        Mimes actualMime = (mimes == null) ? Mimes.APPLICATION_OCTET_STREAM : mimes;

        // 编码后的 UTF-8 文件名，供 filename* 使用
        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replace("+", "%20");

        // 判断是否适合浏览器内联预览
        String dispositionType = PREVIEWABLE_MIME_TYPES.contains(actualMime.value())
                ? "inline"
                : "attachment";

        // 标准的 Content-Disposition
        String contentDisposition = dispositionType + "; filename=\"" + encodedFilename + "\"";

        // ===== 基础响应头 =====
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(actualMime.value());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);

        // ===== 缓存控制 =====
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0);

        // ===== 允许前端读取这些响应头 =====
        response.setHeader("Access-Control-Expose-Headers",
                String.join(", ",
                        HttpHeaders.CONTENT_DISPOSITION,
                        HttpHeaders.CONTENT_LENGTH,
                        HttpHeaders.CONTENT_TYPE));

        if (consumer != null) {
            consumer.accept(response);
        }
    }

    public static void response(HttpServletResponse res, Integer code, String msg) {
        try {
            R<?> r = code == 0 ? R.success() : R.error();
            r.setMessage(msg).setCode(code);
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(HttpStatus.OK.value());
            res.getWriter().printf(r.toString());
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    /**
     * 获取HTTP请求发起者的IP
     * @return 请求的IP
     */
    public static String getIp() {
        HttpServletRequest request = getReq();
        if (request == null) {
            throw new IllegalStateException("当前非web环境，无法获取IP");
        }
        String ip = null;
        for (String name : IP_HEADER) {
            String value = request.getHeader(name);
            if (!Strs.isEmpty(value) && !"unknown".equalsIgnoreCase(value)) {
                ip = value;
                break;
            }
        }

        if (Strs.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多级代理取第一个真实 IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        // 本机回环地址处理
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return ip;
    }

}