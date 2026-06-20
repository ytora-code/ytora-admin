package xyz.ytora.core.ai.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * AI 对话请求参数
 *
 * @author ytora
 * @since 1.0
 */
@Data
@Schema(description = "AI 对话请求参数")
public class AiChatParam {

    /**
     * 会话ID。为空时表示开启新对话。
     */
    @Schema(description = "会话ID。为空时表示开启新对话")
    private String sessionId;

    /**
     * 用户消息正文
     */
    @NotBlank
    @Schema(description = "用户消息正文")
    private String msg;

    /**
     * 透传给底层 AI 工具的附加参数
     */
    @Schema(description = "透传给底层 AI 工具的附加参数")
    private String[] args;

}
