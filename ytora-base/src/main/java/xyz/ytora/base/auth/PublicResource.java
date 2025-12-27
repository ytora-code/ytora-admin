package xyz.ytora.base.auth;

/**
 * 系统无需鉴权的接口
 */
public enum PublicResource {
    CAPTCHA("/sys/getCode"),
    LOGIN("/sys/doLogin"),
    LOGOUT("/sys/logout"),
    TOKEN_TIME_OUT("/sys/getTokenTimeOut"),
    SYS_STATUS_CODE("/sys/listResCode"),
    SYS_PUBLIC_RESOURCE("/sys/listSysPublicResource"),
    UPLOAD_FILE("/sys/file/upload"),
    DOWNLOAD_FILE("/sys/file/download"),
    DOWNLOAD_FILE_TEST("/sys/fileTest/**"),
    DELETE_FILE("/sys/deleteFile"),
    DRUID("/druid/**"),
    SSE("/sys/sse/connect"),
    ;

    /**
     * 资源名称
     */
    public final String resource;

    PublicResource(String resource) {
        this.resource = resource;
    }
}
