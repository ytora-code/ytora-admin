package xyz.ytora.core.monitor.sse.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 客户端最近推送消息
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Builder
@Schema(description = "客户端最近推送消息")
public class PushMessageItem {
    @Schema(description = "推送时间戳")
    private Long timestamp;

    @Schema(description = "事件名称")
    private String event;

    @Schema(description = "消息ID")
    private String messageId;

    @Schema(description = "推送数据")
    private String payload;

    @Schema(description = "推送数据字节数")
    private Long payloadBytes;
}
