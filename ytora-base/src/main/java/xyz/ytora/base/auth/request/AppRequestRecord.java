package xyz.ytora.base.auth.request;

/**
 * 单次请求的监控记录。
 *
 * @param startTime   请求开始时间戳
 * @param method      请求方法
 * @param path        请求路径
 * @param query       请求查询串
 * @param status      响应状态码
 * @param durationMs  总耗时
 * @param clientIp    客户端IP
 * @param userId      当前用户ID
 * @param userName    当前用户名
 * @param error       是否异常结束
 */
public record AppRequestRecord(
        long startTime,
        String method,
        String path,
        String query,
        int status,
        long durationMs,
        String clientIp,
        String userId,
        String userName,
        boolean error
) {
}
