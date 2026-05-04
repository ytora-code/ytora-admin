package xyz.ytora.core.monitor.sse.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * SSE客户端
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Builder
@Schema(description = "SSE客户端")
public class ClientItem {
    @Schema(description = "客户端ID")
    private String clientId;

    @Schema(description = "建连请求路径")
    private String requestUri;

    @Schema(description = "客户端IP")
    private String clientIp;

    @Schema(description = "UserAgent")
    private String userAgent;

    @Schema(description = "建连时间戳")
    private Long connectedAt;

    @Schema(description = "累计推送数据大小(Bytes)")
    private Long totalPayloadBytes;

    @Schema(description = "最近推送的10条数据")
    private List<PushMessageItem> recentMessages;
}