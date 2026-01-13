package xyz.ytora.core.monitor.os.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "网络实时流量指标")
public class NetDynamicResp {
    @Schema(description = "接口名称", example = "eth0")
    private String ifaceName;
    @Schema(description = "接收速度 (Bytes/s)")
    private Long rxSpeed;
    @Schema(description = "发送速度 (Bytes/s)")
    private Long txSpeed;
    @Schema(description = "累计接收 (Bytes)")
    private Long rxBytes;
    @Schema(description = "累计发送 (Bytes)")
    private Long txBytes;
    @Schema(description = "采样时间戳")
    private Long timestamp;
}