package xyz.ytora.core.monitor.sse.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * SSE 连接状态。
 */
@Data
@Builder
@Schema(description = "SSE连接状态")
public class AppSseMetricsData {

    @Schema(description = "当前SSE连接总数")
    private Integer connectionCount;

    @Schema(description = "客户端连接与推送统计信息")
    private List<ClientItem> clients;

    @Schema(description = "采样时间戳")
    private Long timestamp;

}
