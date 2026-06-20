package xyz.ytora.core.ai.logic.aiimpl.support.api.model;

import lombok.Data;

/**
 * 统一AI API对话响应
 *
 * @author ytora
 * @since 1.0
 */
@Data
public class ApiChatResponse {

    private String provider;

    private String model;

    private String sessionId;

    private String chatId;

    private String content;

    private String finishReason;

    private ApiUsage usage;

    private String requestJson;

    private String rawJson;

}
