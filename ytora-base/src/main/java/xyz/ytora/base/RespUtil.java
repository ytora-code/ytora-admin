package xyz.ytora.base;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.ytora.base.download.Mimes;
import xyz.ytora.base.exception.BaseException;
import xyz.ytora.base.mvc.R;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * created by yangtong on 2025/4/4 下午7:09
 * <br/>
 * 响应工具类
 */
public class RespUtil {

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
     * 获取当前的响应对象
     */
    public static HttpServletResponse getResp() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new xyz.ytora.base.exception.BaseException("无法获取响应对象，请确认当前是否为web环境！");
        }

        return requestAttributes.getResponse();
    }

    /**
     * 设置当前请求为文件下载类型的请求
     * @param filename 文件名称
     * @param mimes 文件类型（可为 null）
     */
    public static void downloadResponse(String filename, Mimes mimes) {
        HttpServletResponse resp = getResp();
        if (resp == null) {
            throw new BaseException("无法获取响应对象");
        }

        // 防止中文文件名乱码 + 空格变加号
        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replace("+", "%20");

        //设置响应的文件类型
        if (mimes == null) {
            // 默认 MIME 类型
            mimes = Mimes.APPLICATION_OCTET_STREAM;
        }
        resp.setContentType(mimes.value());

        // 指定是否可预览和文件名称
        String dispositionType = PREVIEWABLE_MIME_TYPES.contains(mimes.value()) ? "inline" : "attachment";
        resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, dispositionType + "; filename=\"" + encodedFilename + "\"");

        // 防止缓存
        resp.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        resp.setHeader(HttpHeaders.PRAGMA, "no-cache");
        resp.setDateHeader(HttpHeaders.EXPIRES, 0);

    }

    /**
     * 设置响应头长度
     * @param contentLength 长度
     */
    public static void setContentLength(Long contentLength) {
        HttpServletResponse resp = getResp();
        if (resp == null) {
            throw new BaseException("无法获取响应对象");
        }
        resp.setContentLengthLong(contentLength);
    }

    public static void responseByCode(HttpServletResponse res, Integer code, String msg) {
        try {
            R<?> r = code == 0 ? R.success() : R.error();
            r.setMessage(msg).setCode(code);
            res.setContentType("text/json;charset=UTF-8");
            res.setStatus(HttpStatus.OK.value());
            res.getWriter().printf(r.toString());
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }
}
