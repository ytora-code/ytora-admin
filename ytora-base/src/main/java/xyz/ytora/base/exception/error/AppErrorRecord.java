package xyz.ytora.base.exception.error;

/**
 * 应用错误记录。
 *
 * @param timestamp      记录时间
 * @param category       错误分类
 * @param exceptionClass 异常类名
 * @param message        异常消息
 * @param path           请求路径
 * @param method         请求方法
 * @param clientIp       客户端IP
 * @param userId         当前用户ID
 * @param userName       当前用户名
 * @param stackTrace     堆栈信息
 */
public record AppErrorRecord(
        long timestamp,
        String category,
        String exceptionClass,
        String message,
        String path,
        String method,
        String clientIp,
        String userId,
        String userName,
        String stackTrace
) {
}
