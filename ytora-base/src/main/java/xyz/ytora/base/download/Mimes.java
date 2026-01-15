package xyz.ytora.base.download;

import xyz.ytora.base.exception.BaseException;

/**
 * MIME类型
 */
public enum Mimes {

    // 基础类型
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_CSV("text/csv"),
    TEXT_XML("text/xml"),

    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART_FORM_DATA("multipart/form-data"),

    // 二进制与流
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    APPLICATION_X_MSDOWNLOAD("application/x-msdownload"),
    //非标准的 MIME 类型，最好不用
    APPLICATION_STREAM("application/stream"),

    // 图片类型
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_SVG("image/svg+xml"),
    IMAGE_WEBP("image/webp"),
    IMAGE_BMP("image/bmp"),

    // 扩展图片类型
    IMAGE_ICO("image/x-icon"),                // .ico 网页图标
    IMAGE_TIFF("image/tiff"),                 // .tiff 高质量图像

    // 音视频类型
    AUDIO_MPEG("audio/mpeg"),
    AUDIO_OGG("audio/ogg"),
    VIDEO_MP4("video/mp4"),
    VIDEO_WEBM("video/webm"),
    AUDIO_WAV("audio/wav"),                   // .wav
    AUDIO_FLAC("audio/flac"),                 // .flac
    VIDEO_AVI("video/x-msvideo"),             // .avi
    VIDEO_MOV("video/quicktime"),             // .mov（苹果格式）

    // Office 97-2003 格式
    APPLICATION_DOC("application/msword"),                     // .doc
    APPLICATION_XLS("application/vnd.ms-excel"),               // .xls
    APPLICATION_PPT("application/vnd.ms-powerpoint"),          // .ppt

    // Office OpenXML 格式（2007+）
    APPLICATION_DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),   // .docx
    APPLICATION_XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),         // .xlsx
    APPLICATION_PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"), // .pptx

    // 模板文件
    APPLICATION_DOTX("application/vnd.openxmlformats-officedocument.wordprocessingml.template"),   // .dotx
    APPLICATION_XLTX("application/vnd.openxmlformats-officedocument.spreadsheetml.template"),      // .xltx
    APPLICATION_POTX("application/vnd.openxmlformats-officedocument.presentationml.template"),     // .potx

    // 幻灯片播放文件
    APPLICATION_PPSX("application/vnd.openxmlformats-officedocument.presentationml.slideshow"),    // .ppsx

    // 含宏格式
    APPLICATION_DOCM("application/vnd.ms-word.document.macroEnabled.12"),        // .docm
    APPLICATION_XLSM("application/vnd.ms-excel.sheet.macroEnabled.12"),          // .xlsm
    APPLICATION_PPTM("application/vnd.ms-powerpoint.presentation.macroEnabled.12"), // .pptm

    // Excel 二进制格式
    APPLICATION_XLSB("application/vnd.ms-excel.sheet.binary.macroEnabled.12"),   // .xlsb
    // OpenDocument 格式（LibreOffice / OpenOffice）
    APPLICATION_ODT("application/vnd.oasis.opendocument.text"),                    // .odt
    APPLICATION_ODS("application/vnd.oasis.opendocument.spreadsheet"),             // .ods
    APPLICATION_ODP("application/vnd.oasis.opendocument.presentation"),            // .odp

    // 字体类型
    FONT_WOFF("font/woff"),                   // .woff
    FONT_WOFF2("font/woff2"),                 // .woff2

    // 浏览器资源类型
    APPLICATION_WASM("application/wasm"),     // .wasm WebAssembly
    APPLICATION_MANIFEST_JSON("application/manifest+json"),  // PWA manifest

    // 安全与证书
    APPLICATION_PEM("application/x-pem-file"),            // .pem 证书文件
    APPLICATION_CRT("application/x-x509-ca-cert"),        // .crt X.509证书

    // 压缩文件
    APPLICATION_ZIP("application/zip"),
    APPLICATION_GZIP("application/gzip"),
    APPLICATION_X_TAR("application/x-tar"),
    APPLICATION_X_7Z_COMPRESSED("application/x-7z-compressed"),

    // PDF / RTF
    APPLICATION_PDF("application/pdf"),
    APPLICATION_RTF("application/rtf"),

    // 其他
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_EPUB("application/epub+zip");

    private final String value;

    Mimes(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Mimes get(String type) {
        for (Mimes mimes : Mimes.values()) {
            if (mimes.value.equals(type)) {
                return mimes;
            }
        }
        throw new BaseException("未知的文件类型：" + type);
    }
}
